package com.example.cw;

public class Vector {
    double x, y, z;

    public Vector() {
    }

    public Vector(double i, double j, double k) {
        x = i;
        y = j;
        z = k;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void normalise() {
        double mag = magnitude();
        if (mag != 0) {
            x /= mag;
            y /= mag;
            z /= mag;
        }
    }

    public Vector cross(Vector a) {
        //System.out.println();
        return new Vector(
                this.y * a.z - this.z * a.y,
                this.z * a.x - this.x * a.z,
                this.x * a.y - this.y * a.x
        );
    }

    public static Vector cross(Vector v1, Vector v2) {
        return new Vector(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x * v2.y - v1.y * v2.x
        );
    }

    public double distance(Vector a) {
        return Math.sqrt(((x - a.x) * 2) + ((y - a.y) * 2.0) + ((z - a.z) * 2.0));
    }

    public double dot(Vector a) {
        return x * a.x + y * a.y + z * a.z;
    }

    public Vector sub(Vector a) {
        return new Vector(x - a.x, y - a.y, z - a.z);
    }

    public Vector add(Vector a) {
        return new Vector(x + a.x, y + a.y, z + a.z);
    }

    public Vector mul(double d) {
        return new Vector(d * x, d * y, d * z);
    }

    public Vector mul(Vector a) {
        return new Vector(a.x * x, a.y * y, a.z * z);
    }

    public String print() {
        return "x=" + x + ", y=" + y + ", z=" + z;
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

}
