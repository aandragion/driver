package com.example.driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.driver.apihelper.SharedPrefManager;
import com.example.driver.apihelper.api.BaseApiService;
import com.example.driver.apihelper.api.UtilsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class beranda extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    TextView user;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ImageButton btnLogout;
    BaseApiService apiInterface;
    List<list_bank> listbank;
    ad_bank adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        sharedPrefManager = new SharedPrefManager(beranda.this);

        user = (TextView) findViewById(R.id.t_userb);
        user.setText(sharedPrefManager.getSPNama(SharedPrefManager.SP_NAMA,""));

                btnLogout    = (ImageButton) findViewById(R.id.b_logout);
                btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(beranda.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });


        recyclerView = findViewById(R.id.recycle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchContact("bank");
    }

    private void fetchContact(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<List<list_bank>> call = apiInterface.getBank(type);
        call.enqueue(new Callback<List<list_bank>>() {
            @Override
            public void onResponse(Call<List<list_bank>> call, Response<List<list_bank>> response) {

                listbank = response.body();
                adapter = new ad_bank(listbank, beranda.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<list_bank>> call, Throwable t) {
                Toast.makeText(beranda.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
