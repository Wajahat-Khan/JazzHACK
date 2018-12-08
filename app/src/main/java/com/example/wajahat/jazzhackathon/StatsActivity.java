package com.example.wajahat.jazzhackathon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;

public class StatsActivity extends AppCompatActivity {
    int age, bp_s, bp_d;
    String race;
    Boolean heart_disease;

// bluetooth components
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    int counter;
    volatile boolean stopWorker;
    Button blu_btn;
    TextView tt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_second);
        // age=getIntent().getIntExtra("age",20);
        //bp_s=getIntent().getIntExtra("bp_s",120);
        //bp_d=getIntent().getIntExtra("bp_d",80);
        //race=getIntent().getStringExtra("race");
        //heart_disease=getIntent().getBooleanExtra("heart_disease", false);
/*
        Toast.makeText(this, Integer.toString(age), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Integer.toString(bp_s), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Integer.toString(bp_d), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, race, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Boolean.toString(heart_disease), Toast.LENGTH_SHORT).show();
   */
        tt=findViewById(R.id.blue);
        blu_btn = findViewById(R.id.bluetooth_btn);
        blu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findBT();
                try {
                    openBT();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });


    }

    private void findBT()  {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null)
        {
            Toast.makeText(this, "no connection", Toast.LENGTH_SHORT).show();
        }

        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 0);
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {

                if(device.getName().equals("HC-05"))
                {
                    mmDevice = device;
                    Toast.makeText(this, "yoyoyoyo", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
        Toast.makeText(this, "Bluetooth Device Found", Toast.LENGTH_SHORT).show();
    }

    void openBT() throws IOException
    {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Standard SerialPortService ID
        if(mmDevice!=null) {
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();

            beginListenForData();
        }
        Toast.makeText(this, "bluetooth opened", Toast.LENGTH_SHORT).show();;
    }

    void beginListenForData() throws UnsupportedEncodingException {
        final Handler handler = new Handler();
        stopWorker = false;
        readBufferPosition = 0;
        boolean g=false;
        readBuffer = new byte[1000024];
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {
                        int bytesAvailable = mmInputStream.available();
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInputStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];

                                readBuffer[readBufferPosition++] = b;
                            }
                        }
                        else{
                            break;
                        }

                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
        String abc=new String(readBuffer, "UTF-8");
        for(int i=0;i<readBuffer.length;i++) {
            Byte b = readBuffer[i];
            tt.setText(b.toString());
        }
    }

    void closeBT() throws IOException
    {
        stopWorker = true;
        mmOutputStream.close();
        mmInputStream.close();
        mmSocket.close();
        Toast.makeText(this, "Connection Closed", Toast.LENGTH_SHORT).show();
    }

}

