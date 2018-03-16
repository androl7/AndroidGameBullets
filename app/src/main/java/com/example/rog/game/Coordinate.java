package com.example.rog.game;

/**
 * Created by ROG on 13.03.2018.
 */

public class Coordinate {
    private int x;
    private int y;


    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }


    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (getX() == that.getX()) if (getY() == that.getY()) return true;
        return false;
    }
}
