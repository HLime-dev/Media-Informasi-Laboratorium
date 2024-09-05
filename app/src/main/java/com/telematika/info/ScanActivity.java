package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Handler;
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
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
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

        int finishTime = 60; // 10 detik
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Buat dialog pop-up dengan pesan
                AlertDialog.Builder builder = new AlertDialog.Builder(ScanActivity.this);
                builder.setMessage("Tidak ada tag yang terdeteksi");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Ketika tombol OK ditekan, buat Intent untuk memulai MainActivity
                        Intent intent = new Intent(ScanActivity.this, MainActivity.class);
                        startActivity(intent);

                        // Tutup Activity saat ini
                        finish();
                    }
                });

                // Tampilkan dialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }, finishTime * 1000);

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
                Toast.makeText(this, "Tag NFC tidak dikenali", Toast.LENGTH_SHORT).show();
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
                    byte[] expectedTagId1 = new byte[]{(byte) 0x53, (byte) 0x38, (byte) 0x77, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    byte[] expectedTagId2 = new byte[]{(byte) 0x53, (byte) 0x0F, (byte) 0x7F, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    byte[] expectedTagId3 = new byte[]{(byte) 0x04, (byte) 0x3C, (byte) 0x6E, (byte) 0xCA, (byte) 0x37, (byte) 0x58, (byte) 0x80};
                    byte[] expectedTagId4 = new byte[]{(byte) 0x04, (byte) 0x3C, (byte) 0x6E, (byte) 0xCA, (byte) 0x37, (byte) 0x58, (byte) 0x80};
                    byte[] expectedTagId5 = new byte[]{(byte) 0x53, (byte) 0x30, (byte) 0x7B, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    byte[] expectedTagId6 = new byte[]{(byte) 0x53, (byte) 0x30, (byte) 0x7B, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    byte[] expectedTagId7 = new byte[]{(byte) 0x53, (byte) 0x30, (byte) 0x7B, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    byte[] expectedTagId8 = new byte[]{(byte) 0x53, (byte) 0x30, (byte) 0x7B, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    byte[] expectedTagId9 = new byte[]{(byte) 0x53, (byte) 0x30, (byte) 0x7B, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    byte[] expectedTagId10 = new byte[]{(byte) 0x53, (byte) 0x30, (byte) 0x7B, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    byte[] expectedTagId11 = new byte[]{(byte) 0x53, (byte) 0x30, (byte) 0x7B, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    byte[] expectedTagId12 = new byte[]{(byte) 0x53, (byte) 0x30, (byte) 0x7B, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};
                    byte[] expectedTagId13 = new byte[]{(byte) 0x53, (byte) 0x30, (byte) 0x7B, (byte) 0xA1, (byte) 0x00, (byte) 0x00, (byte) 0x01};

                    if (Arrays.equals(expectedTagId1, id)) {
                        // ID tag sesuai, pindah ke MenuActivity
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab1", "lab1.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId2, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab2", "lab2.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId3, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab3", "lab3.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId4, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab4", "lab4.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId5, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab5", "lab5.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId6, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab6", "lab6.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId7, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab7", "lab7.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId8, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab8", "lab8.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId9, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab9", "lab9.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId10, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab10", "lab10.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId11, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab11", "lab11.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId12, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab12", "lab12.php");
                        startActivity(intent);
                    } else if (Arrays.equals(expectedTagId13, id)) {
                        Intent intent = new Intent(ScanActivity.this, MenuActivity.class);
                        intent.putExtra("lab13", "lab13.php");
                        startActivity(intent);
                    } else {
                        // ID tag berbeda, tampilkan Toast
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ScanActivity.this, "Tag NFC tidak dikenali", Toast.LENGTH_SHORT).show();
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