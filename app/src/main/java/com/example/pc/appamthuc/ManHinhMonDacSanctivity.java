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

public class ManHinhMonDacSanctivity extends AppCompatActivity {
    ListView lvMonDacSan;
    ArrayList<ThucUong> dsMonDacSan;
    AdapterThucUong adapterMonDacSan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_mon_dac_sanctivity);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvMonDacSan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThucUong thucUong = dsMonDacSan.get(position);
                Intent intent = new Intent(ManHinhMonDacSanctivity.this, ManHinhMonDacSan2Activity.class);

                intent.putExtra("TUnote", thucUong.getNote());
                intent.putExtra("TUuse", thucUong.getUse());


                startActivity(intent);

            }
        });
    }

    private void addControls() {
        lvMonDacSan = (ListView) findViewById(R.id.lvMonDacSan);
        dsMonDacSan = new ArrayList<>();
        adapterMonDacSan = new AdapterThucUong(ManHinhMonDacSanctivity.this, R.layout.itemsthucuong, dsMonDacSan);
        lvMonDacSan.setAdapter(adapterMonDacSan);
        showAllOnListView();
    }

    private void showAllOnListView() {
        MainActivity.database = openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = MainActivity.database.rawQuery("SELECT * FROM MonDacSan",null);
        dsMonDacSan.clear();
        while (cursor.moveToNext()){
            ThucUong thucUong = new ThucUong();
            byte[] bytes = cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            thucUong.setAnh(bitmap);
            thucUong.setTen(cursor.getString(1));
            thucUong.setNote(cursor.getString(2));
            thucUong.setUse(cursor.getString(4));

            dsMonDacSan.add(thucUong);

        }
        cursor.close();
        adapterMonDacSan.notifyDataSetChanged();

    }
}
