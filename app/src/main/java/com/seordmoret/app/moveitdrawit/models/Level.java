package com.seordmoret.app.moveitdrawit.models;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private int nr;
    private String levelName;
    private float speed = 1f;//standard 1
    private int seconds = 5;//standard 5

    private List<PathPoints> pathPoints;
    private Paint pathPaint = new Paint();
    private Paint linePaint = new Paint();

    public Level(int nr, String levelName, float speed, int seconds) {
        this.nr = nr;
        this.levelName = levelName;
        this.speed = speed;
        this.seconds = seconds;

        setStandardValues();
        setPath();
    }

    private void setPath() {
        pathPoints = new ArrayList<>();
        pathPoints.add(new PathPoints(0.25f, 0.25f));
        pathPoints.add(new PathPoints(0.75f, 0.25f));
        pathPoints.add(new PathPoints(0.75f, 0.75f));
        pathPoints.add(new PathPoints(0.25f, 0.75f));
        pathPoints.add(new PathPoints(0.25f, 0.25f));
    }

    private void setStandardValues() {
        linePaint.setColor(Color.BLUE);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(8f);

        pathPaint.setColor(Color.YELLOW);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(25f);
    }

    public int getNr() {
        return nr;
    }

    public String getLevelName() {
        return levelName;
    }

    public float getSpeed() {
        return speed;
    }

    public int getSeconds() {
        return seconds;
    }

    public List<PathPoints> getPathPoints() {
        return pathPoints;
    }

    public Paint getPathPaint() {
        return pathPaint;
    }

    public Paint getLinePaint() {
        return linePaint;
    }
}
