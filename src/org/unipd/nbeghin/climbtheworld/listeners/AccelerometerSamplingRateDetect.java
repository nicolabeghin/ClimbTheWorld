package org.unipd.nbeghin.climbtheworld.listeners;

import org.unipd.nbeghin.climbtheworld.MainActivity;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

/**
 * Listener for sampling rate detector
 *
 */
public class AccelerometerSamplingRateDetect implements SensorEventListener {
    private int sensorDelay;
    private long now = 0;
    private long time = 0;
    private int temp = 0;
    private static final double nbElements = 30;
    IntentService service;
    public final static String SAMPLING_RATE_ACTION="com.nbeghin.unipd.accelerometer.samplingrate";
    public final static String SAMPLING_RATE="SAMPLING_RATE";

    public void setSensorDelay(int sensorDelay) {
        this.sensorDelay=sensorDelay;
    }

    public AccelerometerSamplingRateDetect(Context context, IntentService service) throws Exception {
        this.service=service;
        Log.i(MainActivity.AppName, "Accelerometer listener for sampling rate detection instanced");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long tS=event.timestamp;
        if (now != 0) {
            temp++;
            if (temp == nbElements) {
                time = tS - now;
                Intent intent=new Intent();
                intent.setAction(SAMPLING_RATE_ACTION);
                intent.putExtra(SAMPLING_RATE, (nbElements * 1000000000 / time));
                service.sendBroadcast(intent);
                temp = 0;
            }
        }
        if (temp == 0) {
            now = tS;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO
    }
}
