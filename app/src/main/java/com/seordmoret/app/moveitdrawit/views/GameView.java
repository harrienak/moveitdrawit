package com.seordmoret.app.moveitdrawit.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

import com.seordmoret.app.moveitdrawit.models.Level;
import com.seordmoret.app.moveitdrawit.models.PathPoints;


public class GameView extends View {
    private int diameter;
    private int x;
    private int y;
    private ShapeDrawable bubble;
    private int width;
    private int height;

    private Level level;

    private Path userPath = new Path();
    private Path levelPath;

    public GameView(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
    }

    private void createBubble() {
        x = width / 2;
        y = height / 2;
        diameter = 100;
        bubble = new ShapeDrawable(new OvalShape());
        bubble.setBounds(x, y, x + diameter, y + diameter);
        bubble.getPaint().setColor(0xff00cccc);
        userPath.moveTo(bubble.getBounds().centerX(), bubble.getBounds().centerY());
    }

    private void addPointToLine(float x, float y) {
        if (userPath != null) {
            userPath.lineTo(x, y);
            userPath.moveTo(x, y);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(levelPath, level.getPathPaint());
        canvas.drawPath(userPath, level.getLinePaint());
        bubble.draw(canvas);
    }

    public void move(float f, float g) {
        x = (int) ((x + (-f * level.getSpeed())));
        y = (int) ((y + (g * level.getSpeed())));

        if (x < 0) {
            x = 0;
        } else if (x > width - diameter) {
            x = width - diameter;
        }
        if (y < 0) {
            y = 0;
        } else if (y > height - diameter) {
            y = height - diameter;
        }

        bubble.setBounds(x, y, x + diameter, y + diameter);
        addPointToLine(bubble.getBounds().centerX(), bubble.getBounds().centerY());
    }

    public void setLevel(Level level) {
        this.level = level;
        levelPath = new Path();
        for (int i = 0; i < level.getPathPoints().size(); i++) {
            PathPoints pathPoints = level.getPathPoints().get(i);
            if (i != 0) {
                levelPath.lineTo(width * pathPoints.getxPercentage(), height * pathPoints.getyPercentage());
            }
            levelPath.moveTo(width * pathPoints.getxPercentage(), height * pathPoints.getyPercentage());
        }

        createBubble();
    }
}
