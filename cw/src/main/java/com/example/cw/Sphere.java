package com.example.cw;

public class Sphere {
    private double x;
    private double y;
    private double z;
    private double radius;
    private double a;
    private double b;
    private double c;
    private double disc;
    private Vector centerSpehere;
    private Vector sphereCol;
    private double shpereT;

    public Sphere(Vector sCol,  double x, double y, double z, double r){
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = r;
        this.sphereCol = sCol;
        setCenterSpehere();
    }

    public double calulateIntersect(Vector o, Vector d){
        Vector v = o.sub(centerSpehere);
        setA(d);
        setB(v,d);
        setC(v);
        return (b*b)-(4*a*c);
    }
    public void setA(Vector d) {
        this.a = d.dot(d);
    }
    public void setB(Vector v, Vector d) {
        this.b = 2 *  (v.dot(d)) ;
    }
    public void setC(Vector v) {
        this.c = (v.dot(v)) - (radius*radius);
    }

    public double getShpereT() {
        return shpereT;
    }
    public void setShpereT(double shpereT) {
        this.shpereT = shpereT;
    }
    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getC() {
        return c;
    }
    public double getDisc() {
        return disc;
    }
    public void setDisc(double disc) {
        this.disc = disc;
    }
    public void setCenterSpehere(Vector centerSpehere) {
        this.centerSpehere = centerSpehere;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getZ() {
        return z;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Vector getCenterSpehere() {
        return centerSpehere;
    }

    public void setCenterSpehere() {
        this.centerSpehere = new Vector(x,y,z);
    }


    public Vector getSphereCol() {
        return sphereCol;
    }


    public void setSphereCol(Vector sphereCol) {
        this.sphereCol = sphereCol;
    }

    public double getColX() {
        return sphereCol.x;
    }

    public double getColY() {
        return sphereCol.y;
    }

    public double getColZ() {
        return sphereCol.z;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
