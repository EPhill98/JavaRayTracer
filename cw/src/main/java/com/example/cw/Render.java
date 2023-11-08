package com.example.cw;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Render {
    private int xSlide;
    private int ySlide;
    private int zSlide;
    private int w;
    private int h;
    private Vector origin;
    private Vector ray; //AKA VPN
    private Vector shpereCol;
    private Vector bkgCol;
    private Vector lookAt;
    private Vector light;
    private PixelWriter image_writer;
    private Camera cam;
    private ArrayList<Sphere> shpereLst;

    public Render(ArrayList sLst, PixelWriter image_writer, int w, int h) {
        this.shpereLst = sLst;
        this.image_writer = image_writer;
        this.w = w;
        this.h = h;
        this.lookAt = new Vector(0, 0, 0);
    }

    public void drawImage() {
        Vector origin = new Vector(xSlide, ySlide, zSlide);
        Camera myCam = new Camera(origin);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                double u = i - w / 2;
                double v = ((h - j) - h / 2);
                ray = myCam.makeRay(v, u);
                Sphere closestSphere = null;
                double tMin = Double.POSITIVE_INFINITY;
                for (Sphere sphere : shpereLst) {
                    double disc = sphere.calulateIntersect(myCam.getVrp(), ray);
                    double t = (-sphere.getB() - Math.sqrt(disc)) / (2 * sphere.getA());
                    if (t < 0) {
                        sphere.setShpereT(t);
                        t = (sphere.getB() - Math.sqrt(disc)) / (2 * sphere.getA());
                    }
                    if (t < tMin) {
                        closestSphere = sphere;
                        tMin = t;
                    }
                    if (closestSphere == null) {
                        image_writer.setColor(i, j, Color.color(bkgCol.x, bkgCol.y, bkgCol.z, 1));
                    } else {
                        Vector p = origin.add(ray.mul(closestSphere.getShpereT()));
                        Vector norm = p.sub(closestSphere.getCenterSpehere());
                        norm.normalise();
                        Vector ld = p.sub(light);
                        ld.normalise();
                        double dp = ld.dot(norm);
                        if (dp < 0) dp = 0;
                        if (dp > 1) dp = 1;
                        Vector colB = closestSphere.getSphereCol().mul(dp * 0.7).add(closestSphere.getSphereCol().mul(0.3));
                        image_writer.setColor(i, j, Color.color(colB.x, colB.y, colB.z, 1.0));
                    }
                }
            }
        }
    }

    public int getxSlide() {
        return xSlide;
    }

    public void setxSlide(int xSlide) {
        this.xSlide = xSlide;
    }

    public int getySlide() {
        return ySlide;
    }

    public void setySlide(int ySlide) {
        this.ySlide = ySlide;
    }

    public int getzSlide() {
        return zSlide;
    }

    public void setzSlide(int zSlide) {
        this.zSlide = zSlide;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Vector getOrigin() {
        return origin;
    }

    public void setOrigin(Vector origin) {
        this.origin = origin;
    }

    public Vector getRay() {
        return ray;
    }

    public void setRay(Vector ray) {
        this.ray = ray;
    }

    public Vector getShpereCol() {
        return shpereCol;
    }

    public void setShpereCol(Vector shpereCol) {
        this.shpereCol = shpereCol;
    }

    public Vector getBkgCol() {
        return bkgCol;
    }

    public void setBkgCol(Vector bkgCol) {
        this.bkgCol = bkgCol;
    }

    public Vector getLookAt() {
        return lookAt;
    }

    public void setLookAt(Vector lookAt) {
        this.lookAt = lookAt;
    }

    public Vector getLight() {
        return light;
    }

    public void setLight(Vector light) {
        this.light = light;
    }

    public PixelWriter getImage_writer() {
        return image_writer;
    }

    public void setImage_writer(PixelWriter image_writer) {
        this.image_writer = image_writer;
    }

    public Camera getCam() {
        return cam;
    }

    public void setCam(Camera cam) {
        this.cam = cam;
    }

    public ArrayList<Sphere> getShpereLst() {
        return shpereLst;
    }

    public void setShpereLst(ArrayList<Sphere> shpereLst) {
        this.shpereLst = shpereLst;
    }
}