package com.example.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.driver.apihelper.SharedPrefManager;

public class splash extends AppCompatActivity {
    private int waktu_loading=2500;
    TextView user;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPrefManager = new SharedPrefManager(splash.this);

        user = (TextView) findViewById(R.id.t_user);
        user.setText(sharedPrefManager.getSPNama(SharedPrefManager.SP_NAMA,""));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home=new Intent(splash.this, beranda.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);
    }
}
