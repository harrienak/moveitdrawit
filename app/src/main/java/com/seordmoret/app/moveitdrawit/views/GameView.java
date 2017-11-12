package com.seordmoret.app.moveitdrawit.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.seordmoret.app.moveitdrawit.R;
import com.seordmoret.app.moveitdrawit.Util.Utils;
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

    Bitmap bitmapStart;

    private long amountOfPixels;

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
        bubble.getPaint().setColor(Utils.getColor(getContext(),R.color.ballColor));
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

        canvas.drawPath(levelPath, level.getPathPaintStroke());
        canvas.drawPath(levelPath, level.getPathPaintFill());
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

    public void calcScore() {
        float left = width;
        float right = 0;
        float top = height;
        float bottom = 0;

        for (int i = 0; i < level.getPathPoints().size();i++){
            float x = level.getPathPoints().get(i).getxPercentage() * width;
            float y = level.getPathPoints().get(i).getyPercentage() * height;
            if(x < left){
                left = x;
            }
            if(x > right){
                right = x;
            }
            if(y < top){
                top = y;
            }
            if(y > bottom){
                bottom = y;
            }
        }


        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas c = new Canvas(b);
        layout(getLeft(), getTop(), getRight (), getBottom());
        draw(c);

        int yellowColor = level.getPathPaintFill().getColor();
        long amount = 0;
        long pixels = 0;

       // for (int i = (int)left; i < (int) right; i++) {
       //     for (int j = (int) top; j < (int) bottom; j++) {
        for (int i = 100; i < b.getWidth(); i++) {
            for (int j = 100; j < b.getHeight(); j++) {
                int pixel = b.getPixel(i, j);
                amount++;
                //Log.d("COLOR", "color: " + i + "," + j + " - " + pixel);
                if (pixel == yellowColor) {
                    Log.d("COLOR", "color: " + i + "," + j + " - " + pixel);
                    pixels++;

                }
            }
        }

        Log.d("COLOR", "amount of pixels stills yellow: " + pixels + " / amount: " +amount);

    }
}
