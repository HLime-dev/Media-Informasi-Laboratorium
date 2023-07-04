package com.telematika.info;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;
import android.widget.Toast;

public class NfcReceiver implements NfcAdapter.ReaderCallback {
    private ScanActivity scanActivity;

    public NfcReceiver(ScanActivity activity) {
        scanActivity = activity;
    }

    @Override
    public void onTagDiscovered(android.nfc.Tag tag) {
        // Membaca data dari tag NDEF
        Ndef ndef = Ndef.get(tag);
        if (ndef != null) {
            try {
                ndef.connect();
                NdefMessage ndefMessage = ndef.getNdefMessage();
                NdefRecord[] records = ndefMessage.getRecords();
                if (records != null && records.length > 0) {
                    NdefRecord ndefRecord = records[0];
                    String tagData = new String(ndefRecord.getPayload());

                    // Memeriksa tag yang sesuai
                  //  if (tagData.equals("telematika")) {
                    //    scanActivity.startNewActivity(); // Memulai aktivitas baru
                  //  } else {
                   //     scanActivity.showToast("Tag yang tidak sesuai!"); // Menampilkan toast
                   // }
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
        }
    }
}

