package com.example.driver;

import com.google.gson.annotations.SerializedName;

public class list_jadwal {
    @SerializedName("id_jadwal") private String id_jadwal;
    @SerializedName("id_pemesanan") private String id_pemesanan;
    @SerializedName("status") private String status;
    @SerializedName("nama_pemesan") private String nama_pemesan;
    @SerializedName("no_telepon") private String no_telepon;
    @SerializedName("daerah_asal") private String daerah_asal;
    @SerializedName("latitude") private String latitude;
    @SerializedName("longitude") private String longitude;
    @SerializedName("tanggal_pesan") private String tanggal_pesan;
    @SerializedName("tanggal_keberangkatan") private String tanggal_keberangkatan;
    @SerializedName("id_armada") private String id_armada;
    @SerializedName("id_paket") private String id_paket;
    @SerializedName("nama_armada") private String nama_armada;
    @SerializedName("nama_paket") private String nama_paket;

    public String getId() {
        return id_jadwal;
    }
    public String getId_pemesanan() {
        return id_pemesanan;
    }
    public String getStatus() {
        return status;
    }
    public String getNama_pemesan() {
        return nama_pemesan;
    }
    public String getNo_telepon() {
        return no_telepon;
    }
        public String getDaerah_asal() {
        return daerah_asal;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public String getTanggal_pesan() {
        return tanggal_pesan;
    }
    public String getTanggal_keberangkatan() {
        return tanggal_keberangkatan;
    }
        public String getId_armada() {
        return id_armada;
    }
    public String getId_paket() {
        return id_paket;
    }
    public String getNama_armada() {
        return nama_armada;
    }
    public String getNama_paket() {
        return nama_paket;
    }
}
