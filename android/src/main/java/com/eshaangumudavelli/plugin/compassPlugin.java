package com.eshaangumudavelli.plugin;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "compass")
public class compassPlugin extends Plugin implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;

    private float[] lastAccelerometer = new float[3];
    private float[] lastMagnetometer = new float[3];
    private boolean accelerometerSet = false;
    private boolean magnetometerSet = false;

    private float[] rotationMatrix = new float[9];
    private float[] orientation = new float[3];

    private compass implementation = new compass();

    @Override
    public void load(){
        Context context = getContext();
      sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
      accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
      magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        startCompassUpdates();


    }


    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        System.out.println("This is the ECHO JSON: " + ret);
        call.resolve(ret);
    }

    public void startCompassUpdates() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @PluginMethod
    public void getHeading(PluginCall call){
        JSObject ret = new JSObject();
        System.out.println("This is the JSON object before: " + ret);
        ret.put("heading", 1234);
        System.out.println("This is the JSON object after: " + ret);
        call.resolve(ret);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

       // if (event.sensor == magnetometer){
       //     int degree = Math.round(event.values[0]);
       //     System.out.println("This is the on sensor change event degree: " + degree);
//
       //     JSObject ret = new JSObject();
//
       //     ret.put("heading", degree);
       //     System.out.println("This is the value of ret: " + ret);
       //     notifyListeners("compassUpdate", ret);
       // }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, lastAccelerometer, 0, event.values.length);
            accelerometerSet = true;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, lastMagnetometer, 0, event.values.length);
            magnetometerSet = true;
        }

        if (accelerometerSet && magnetometerSet) {
            SensorManager.getRotationMatrix(rotationMatrix, null, lastAccelerometer, lastMagnetometer);
            SensorManager.getOrientation(rotationMatrix, orientation);

            // Convert radians to degrees
            float azimuthInRadians = orientation[0];
            float azimuthInDegrees = (float) (Math.toDegrees(azimuthInRadians) + 360) % 360;

            System.out.println("Azimuth: " + azimuthInDegrees);

            JSObject ret = new JSObject();

            ret.put("heading", azimuthInDegrees);
            notifyListeners("compassUpdate", ret);

            //  if (event.sensor == accelerometer) {
            //      System.arraycopy(event.values, 0, lastAccelerometer, 0, event.values.length);
            //      isAccelerometerSet = true;
            //  } else if (event.sensor == magnetometer) {
            //      System.arraycopy(event.values, 0, lastMagnetometer, 0, event.values.length);
            //      isMagnetometerSet = true;
            //  }
//
            //  if (isAccelerometerSet && isMagnetometerSet) {
            //      SensorManager.getRotationMatrix(rotationMatrix, null, lastAccelerometer, lastMagnetometer);
            //      SensorManager.getOrientation(rotationMatrix, orientation);
            //      float azimuthInRadians = orientation[0];
            //      float azimuthInDegrees = (float) (Math.toDegrees(azimuthInRadians) + 360) % 360;
//
            //      System.out.println("This is azimuth in degrees: " + azimuthInDegrees);


            //isAccelerometerSet = false;
            //isMagnetometerSet = false;
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
