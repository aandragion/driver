package com.example.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class detail_jemput extends AppCompatActivity {
    private String mId, mnama, mlogo, mjlm, mrek, mpemilik, tomorrowAsString, todayString;
    TextView nama, alamat, tujuan, harga;
    ImageButton kembali;
    Button jemput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jemput);

        Intent intent = getIntent();
        mId         = intent.getStringExtra(Constant.KEY_ID_BANK);
        mnama       = intent.getStringExtra(Constant.KEY_BANK);
        mlogo       = intent.getStringExtra(Constant.KEY_LOGO);
        mrek        = intent.getStringExtra(Constant.KEY_REK);
        mpemilik    = intent.getStringExtra(Constant.KEY_PEMILIK);

        nama    = (TextView) findViewById(R.id.t_nama);
        alamat  = (TextView) findViewById(R.id.t_alamat);
        tujuan  = (TextView) findViewById(R.id.t_tujuan);
        harga   = (TextView) findViewById(R.id.t_harga);
        kembali   = (ImageButton) findViewById(R.id.b_kembali);
        jemput   = (Button) findViewById(R.id.b_jemput);

        nama.setText(mnama);
        alamat.setText(mrek);
        tujuan.setText(mpemilik);
        harga.setText(mId);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(detail_jemput.this, beranda.class));
            }
        });
    }
}
