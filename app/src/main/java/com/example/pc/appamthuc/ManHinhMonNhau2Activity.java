package com.example.pc.appamthuc;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ManHinhMonNhau2Activity extends AppCompatActivity {
    ImageView imgHinh;
    TextView txtText, txtTenTieuDe;
    ThucUong thucUong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_thuc_uong2);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        addControls();
        showDaTa();

        addEvents();

    }

    private void showDaTa() {
        Intent intent = getIntent();
        thucUong =new ThucUong();
        thucUong.setId(intent.getIntExtra("TUid",-1));
        thucUong.setTen(intent.getStringExtra("TUten"));
        thucUong.setNote(intent.getStringExtra("TUnote"));
        thucUong.setUse(intent.getStringExtra("TUuse"));


        MainActivity.database = openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = MainActivity.database.query("CacMonNhau", null,
                "Note=?", new String[]{thucUong.getNote()},
                null,null,null);

        while (cursor.moveToNext()) {
            byte[] bytes = cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            thucUong.setTen(cursor.getString(1));
            thucUong.setId(cursor.getInt(0));
            thucUong.setAnh(bitmap);
        }
        cursor.close();
        txtTenTieuDe.setText(thucUong.getTen());
        imgHinh.setImageBitmap(thucUong.getAnh());
        txtText.setText(thucUong.getUse());

    }

    private void addEvents() {

    }

    private void addControls() {
        txtTenTieuDe = (TextView) findViewById(R.id.txtTenTieuDe);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        txtText = (TextView) findViewById(R.id.txtText);


    }
}
