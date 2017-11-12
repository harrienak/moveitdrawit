package com.seordmoret.app.moveitdrawit.models;


import android.content.Context;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;

import com.seordmoret.app.moveitdrawit.R;
import com.seordmoret.app.moveitdrawit.Util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private Context context;
    private int nr;
    private String levelName;
    private float speed = 1f;//standard 1
    private int seconds = 5;//standard 5

    private List<PathPoints> pathPoints;
    private Paint pathPaintStroke = new Paint();
    private Paint pathPaintFill = new Paint();
    private Paint linePaint = new Paint();

    public Level(Context context, int nr, String levelName, float speed, int seconds) {
        this.context = context;
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
        linePaint.setColor(Utils.getColor(context, R.color.pathLine));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(12);
        linePaint.setDither(true);                    // set the dither to true
        linePaint.setStyle(Paint.Style.STROKE);       // set to STOKE
        linePaint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        linePaint.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
        linePaint.setPathEffect(new CornerPathEffect(10) );

        pathPaintStroke.setColor(Utils.getColor(context, R.color.levelLineStroke));
        pathPaintStroke.setAntiAlias(true);
        pathPaintStroke.setStrokeWidth(40);
        pathPaintStroke.setDither(true);                    // set the dither to true
        pathPaintStroke.setStyle(Paint.Style.STROKE);       // set to STOKE
        pathPaintStroke.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        pathPaintStroke.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
        pathPaintStroke.setPathEffect(new CornerPathEffect(10) );

        pathPaintFill.setColor(Utils.getColor(context, R.color.levelLineFill));
        pathPaintFill.setAntiAlias(true);
        pathPaintFill.setStyle(Paint.Style.STROKE);
        pathPaintFill.setStrokeWidth(18);
        pathPaintFill.setDither(true);                    // set the dither to true
        pathPaintFill.setStyle(Paint.Style.STROKE);       // set to STOKE
        pathPaintFill.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        pathPaintFill.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
        pathPaintFill.setPathEffect(new CornerPathEffect(10) );
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

    public Paint getLinePaint() {
        return linePaint;
    }

    public Paint getPathPaintStroke() {
        return pathPaintStroke;
    }

    public Paint getPathPaintFill() {
        return pathPaintFill;
    }
}
