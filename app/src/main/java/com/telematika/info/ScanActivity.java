package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScanActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;

    LinearLayout kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        kembali = (LinearLayout) findViewById(R.id.kembali);

        getWindow() .getDecorView(). setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // Mengecek apakah NFC tersedia di perangkat
        if (nfcAdapter == null) {
            Toast.makeText(this, "Perangkat tidak mendukung NFC", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mengecek apakah NFC diaktifkan di perangkat
        if (!nfcAdapter.isEnabled()) {
            // Menampilkan pesan untuk mengaktifkan NFC
            Toast.makeText(this, "Aktifkan NFC", Toast.LENGTH_SHORT).show();

            // Membuka pengaturan untuk mengaktifkan NFC
            Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
            startActivity(intent);
        }


        LinearLayout kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] id = tag.getId();

            // Periksa apakah ID tag sesuai dengan ID yang diharapkan
            byte[] expectedTagId = new byte[]{(byte) 0x53, (byte) 0x44, (byte) 0x77, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
            if (Arrays.equals(expectedTagId, id)) {
                // ID tag sesuai, pindah ke MenuActivity
                Intent newActivityIntent = new Intent(this, MenuActivity.class);
                startActivity(newActivityIntent);
            } else {
                // ID tag berbeda, tampilkan Toast
                Toast.makeText(this, "Tag NFC dengan ID berbeda terdeteksi", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_IMMUTABLE);

            nfcAdapter.enableReaderMode(this, new NfcAdapter.ReaderCallback() {
                @Override
                public void onTagDiscovered(Tag tag) {
                    byte[] id = tag.getId();

                    // Periksa apakah ID tag sesuai dengan ID yang diharapkan
                    byte[] expectedTagId = new byte[]{(byte) 0x53, (byte) 0x44, (byte) 0x77, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    if (Arrays.equals(expectedTagId, id)) {
                        // ID tag sesuai, pindah ke MenuActivity
                        Intent newActivityIntent = new Intent(ScanActivity.this, MenuActivity.class);
                        startActivity(newActivityIntent);
                    } else {
                        // ID tag berbeda, tampilkan Toast
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ScanActivity.this, "Tag NFC dengan ID berbeda terdeteksi", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }, NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK | NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_NFC_B | NfcAdapter.FLAG_READER_NFC_BARCODE, null);
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }


}