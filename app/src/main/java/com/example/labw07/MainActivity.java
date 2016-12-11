package com.example.labw07;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button reset;
    TextView result, maxresult;
    SensorManager sensor;
    Sensor accel;
    float x,y,z,mx,my,mz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        reset = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.textView2);
        maxresult = (TextView) findViewById(R.id.textView3);
        reset.setOnClickListener(res);

    }

    View.OnClickListener res = new View.OnClickListener() {
        public void onClick(View v) {
            mx = 0;
            my = 0;
            mz = 0;
        }
    };

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        result.setText(String.valueOf(x)+" "+(y)+" "+(z));

        if (x > mx) mx = x; maxresult.setText(String.valueOf(mx)+" "+(my)+" "+(mz));
        if (y > my) my = y; maxresult.setText(String.valueOf(mx)+" "+(my)+" "+(mz));
        if (z > mz) mz = z; maxresult.setText(String.valueOf(mx)+" "+(my)+" "+(mz));

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensor.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
    }

}