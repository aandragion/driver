package com.example.driver.apihelper.api;

public class UtilsApi {

    // 10.0.2.2 ini adalah localhost.https://cobabioskop.000webhostapp.com/ https://travelkuku.000webhostapp.com/ http://192.168.8.109/admin-api/ http://192.168.8.109/travelku-api/
    public static final String BASE_URL_API = "https://travelku2020.000webhostapp.com";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
