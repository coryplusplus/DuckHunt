package game;

import java.awt.*;
import java.util.Random;

public class Duck {

    private int XSIZE;
    private int YSIZE;
    // determines if the duck has been hit or not
    private int IsHit;

    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;


    public Duck()
    {
        Random generator = new Random();
        new Color(generator.nextInt(255), generator.nextInt(255),
                generator.nextInt(255));
        ;
        XSIZE = 85;
        YSIZE = 85;
        x = generator.nextInt(600);
        y = generator.nextInt(500);
    }

    public void move(int width, int height) {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= width) {
            x = width - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= height - 200) {
            y = height - 200 - YSIZE;
            dy = -dy;
        }
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public void setX(double z) {
        x = z;
    }

    public void setY(double z) {
        y = z;
    }

    public int getXSIZE() {
        return XSIZE;
    }

    public int getYSIZE() {
        return YSIZE;
    }

    public int getIsHit() {
        return IsHit;
    }

    public void setHit(int x) {
        IsHit = x;
    }

    public int getDX() {
        return (int) dx;
    }

    public int getDY() {
        return (int) dy;
    }

    public void setDX(double x) {
        dx = x;
    }

    public void setDY(double x) {
        dy = x;
    }

}