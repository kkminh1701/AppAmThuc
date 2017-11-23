package com.example.pc.appamthuc;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class AdapterThucUong extends ArrayAdapter<ThucUong>{
    Activity context;
    int resource;
    @NonNull List<ThucUong> objects;

    public AdapterThucUong(@NonNull Activity context, int resource, @NonNull List<ThucUong> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);

        ImageView imgHinh = row.findViewById(R.id.imgHinh1);
        TextView txtName = row.findViewById(R.id.txtName);
        TextView txtNote = row.findViewById(R.id.txtNote);


        ThucUong thucUong = this.objects.get(position);
        txtName.setText(thucUong.getTen());
        txtNote.setText(thucUong.getNote());
        imgHinh.setImageBitmap(thucUong.getAnh());

        return row;
    }
}
