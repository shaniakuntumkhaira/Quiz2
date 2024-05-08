package com.shania.quiz2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;
import java.util.Locale;

public class InvoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        // Mendapatkan data yang dipilih pengguna dari MainActivity
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String tambahan = intent.getStringExtra("tambahan");
        int waktu = intent.getIntExtra("waktu", 0);
        int totalBiaya = intent.getIntExtra("totalBiaya", 0);

        // Menampilkan data di TextView pada layout InvoiceActivity
        TextView tvType = findViewById(R.id.tvType2);
        TextView tvTambahan = findViewById(R.id.tvTambahan2);
        TextView tvWaktu = findViewById(R.id.tvWaktu2);
        TextView tvTotalBiaya = findViewById(R.id.tvTotalBiaya);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        formatter.setMaximumFractionDigits(0);

        // Menampilkan informasi tentang jenis PS dan biayanya
        String biayaTypeText = "Type : " + type + "(" + formatter.format(hargaAwalPS(type)) + ")";
        tvType.setText(biayaTypeText);

        // Menampilkan informasi tentang tambahan dan biayanya
        String tambahanText = tambahan + " : " + formatter.format(hitungBiayaTambahan(tambahan));
        tvTambahan.setText(tambahanText);

        // Menampilkan informasi tentang waktu
        tvWaktu.setText("Waktu : " + waktu + " jam");

        // Menampilkan total biaya
        tvTotalBiaya.setText("Total Biaya : " + formatter.format(totalBiaya));
    }

    private int hitungBiayaType(String type, int waktu) {
        switch (type) {
            case "PS5":
                return 10000 * waktu;
            case "PS4":
                return 8000 * waktu;
            case "PS3":
                return 5000 * waktu;
            case "PSVR":
                return 20000 * waktu;
            default:
                return 0;
        }
    }

    private int hitungBiayaTambahan(String tambahan) {
        switch (tambahan) {
            case "Indomie":
                return 7000;
            case "Mie Ayam":
                return 10000;
            case "Siomay":
                return 5000;
            default:
                return 0;
        }
    }

    private int hargaAwalPS(String type) {
        switch (type) {
            case "PS5":
                return 10000;
            case "PS4":
                return 8000;
            case "PS3":
                return 5000;
            case "PSVR":
                return 20000;
            default:
                return 0;
        }
    }
}
