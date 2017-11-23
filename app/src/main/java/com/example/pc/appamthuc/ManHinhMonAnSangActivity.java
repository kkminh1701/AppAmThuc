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

public class ManHinhMonAnSangActivity extends AppCompatActivity {
    ListView lvMonAnSang;
    ArrayList<ThucUong> dsMonAnSang;
    AdapterThucUong adapterMonAnSang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_mon_an_sang);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvMonAnSang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThucUong thucUong = dsMonAnSang.get(position);
                Intent intent = new Intent(ManHinhMonAnSangActivity.this, ManHinhMonAnSang2Activity.class);

                intent.putExtra("TUnote", thucUong.getNote());
                intent.putExtra("TUuse", thucUong.getUse());


                startActivity(intent);

            }
        });
    }

    private void addControls() {
        lvMonAnSang = (ListView) findViewById(R.id.lvMonAnSang);
        dsMonAnSang = new ArrayList<>();
        adapterMonAnSang = new AdapterThucUong(ManHinhMonAnSangActivity.this, R.layout.itemsthucuong, dsMonAnSang);
        lvMonAnSang.setAdapter(adapterMonAnSang);
        showAllOnListView();
    }

    private void showAllOnListView() {
        MainActivity.database = openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = MainActivity.database.rawQuery("SELECT * FROM MonAnSang",null);
        dsMonAnSang.clear();
        while (cursor.moveToNext()){
            ThucUong thucUong = new ThucUong();
            byte[] bytes = cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            thucUong.setAnh(bitmap);
            thucUong.setTen(cursor.getString(1));
            thucUong.setNote(cursor.getString(2));
            thucUong.setUse(cursor.getString(4));

            dsMonAnSang.add(thucUong);

        }
        cursor.close();
        adapterMonAnSang.notifyDataSetChanged();

    }

}
