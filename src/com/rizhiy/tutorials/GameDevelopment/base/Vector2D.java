package com.rizhiy.tutorials.GameDevelopment.base;

/**
 * Created by rizhiy on 23/04/16.
 */
public class Vector2D {
    private double x;
    private double y;

    public Vector2D(Vector2D other){
        x = other.x;
        y = other.y;
    }

    public Vector2D(){
        this(0,0);
    }

    @Override
    public String toString() {
        return  "("+ x +","+y+")";
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D zero(){
        return new Vector2D();
    }

    public void normalise() {
        double length = Math.sqrt(x * x + y * y);

        if (length != 0) {
            x = (double) (x / length);
            y = (double) (y / length);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (Double.compare(vector2D.x, x) != 0) return false;
        return Double.compare(vector2D.y, y) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static Vector2D add(Vector2D left, Vector2D right){
        return new Vector2D(left.x + right.x, left.y + right.x);
    }

    public void add(Vector2D other){
        this.x += other.getX();
        this.y += other.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void changeX(double change){
        x += change;
    }

    public void incX(){
        changeX(1.0);
    }

    public void decX(){
        changeX(-1.0);
    }

    public void changeY(double change){
        y += change;
    }

    public void incY(){
        changeY(1.0);
    }
    public void decY(){
        changeY(-1.0);
    }

    public static double getDistanceOnScreen(Vector2D vec1, Vector2D vec2){
        double dx = vec1.getX() - vec2.getX();
        double dy = vec1.getY() - vec2.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    //Not sure if this is needed
    public static double getDistanceBetweenWorldVectors(Vector2D vec1, Vector2D vec2){
        double dx = vec1.getX() - vec2.getX();
        double dy = vec1.getY() - vec2.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

}
