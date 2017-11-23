package com.example.pc.appamthuc;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.pc.appamthuc.com.example.pc.minhit.ImageAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    public static String DATABASE_NAME ="MonAn.sqlite";
    public static String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    GridView gvHinh;
    ArrayList<Integer> dsMonAn;
    ImageAdapter imageAdapter;
    ImageView imgmonNgonMoiNgay;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);
        xuLySaoChepCSDLTuAssetVaoHeThongMobile();
        addControls();
        addEvents();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.Book) {
            Intent intent = new Intent(MainActivity.this, BookActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.exit){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }




    private void addControls() {

        imgmonNgonMoiNgay = (ImageView) findViewById(R.id.imgmonNgonMoiNgay);
        gvHinh = (GridView) findViewById(R.id.gvHinh);
        dsMonAn = new ArrayList<>();
        dsMonAn.add(R.drawable.thucuong);
        dsMonAn.add(R.drawable.mondacsan);
        dsMonAn.add(R.drawable.cachlambanh);
        dsMonAn.add(R.drawable.monnhau);
        dsMonAn.add(R.drawable.monchay);
        dsMonAn.add(R.drawable.monansanng);


        imageAdapter = new ImageAdapter(MainActivity.this, R.layout.items, dsMonAn);
        gvHinh.setAdapter(imageAdapter);
    }

    private void addEvents() {
        gvHinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xuLyClickItem(position);
            }
        });
    }

    private void xuLyClickItem(int position) {

        switch (position) {
            case 0:
                //chon thuc uong
                Intent intent = new Intent(MainActivity.this,ManHinhThucUongActivity.class);
                startActivity(intent);
                break;
            case 1:
                 Intent intent1 = new Intent(MainActivity.this, ManHinhMonDacSanctivity.class);
                 startActivity(intent1);
                 break;
            case 2:
                Intent intent2 = new Intent(MainActivity.this,ManHinhCachLamBanhActivity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(MainActivity.this, ManHinhMonNhauActivity.class);
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(MainActivity.this, ManHinhMonChayActivity.class);
                startActivity(intent4);
                break;
            case 5:
                Intent intent5 = new Intent(MainActivity.this, ManHinhMonAnSangActivity.class);
                startActivity(intent5);
                break;



        }
    }


    private void xuLySaoChepCSDLTuAssetVaoHeThongMobile() {
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "sao chep CSDL thanh cong", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = layDuongDanLuuTru();


            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException ex) {

            Log.e("loi sao chep", ex.toString());


        }
    }

    private String layDuongDanLuuTru() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;

    }


}



