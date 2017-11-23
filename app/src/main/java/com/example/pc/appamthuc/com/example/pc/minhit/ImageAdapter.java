package com.example.pc.appamthuc.com.example.pc.minhit;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.pc.appamthuc.MainActivity;
import com.example.pc.appamthuc.R;

import java.util.List;

/**
 * Created by PC on 10/17/2017.
 */

public class ImageAdapter extends ArrayAdapter<Integer> {
    @NonNull
    MainActivity context;
    @LayoutRes int resource;
    @NonNull List<Integer> objects;
    public ImageAdapter(@NonNull MainActivity context, @LayoutRes int resource, @NonNull List<Integer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        ImageView imgthucUong;
        imgthucUong = row.findViewById(R.id.imgthucUong);
        imgthucUong.setImageResource(this.objects.get(position));
        return row;
    }
}
