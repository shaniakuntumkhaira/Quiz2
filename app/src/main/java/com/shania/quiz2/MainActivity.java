package com.shania.quiz2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupType, radioGroupTambahan;
    private EditText editTextWaktu;
    private Button buttonPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupType = findViewById(R.id.rgType);
        radioGroupTambahan = findViewById(R.id.rgTambahan);
        editTextWaktu = findViewById(R.id.etWaktu);
        buttonPesan = findViewById(R.id.btnPesan);

        buttonPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedTypeId = radioGroupType.getCheckedRadioButtonId();
                int selectedTambahanId = radioGroupTambahan.getCheckedRadioButtonId();

                if (selectedTypeId == -1 || selectedTambahanId == -1) {
                    Toast.makeText(MainActivity.this, "Pilih jenis PS dan tambahan terlebih dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton radioButtonType = findViewById(selectedTypeId);
                RadioButton radioButtonTambahan = findViewById(selectedTambahanId);

                int waktu = Integer.parseInt(editTextWaktu.getText().toString());

                int biayaType = hitungBiayaType(radioButtonType.getText().toString(), waktu);
                int biayaTambahan = hitungBiayaTambahan(radioButtonTambahan.getText().toString());

                int totalBiaya = biayaType + biayaTambahan;

                // Membuat Intent untuk memulai InvoiceActivity
                Intent intent = new Intent(MainActivity.this, InvoiceActivity.class);
                intent.putExtra("type", radioButtonType.getText().toString());
                intent.putExtra("tambahan", radioButtonTambahan.getText().toString());
                intent.putExtra("waktu", waktu);
                intent.putExtra("totalBiaya", totalBiaya);
                startActivity(intent);
            }
        });

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
}
