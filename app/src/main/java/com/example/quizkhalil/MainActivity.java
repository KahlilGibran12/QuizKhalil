package com.example.quizkhalil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etWaktu;

    RadioGroup rgPs, rgMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etWaktu = findViewById(R.id.etwaktu);
        Button btnPesan = findViewById(R.id.btnPesan);
        rgPs = findViewById(R.id.rgPs);
        rgMakanan = findViewById(R.id.rgMakanan);

        btnPesan.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPesan) {
            ProsesPesan();
        }
    }

    private void ProsesPesan() {
        String waktu = etWaktu.getText().toString().trim();

        if (waktu.isEmpty()) {
            Toast.makeText(this, "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedPsId = rgPs.getCheckedRadioButtonId();
        double hargaPs = 0;
        if (selectedPsId != -1) {
            RadioButton radioButton = findViewById(selectedPsId);
            String psText = radioButton.getText().toString();
            hargaPs = getHargaPs(psText);
        } else {
            Toast.makeText(this, "Mohon pilih jenis PS", Toast.LENGTH_SHORT).show();
            return;
        }

        double hargaMakanan = 0;
        int selectedMakananId = rgMakanan.getCheckedRadioButtonId();
        if (selectedMakananId != -1) {
            RadioButton radioButton = findViewById(selectedMakananId);
            String makananText = radioButton.getText().toString();
            hargaMakanan = getHargaMakanan(makananText);
        }

        double totalHarga = (hargaPs * Integer.parseInt(waktu)) + hargaMakanan;

        // Intent untuk beralih ke DetailActivity dan mengirimkan data harga
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("TOTAL_HARGA", totalHarga);
        intent.putExtra("WAKTU", Integer.parseInt(waktu));
        intent.putExtra("HARGA_PS", hargaPs);
        intent.putExtra("HARGA_MAKANAN", hargaMakanan);
        intent.putExtra("TYPE_PS", getJenisPs(selectedPsId));
        intent.putExtra("MAKANAN", getMakanan(selectedMakananId));
        startActivity(intent);
    }

    private double getHargaPs(String jenisPs) {
        switch (jenisPs) {
            case "PS5":
                return 10000;
            case "PS4":
                return 8000;
            case "PS3":
                return 5000;
            case "PSVR":
                return 2000;
            default:
                return 0;
        }
    }

    private double getHargaMakanan(String jenisMakanan) {
        switch (jenisMakanan) {
            case "Indomie":
                return 7000;
            case "Mie Ayam":
                return 10000;
            case "Somay":
                return 5000;
            default:
                return 0;
        }
    }

    private String getJenisPs(int selectedPsId) {
        RadioButton radioButton = findViewById(selectedPsId);
        return radioButton.getText().toString();
    }

    private String getMakanan(int selectedMakananId) {
        if (selectedMakananId != -1) {
            RadioButton radioButton = findViewById(selectedMakananId);
            return radioButton.getText().toString();
        }
        return ""; // Jika tidak ada makanan yang dipilih, kembalikan string kosong
    }
}
