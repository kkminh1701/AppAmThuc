package com.example.pc.appamthuc;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ManHinhCachLamBanhActivity extends AppCompatActivity {
    ListView lvCachLamBanh;
    ArrayList<ThucUong> dsCachLamBanh;
    AdapterThucUong adapterCachLamBanh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cach_lam_banh);
        addControls();
        addEvents();

    }

    private void addEvents() {
        lvCachLamBanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThucUong thucUong = dsCachLamBanh.get(position);
                Intent intent = new Intent(ManHinhCachLamBanhActivity.this, ManHinhCachLamBanh2Activity.class);

                intent.putExtra("TUnote", thucUong.getNote());
                intent.putExtra("TUuse", thucUong.getUse());


                startActivity(intent);

            }
        });
    }

    private void addControls() {
        lvCachLamBanh = (ListView) findViewById(R.id.lvCachLamBanh);
        dsCachLamBanh = new ArrayList<>();
        adapterCachLamBanh = new AdapterThucUong(ManHinhCachLamBanhActivity.this, R.layout.itemsthucuong, dsCachLamBanh);
        lvCachLamBanh.setAdapter(adapterCachLamBanh);
        showAllListView();


    }

    private void showAllListView() {
        MainActivity.database = openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = MainActivity.database.rawQuery("SELECT * FROM CachLamBanh",null);
        dsCachLamBanh.clear();
        while (cursor.moveToNext()){
            ThucUong thucUong = new ThucUong();
            byte[] bytes =  cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            thucUong.setAnh(bitmap);
            thucUong.setTen(cursor.getString(1));
            thucUong.setNote(cursor.getString(2));
            thucUong.setUse(cursor.getString(4));

            dsCachLamBanh.add(thucUong);

        }
        cursor.close();
        adapterCachLamBanh.notifyDataSetChanged();
    }


}
