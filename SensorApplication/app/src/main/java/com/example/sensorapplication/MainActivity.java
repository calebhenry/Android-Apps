package com.example.sensorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
    implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;
    private float[] mAccelerometerData = new float[3];
    private float[] mMagnetometerData = new float[3];
    private ImageView bubble;

    private float previousX = 0f;
    private float previousY = 0f;

    private static final float VALUE_DRIFT = 0.02f;

    private static final float MAXIMUM_MOVE = 0.03f;

    private static final float MINIMUM_MOVE = 0.01f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bubble = (ImageView) findViewById(R.id.bubble);;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this, mSensorAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorMagnetometer != null) {
            mSensorManager.registerListener(this, mSensorMagnetometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                mAccelerometerData = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagnetometerData = sensorEvent.values.clone();
                break;
            default:
                return;
        }

        float[] rotationMatrix = new float[9];
        boolean rotationOK = SensorManager.getRotationMatrix(rotationMatrix, null,
                mAccelerometerData, mMagnetometerData);
        float[] orientationValues = new float[3];
        if (rotationOK) {
            SensorManager.getOrientation(rotationMatrix, orientationValues);
        }
        float pitch = orientationValues[1];
        float roll = orientationValues[2];

        if (Math.abs(pitch) < VALUE_DRIFT) {
            pitch = 0;
        }
        if (Math.abs(roll) < VALUE_DRIFT) {
            roll = 0;
        }

        roll = normalizeMove(previousX, roll, "roll");
        pitch = normalizeMove(previousY, pitch, "pitch");

        bubble.setX(540 - roll*540);
        bubble.setY(1200 + pitch * 1200);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //intentionally blank
    }

    /**
     * Takes a move and ensures it goes no further than a specified distance
     * @param previousValue The value of this variable at the last render
     * @param currentValue The new value of this variable
     * @param type If this variable represents pitch or roll
     * @return The new pitch or roll value
     */
    private float normalizeMove(float previousValue, float currentValue, String type) {
        if(Math.abs(previousValue-currentValue) > MAXIMUM_MOVE) {
            if(previousValue-currentValue > 0) {
                currentValue = previousValue - MAXIMUM_MOVE;
            } else {
                currentValue = previousValue + MAXIMUM_MOVE;
            }
            if(Objects.equals(type, "roll")) {
                previousX = currentValue;
            } else {
                previousY = currentValue;
            }
        } else if(Math.abs(previousValue-currentValue) < MINIMUM_MOVE) {
            currentValue = previousValue;
        } else {
            if(Objects.equals(type, "roll")) {
                previousX = currentValue;
            } else {
                previousY = currentValue;
            }
        }
        if(currentValue > 0.95) {
            currentValue = 0.95f;
        } else if(currentValue < -0.95) {
            currentValue = -0.95f;
        }
        return currentValue;
    }
}