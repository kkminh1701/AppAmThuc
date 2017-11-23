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

public class ManHinhMonNhauActivity extends AppCompatActivity {
    ListView lvMonNhau;
    ArrayList<ThucUong> dsMonNhau;
    AdapterThucUong adapterMonNhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_mon_nhau);
        addControls();
        addEvents();

    }

    private void addEvents() {
        lvMonNhau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThucUong thucUong = dsMonNhau.get(position);
                Intent intent = new Intent(ManHinhMonNhauActivity.this, ManHinhMonNhau2Activity.class);

                intent.putExtra("TUnote", thucUong.getNote());
                intent.putExtra("TUuse", thucUong.getUse());


                startActivity(intent);

            }
        });
    }

    private void addControls() {
        lvMonNhau = (ListView) findViewById(R.id.lvMonNhau);
        dsMonNhau = new ArrayList<>();
        adapterMonNhau = new AdapterThucUong(ManHinhMonNhauActivity.this, R.layout.itemsthucuong, dsMonNhau);
        lvMonNhau.setAdapter(adapterMonNhau);
        showAllOnListView();
    }

    private void showAllOnListView() {
        MainActivity.database = openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = MainActivity.database.rawQuery("SELECT * FROM CacMonNhau",null);
        dsMonNhau.clear();
        while (cursor.moveToNext()){
            ThucUong thucUong = new ThucUong();
            byte[] bytes = cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            thucUong.setAnh(bitmap);
            thucUong.setTen(cursor.getString(1));
            thucUong.setNote(cursor.getString(2));
            thucUong.setUse(cursor.getString(4));

            dsMonNhau.add(thucUong);
        }
        cursor.close();
        adapterMonNhau.notifyDataSetChanged();
    }
}
