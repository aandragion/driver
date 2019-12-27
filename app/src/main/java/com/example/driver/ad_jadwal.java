package com.example.driver;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ad_jadwal extends RecyclerView.Adapter<ad_jadwal.ViewHolder>{

    View v;
    List<list_jadwal> daftarjadwal;
    Context context;


    public ad_jadwal(List<list_jadwal> contacts, Context context) {
        this.daftarjadwal = contacts;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jemput, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.nama.setText(daftarjadwal.get(position).getDaerah_asal());

//        String fileName = daftarjadwal.get(position).getLogo_bank();
////        String fullUrlImage = "https://cobabioskop.000webhostapp.com/upload/gbrfilm/" + fileName;
//
//        Picasso.with(holder.itemView.getContext())
//                .load(load.foto(fileName))
//                .into(holder.logo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //memanggil detailfilm
                String id = daftarjadwal.get(position).getId();
                String n_pemesan = daftarjadwal.get(position).getNama_pemesan();
                String daerah_asal = daftarjadwal.get(position).getDaerah_asal();
                String tgl_pesan = daftarjadwal.get(position).getTanggal_pesan();
                String tgl_berangkat = daftarjadwal.get(position).getTanggal_keberangkatan();
                String armada = daftarjadwal.get(position).getNama_armada();
                String paket = daftarjadwal.get(position).getNama_paket();
                String status = daftarjadwal.get(position).getStatus();

                Intent intent = new Intent(v.getContext(), detail_jemput.class);
                Intent mntent = ((beranda)v.getContext()).getIntent();
                intent.putExtra(Constant.KEY_ID_JADWAL, id);
                intent.putExtra(Constant.KEY_N_PEMESAN, n_pemesan);
                intent.putExtra(Constant.KEY_ASAL, daerah_asal);
                intent.putExtra(Constant.KEY_TGL_PESAN, tgl_pesan);
                intent.putExtra(Constant.KEY_TGL_BERANGKAT, tgl_berangkat);
                intent.putExtra(Constant.KEY_ARMADA, armada);
                intent.putExtra(Constant.KEY_PAKET, paket);
                intent.putExtra(Constant.KEY_STATUS, status);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarjadwal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
        }
    }
}