package com.seordmoret.app.moveitdrawit.activity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;

import com.seordmoret.app.moveitdrawit.R;
import com.seordmoret.app.moveitdrawit.models.Level;
import com.seordmoret.app.moveitdrawit.views.GameView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements SensorEventListener {
    @BindView(R.id.game_container)
    FrameLayout gameContainer;
    @BindView(R.id.non_game_container)
    FrameLayout nonGameContainer;


    private SensorManager manager;
    private Sensor accel;

    private GameView gameView;
    private Level level;

    private int width, height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreenSizes();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        level = new Level(1, "Square..", 1.5f, 5);

        gameView = new GameView(this, width, height);
        gameContainer.addView(gameView);
        gameView.setLevel(level);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, accel,
                SensorManager.SENSOR_DELAY_GAME);
    }

    private void setScreenSizes() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        gameView.move(event.values[0], event.values[1]);
        gameView.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, accel,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
