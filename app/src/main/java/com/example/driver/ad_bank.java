package com.example.driver;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ad_bank extends RecyclerView.Adapter<ad_bank.ViewHolder>{

    View v;
    List<list_bank> daftarbank;
    Context context;


    public ad_bank(List<list_bank> contacts, Context context) {
        this.daftarbank = contacts;
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
        holder.nama.setText(daftarbank.get(position).getNama_bank());

//        String fileName = daftarbank.get(position).getLogo_bank();
////        String fullUrlImage = "https://cobabioskop.000webhostapp.com/upload/gbrfilm/" + fileName;
//
//        Picasso.with(holder.itemView.getContext())
//                .load(load.foto(fileName))
//                .into(holder.logo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //memanggil detailfilm
                String id = daftarbank.get(position).getId();
                String nama_bank = daftarbank.get(position).getNama_bank();
                String logo_bank = daftarbank.get(position).getLogo_bank();
                String no_rekening = daftarbank.get(position).getNo_rekening();
                String nama_pemilik = daftarbank.get(position).getNama_pemilik();


                Intent intent = new Intent(v.getContext(), detail_jemput.class);
                Intent mntent = ((beranda)v.getContext()).getIntent();
                intent.putExtra(Constant.KEY_ID_BANK, id);
                intent.putExtra(Constant.KEY_BANK, nama_bank);
                intent.putExtra(Constant.KEY_LOGO, logo_bank);
                intent.putExtra(Constant.KEY_REK, no_rekening);
                intent.putExtra(Constant.KEY_PEMILIK, nama_pemilik);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarbank.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
        }
    }
}