package com.example.cw;

public class Camera {
    private Vector vuv; // View Up Vector
    private Vector vrp; //View Reference Point
    private Vector vrv; //View Right Vector
    private Vector vpn; //View Plane Normal
    private Vector lookAt;

    public Camera(Vector vrp) {
        this.vuv = new Vector(0, 1, 0);
        this.lookAt = new Vector(0, 0, 0);
        this.vrp = vrp;
        this.vpn = lookAt.sub(vrp);
        this.vpn.normalise();
        this.vrv = vpn.cross(vuv);
        this.vrv.normalise();
        this.vuv.cross(vpn);
        vuv.normalise();
    }

    public Vector makeRay(double x, double y) {
        return vrp.add(vrv.mul(x)).add(vuv.mul(y));
    }

    public Vector calVPN(Vector lookAt, Vector vrp) {
        return lookAt.sub(vrp);
    }


    public Vector calVRV(Vector vpn, Vector vuv) {
        return vpn.cross(vuv);
    }

    public Vector calVUV(Vector vrv, Vector vpn) {
        return vrv.cross(vpn);
    }

    public Vector calVRP(Vector vpn, Vector lookAt) {
        return vpn.add(lookAt);
    }

    public Vector getVuv() {
        return vuv;
    }

    public void setVuv(Vector vuv) {
        this.vuv = vuv;
    }

    public Vector getVrp() {
        return vrp;
    }

    public void setVrp(Vector vrp) {
        this.vrp = vrp;
    }

    public Vector getVrv() {
        return vrv;
    }

    public void setVrv(Vector vrv) {
        this.vrv = vrv;
    }

    public Vector getVpn() {
        return vpn;
    }

    public void setVpn(Vector vpn) {
        this.vpn = vpn;
    }

    public Vector getLookAt() {
        return lookAt;
    }

    public void setLookAt(Vector lookAt) {
        this.lookAt = lookAt;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "vrp=" + vrp +
                ", lookAt=" + lookAt +
                '}';
    }
}
