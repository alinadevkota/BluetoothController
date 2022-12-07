package com.example.bluetoothtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    private Button top, bottom, left, right, s1cw, s2cw, s3cw, s4cw, s1acw, s2acw, s3acw, s4acw;

    private OutputStream outputStream;
    private InputStream inStream;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text_device = (TextView) this.findViewById(R.id.device_name_id);
        BA = BluetoothAdapter.getDefaultAdapter();
        top = findViewById(R.id.btop_id);
        bottom = findViewById(R.id.bbottom_id);
        left = findViewById(R.id.bleft_id);
        right = findViewById(R.id.bright_id);
        s1cw = findViewById(R.id.s1cw_id);
        s2cw = findViewById(R.id.s2cw_id);
        s3cw = findViewById(R.id.s3cw_id);
        s4cw = findViewById(R.id.s4cw_id);
        s1acw = findViewById(R.id.s1acw_id);
        s2acw = findViewById(R.id.s2acw_id);
        s3acw = findViewById(R.id.s3acw_id);
        s4acw = findViewById(R.id.s4acw_id);

        if (BA == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth not supported", Toast.LENGTH_LONG).show();
        }
        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
        }
        Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
//
        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                ParcelUuid[] uuids = device.getUuids();
                String deviceName = device.getName();
                BluetoothSocket socket = null;
                try {
                    socket = device.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to create Socket", Toast.LENGTH_LONG).show();
                }
                try {
                    socket.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to connect Socket", Toast.LENGTH_LONG).show();
                }
                try {
                    outputStream = socket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to create Outputstream to send bluetooth data", Toast.LENGTH_LONG).show();
                }
                String deviceHardwareAddress = device.getAddress(); // MAC address
//                Toast.makeText(getApplicationContext(), deviceHardwareAddress, Toast.LENGTH_LONG).show();
                text_device.setText(deviceName);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "No Paired Devices Found", Toast.LENGTH_LONG).show();
        }


        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "top 0000", Toast.LENGTH_LONG).show();
                try {
                    outputStream.write(1000);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_LONG).show();
                }
            }
        });
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "bottom 0001", Toast.LENGTH_LONG).show();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "left 0010", Toast.LENGTH_LONG).show();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "right 0011", Toast.LENGTH_LONG).show();
            }
        });
        s1cw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "S1cw 0100", Toast.LENGTH_LONG).show();
            }
        });
        s1acw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "S1acw 0101", Toast.LENGTH_LONG).show();
            }
        });
        s2cw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "S2cw 0110", Toast.LENGTH_LONG).show();
            }
        });
        s2acw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "S2acw 0111", Toast.LENGTH_LONG).show();
            }
        });
        s3cw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "S3cw 1000", Toast.LENGTH_LONG).show();
            }
        });
        s3acw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "S3acw 1001", Toast.LENGTH_LONG).show();
            }
        });
        s4cw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "S4cw 1010", Toast.LENGTH_LONG).show();
            }
        });
        s4acw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Instead of Toast below, send a code to bluetooth serial port
                Toast.makeText(getApplicationContext(), "S4acw 1011", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void write(String s, OutputStream outputStream) throws IOException {
        outputStream.write(s.getBytes());
    }
}