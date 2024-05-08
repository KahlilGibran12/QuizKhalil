package com.example.quizkhalil;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Mengambil nilai total harga dari intent
        double totalHarga = getIntent().getDoubleExtra("TOTAL_HARGA", 0);
        int waktuPemesanan = getIntent().getIntExtra("WAKTU", 0);
        double hargaPs = getIntent().getDoubleExtra("HARGA_PS", 0);
        double hargaMakanan = getIntent().getDoubleExtra("HARGA_MAKANAN", 0);
        String typePs = getIntent().getStringExtra("TYPE_PS");
        String makanan = getIntent().getStringExtra("MAKANAN");

        // Format harga ke format mata uang Rupiah
        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        // Menampilkan nilai total harga di TextView dalam format Rupiah
        TextView tvTotalHarga = findViewById(R.id.tvTotal);
        tvTotalHarga.setText("Total Harga: " + rupiahFormat.format(totalHarga));

        // Menampilkan harga PS di TextView dalam format Rupiah
        TextView tvHargaPs = findViewById(R.id.tvTypePs);
        tvHargaPs.setText("Type : "  +  typePs  +  " { " + rupiahFormat.format(hargaPs) + " } ");

        // Menampilkan harga makanan di TextView dalam format Rupiah
        TextView tvHargaMakanan = findViewById(R.id.tvHargaMakanan);
        tvHargaMakanan.setText(makanan + ": " + rupiahFormat.format(hargaMakanan));

        // Menampilkan waktu pemesanan di TextView
        TextView tvWaktu = findViewById(R.id.tvWaktu);
        tvWaktu.setText("Waktu Pemesanan: " + String.valueOf(waktuPemesanan) + " jam");
    }
}


