package com.example.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.driver.apihelper.api.BaseApiService;
import com.example.driver.apihelper.api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detail_jemput extends AppCompatActivity {
    private String mId,status, status_selesai, mnama, masal, mjlm, m_tgl_pesan, berangkat, tomorrowAsString, todayString, marmada, mpaket, mstatus;
    TextView nama, alamat, armada, paket, tgl_berangkat, tgl_pesan;
    ImageButton kembali;
    Button jemput, selesai;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jemput);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        Intent intent = getIntent();
        mId         = intent.getStringExtra(Constant.KEY_ID_JADWAL);
        mnama       = intent.getStringExtra(Constant.KEY_N_PEMESAN);
        masal       = intent.getStringExtra(Constant.KEY_ASAL);
        m_tgl_pesan = intent.getStringExtra(Constant.KEY_TGL_PESAN);
        berangkat   = intent.getStringExtra(Constant.KEY_TGL_BERANGKAT);
        marmada     = intent.getStringExtra(Constant.KEY_ARMADA);
        mpaket      = intent.getStringExtra(Constant.KEY_PAKET);
        mstatus     = intent.getStringExtra(Constant.KEY_STATUS);
        status      = "2";
        status_selesai= "3";

        nama            = (TextView) findViewById(R.id.t_nama);
        alamat          = (TextView) findViewById(R.id.t_alamat);
        armada          = (TextView) findViewById(R.id.t_armada);
        paket           = (TextView) findViewById(R.id.t_paket);
        tgl_berangkat   = (TextView) findViewById(R.id.tgl_berangkat);
        tgl_pesan       = (TextView) findViewById(R.id.tgl_pesan);
        kembali         = (ImageButton) findViewById(R.id.b_kembali);
        jemput          = (Button) findViewById(R.id.b_jemput);
        selesai          = (Button) findViewById(R.id.b_selesai);
        nama.setText(mnama);
        alamat.setText(masal);
        armada.setText(marmada);
        paket.setText(mpaket);
        tgl_pesan.setText(m_tgl_pesan);
        tgl_berangkat.setText(berangkat);

        if( mstatus.equals( "1" )  ) {
            jemput.setVisibility(View.VISIBLE);
        }
        if( mstatus.equals( "2" )  ) {
            selesai.setVisibility(View.VISIBLE);
        }
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(detail_jemput.this, beranda.class));
            }
        });

        jemput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestjemput();
            }
        });
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestselesai();
            }
        });
    }
    private void requestjemput() {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        mApiService.jemputRequest(
              status,
                mId
        ).enqueue(new Callback<ResponseBody>() {
                      @Override
                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                          if (response.isSuccessful()) {
                              Log.i("debug", "onResponse: BERHASIL");
                              loading.dismiss();
                              try {
                                  JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                  if (jsonRESULTS.getString("error").equals("false")) {
//                                      Toast.makeText(mContext, "TIKET BERHASIL DIPESAN", Toast.LENGTH_SHORT).show();
                                      startActivity(new Intent(mContext, beranda.class));
                                  } else {
                                      String error_message = jsonRESULTS.getString("error_msg");
                                      Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                  }
                              } catch (JSONException e) {
                                  e.printStackTrace();
                              } catch (IOException e) {
                                  e.printStackTrace();
                              }
                          } else {
                              Log.i("debug", "onResponse: GA BERHASIL");
                              loading.dismiss();
                          }

                      }

                      @Override
                      public void onFailure(Call<ResponseBody> call, Throwable t) {
                          Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                          Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                      }
                  }
        );
    }
    private void requestselesai() {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        mApiService.jemputRequest(
                status_selesai,
                mId
        ).enqueue(new Callback<ResponseBody>() {
                      @Override
                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                          if (response.isSuccessful()) {
                              Log.i("debug", "onResponse: BERHASIL");
                              loading.dismiss();
                              try {
                                  JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                  if (jsonRESULTS.getString("error").equals("false")) {
//                                      Toast.makeText(mContext, "TIKET BERHASIL DIPESAN", Toast.LENGTH_SHORT).show();
                                      startActivity(new Intent(mContext, beranda.class));
                                  } else {
                                      String error_message = jsonRESULTS.getString("error_msg");
                                      Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                  }
                              } catch (JSONException e) {
                                  e.printStackTrace();
                              } catch (IOException e) {
                                  e.printStackTrace();
                              }
                          } else {
                              Log.i("debug", "onResponse: GA BERHASIL");
                              loading.dismiss();
                          }

                      }

                      @Override
                      public void onFailure(Call<ResponseBody> call, Throwable t) {
                          Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                          Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                      }
                  }
        );
    }
}
