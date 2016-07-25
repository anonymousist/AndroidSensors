package com.example.administrator.accelerometertest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements SensorEventListener{
    public SensorManager sensorManager;
    public TextView editText,magneticText;
    public TextView directText;
    public TextView lighText;
    public TextView TemperatureText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (TextView)findViewById(R.id.textView2);
        magneticText = (TextView)findViewById(R.id.textView3);
        directText  = (TextView)findViewById(R.id.textView4);
        lighText = (TextView)findViewById(R.id.textView5);
        TemperatureText = (TextView)findViewById(R.id.textView6);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }
    @Override
    protected  void onResume()
    {
     super.onResume();
        //register listener for sensor
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected  void onStop(){
        sensorManager.unregisterListener(this);
        super.onStop();
    }
    @Override
    protected void onPause(){
        sensorManager.unregisterListener(this);
        super.onPause();

    }
    public void onSensorChanged(SensorEvent event){
        float[] values = event.values;
        int sensorType = event.sensor.getType();
        StringBuilder sb = null;
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                sb = new StringBuilder();
                sb.append("X轴上的加速度： ");
                sb.append(values[0]);
                sb.append("\nY轴上的加速度： ");
                sb.append(values[1]);
                sb.append("\nZ轴上的加速度： ");
                sb.append(values[2]);
                editText.setText(sb.toString());
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                 sb = new StringBuilder();
                 sb.append("X轴上的磁场强度： ");
                 sb.append(values[0]);
                 sb.append("\nY轴上的磁场强度： ");
                 sb.append(values[1]);
                 sb.append("\nZ轴上的磁场强度： ");
                 sb.append(values[2]);
                magneticText.setText(sb.toString());
                break;
            case Sensor.TYPE_ORIENTATION:
                sb = new StringBuilder();
                sb.append("绕Z轴旋转的角度： ");
                sb.append(values[0]);
                sb.append("\n绕X轴旋转的角度： ");
                sb.append(values[1]);
                sb.append("\n绕Y轴旋转的角度： ");
                sb.append(values[2]);
                directText.setText(sb.toString());
                break;
            case Sensor.TYPE_LIGHT:
                sb = new StringBuilder();
                sb.append("当前光线强度： ");
                sb.append(values[0]);
                lighText.setText(sb.toString());
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sb = new StringBuilder();
                sb.append("当前温度为： ");
                sb.append(values[0]);
                lighText.setText(sb.toString());
                break;
        }
    }
    public  void onAccuracyChanged(Sensor sensor,int accuracy){

    }
}
