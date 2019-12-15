package com.example.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.driver.apihelper.SharedPrefManager;
import com.example.driver.apihelper.api.BaseApiService;
import com.example.driver.apihelper.api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText etUser, etPassword;

    Button btnLogin;
    Button btnRegister;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;
    boolean pwd_status = true;

    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()) {
            startActivity(new Intent(MainActivity.this, splash.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }


//        EdUsername = (EditText) findViewById(R.id.t_user);
        etUser = (EditText) findViewById(R.id.e_username);
        etPassword = (EditText) findViewById(R.id.e_pass);
        btnLogin = (Button) findViewById(R.id.b_login);
//

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.b_login:
                        if (validasi())
                            requestLogin();
                        break;
                }
            }
        });
    }
    private boolean validasi() {
        return (!validate.cek(etUser) && !validate.cek(etPassword)) ? true : false;
    }

    private void requestLogin() {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        mApiService.loginRequest(etUser.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")) {
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                    String id_user = jsonRESULTS.getJSONObject("id_user").getString("id_user");
                                    String username = jsonRESULTS.getJSONObject("user").getString("username");
                                    String email = jsonRESULTS.getJSONObject("user").getString("email");
                                    String alamat = jsonRESULTS.getJSONObject("user").getString("alamat");
                                    String no_tlp = jsonRESULTS.getJSONObject("user").getString("no_tlp");
                                    String pass = jsonRESULTS.getJSONObject("user").getString("password");
                                    String photo = jsonRESULTS.getJSONObject("user").getString("photo");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, id_user);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, username);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_ALAMAT, alamat);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NO_TLP, no_tlp);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_PASS, pass);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_PHOTO, photo);
                                    // Shared Pref ini berfungsi untuk menjadi trigger session login
                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                    startActivity(new Intent(mContext, splash.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }

}
