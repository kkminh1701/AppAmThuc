package com.example.pc.appamthuc;



import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class ManHinhThucUongActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    ListView lvThucUong;
    ArrayList<ThucUong> dsThucUong;
    AdapterThucUong adapterThucUong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_thuc_uong);
        addControls();
        addEvents();
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_action, menu);
//        MenuItem item = menu.findItem(R.id.menuSearch);
//        SearchView a = (SearchView) item.getActionView();
//        a.setOnQueryTextListener(this);
//
//        return true;
//    }


    private void addControls() {
        lvThucUong = (ListView) findViewById(R.id.lvThucUong);
        dsThucUong = new ArrayList<>();
        adapterThucUong = new AdapterThucUong(ManHinhThucUongActivity.this, R.layout.itemsthucuong, dsThucUong);
        lvThucUong.setAdapter(adapterThucUong);
        showAllOnListView();
    }

    private void showAllOnListView() {
        //buoc 1 mo co so du lieu
        MainActivity.database = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = MainActivity.database.rawQuery("SELECT * FROM ThucUong", null);
        dsThucUong.clear();
        while (cursor.moveToNext()) {
            ThucUong thucUong = new ThucUong();
            byte[] bytes = cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            thucUong.setAnh(bitmap);
            thucUong.setTen(cursor.getString(1));
            thucUong.setNote(cursor.getString(2));
            thucUong.setUse(cursor.getString(4));

            dsThucUong.add(thucUong);

        }
        cursor.close();
        adapterThucUong.notifyDataSetChanged();

    }

    private void addEvents() {
        lvThucUong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThucUong thucUong = dsThucUong.get(position);
                Intent intent = new Intent(ManHinhThucUongActivity.this, ManHinhThucUong2Activity.class);

                intent.putExtra("TUnote", thucUong.getNote());
                intent.putExtra("TUuse", thucUong.getUse());


                startActivity(intent);

            }
        });
    }

    private void xuLyClick(int position) {


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       


        return true;
    }
}
