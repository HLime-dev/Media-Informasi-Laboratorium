package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ScanActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;

    LinearLayout kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        kembali = (LinearLayout) findViewById(R.id.kembali);

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
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // Menambahkan flag FLAG_ACTIVITY_SINGLE_TOP

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);

        super.onNewIntent(intent);

        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            if (tag == null) {
                return;
            }

            // Membaca data dari NFC tag
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                try {
                    ndef.connect();
                    NdefMessage ndefMessage = ndef.getNdefMessage();
                    if (ndefMessage != null && ndefMessage.getRecords().length > 0) {
                        NdefRecord ndefRecord = ndefMessage.getRecords()[0];
                        String payload = new String(ndefRecord.getPayload(), StandardCharsets.UTF_8);

                        // Melakukan pengecekan jika data pada NFC tag adalah plain/text "telematika"
                        if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN &&
                                Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT) &&
                                payload.equals("telematika")) {
                            // membuka activity Menu
                            // membuka activity Menu
                            Intent menuIntent = new Intent(this, MenuActivity.class);
                            startActivity(menuIntent);
                            finish(); // Menambahkan perintah finish() untuk menghentikan pemrosesan lebih lanjut

                        } else {
                            Toast.makeText(this, "Tag tidak mengandung data 'telematika'", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Tag tidak mengandung data NDEF", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        ndef.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Toast.makeText(this, "Tag tidak mengandung NDEF data", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        nfcAdapter.disableForegroundDispatch(this);
    }




}