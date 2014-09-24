package com.sinius15.gyro;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class MainActivity extends Activity implements SensorEventListener, Runnable {

    private TextView console, ipView;
    private ServerSocket serverSocket;
    private boolean running = true;

    private ArrayList<SendSocket> sockets = new ArrayList<SendSocket>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(SendSocket s : sockets){
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        console = (TextView) findViewById(R.id.consoleView);
        ipView = (TextView) findViewById(R.id.ipView);
        ipView.setText(getIp());
        console.setText("Starting up...");

        SensorManager mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        mSensorManager.registerListener(this, mSensor, 1);
        running = true;
        new Thread(this).start();

    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(5555);
            while(running) {
                sockets.add(new SendSocket(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        console.setText("x-degrees: " + event.values[0] + "\ny-degrees: " +
                event.values[1] + "\nz-degrees: " + event.values[2]);
        for(SendSocket s : sockets){
            s.sendData(event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //hey this is new, lets do NOTHING. YEA. This feels good. :D
    }

    public String getIp() {
        WifiManager wifiMgr = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        return Formatter.formatIpAddress(ip);
    }
}
