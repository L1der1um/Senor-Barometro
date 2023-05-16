package com.example.senor_barometro_2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView;

    //gerenciador de sensores
    private SensorManager sensorManager;

    //instanciador do sensor
    private Sensor sensorpressao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        // type cast (SensorManager);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // DEFININDO O SENSOR (TYPE_PRESSURE)

        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
            sensorpressao = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        } else {
            Toast.makeText(this, "Este sensor não existe neste dispositivo", Toast.LENGTH_SHORT).show();
        }
    }

    //metodo chamado quando o app estiver inativo ou em segundo plano
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorpressao, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values;
        textView.setText(String.format("%.3f:mBar", values[0]));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}