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


public class GameView extends View {
    private int diameter;
    private int x;
    private int y;
    private ShapeDrawable bubble;
    private float speed = 1.55f;
    private int width;
    private int height;
    Path p = new Path();
    private Paint linePaint = new Paint();

    private Path levelPath = new Path();
    private Paint levelPaint = new Paint();


    public GameView(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;

        setPaints();
        setLevel();
        createBubble();
    }

    private void setPaints(){
        linePaint.setColor(Color.BLUE);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(8f);

        levelPaint.setColor(Color.YELLOW);
        levelPaint.setStyle(Paint.Style.STROKE);
        levelPaint.setStrokeWidth(25f);
    }

    private void setLevel(){
        levelPath = new Path();
        //start position
        levelPath.moveTo(width/4, height/4);

        levelPath.lineTo((width/4)*3, (height/4));
        levelPath.moveTo((width/4)*3, (height/4));

        levelPath.lineTo((width/4)*3, (height/4)*3);
        levelPath.moveTo((width/4)*3, (height/4)*3);

        levelPath.lineTo((width/4), (height/4)*3);
        levelPath.moveTo((width/4), (height/4)*3);

        levelPath.lineTo(width/4, height/4);
    }

    private void createBubble() {
        x = width / 2;
        y = height / 2;
        diameter = 100;
        bubble = new ShapeDrawable(new OvalShape());
        bubble.setBounds(x, y, x + diameter, y + diameter);
        bubble.getPaint().setColor(0xff00cccc);
        p.moveTo(bubble.getBounds().centerX(), bubble.getBounds().centerY());
    }

    private void addPointToLine(float x, float y) {
        if (p != null) {
            p.lineTo(x, y);
            p.moveTo(x, y);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bubble.draw(canvas);
        canvas.drawPath(levelPath, levelPaint);
        canvas.drawPath(p, linePaint);
    }

    public void move(float f, float g) {
        x = (int) ((x + (-f * speed)));
        y = (int) ((y + (g * speed)));

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
}
