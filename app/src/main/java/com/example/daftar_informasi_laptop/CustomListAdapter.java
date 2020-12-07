package com.example.daftar_informasi_laptop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Laptop> movieItems;

    public CustomListAdapter(Activity activity, List<Laptop> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }
    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return  movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_list, null);
        ImageView image = (ImageView) convertView.findViewById(R.id.iconid);
        TextView nama = (TextView) convertView.findViewById(R.id.text_nama);
        TextView tipe = (TextView) convertView.findViewById(R.id.text_tipe);
        TextView warna = (TextView) convertView.findViewById(R.id.text_warna);
        TextView harga = (TextView) convertView.findViewById(R.id.text_harga);
        Laptop m = movieItems.get(position);
        image.setImageBitmap(ByteArrayToBitmap(m.get_image()));
        nama.setText("Nama              : "+ m.get_nama());
        tipe.setText("Tipe              : "+ m.get_tipe());
        warna.setText("Warna           : "+ m.get_warna());
        harga.setText("Harga            : "+ m.get_harga());
        return convertView;
    }
    public Bitmap ByteArrayToBitmap(byte[] byteArray) {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }

}

