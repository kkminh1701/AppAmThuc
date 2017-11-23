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

public class ManHinhMonChayActivity extends AppCompatActivity {
    ListView lvMonChay;
    ArrayList<ThucUong> dsMonChay;
    AdapterThucUong adapterMonChay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_mon_chay);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvMonChay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThucUong thucUong = dsMonChay.get(position);
                Intent intent = new Intent(ManHinhMonChayActivity.this, ManHinhMonChay2Activity.class);

                intent.putExtra("TUnote", thucUong.getNote());
                intent.putExtra("TUuse", thucUong.getUse());


                startActivity(intent);

            }
        });
    }

    private void addControls() {
        lvMonChay = (ListView) findViewById(R.id.lvMonChay);
        dsMonChay = new ArrayList<>();
        adapterMonChay = new AdapterThucUong(ManHinhMonChayActivity.this, R.layout.itemsthucuong, dsMonChay);
        lvMonChay.setAdapter(adapterMonChay);
        showAllOnListView();


    }

    private void showAllOnListView() {
        MainActivity.database = openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = MainActivity.database.rawQuery("SELECT * FROM MonChay",null);
        dsMonChay.clear();
        while (cursor.moveToNext()){
            ThucUong thucUong = new ThucUong();
            byte[] bytes = cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            thucUong.setAnh(bitmap);
            thucUong.setTen(cursor.getString(1));
            thucUong.setNote(cursor.getString(2));
            thucUong.setUse(cursor.getString(4));

            dsMonChay.add(thucUong);

        }
        cursor.close();
        adapterMonChay.notifyDataSetChanged();

    }
}
