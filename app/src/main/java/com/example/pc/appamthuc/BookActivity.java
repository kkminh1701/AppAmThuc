package com.example.pc.appamthuc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;


public class BookActivity extends AppCompatActivity {
    EditText txtTieuDe, txtNoiDung;
    ImageButton btnLuu, btnThoat;
    ListView lvHistory;

    ArrayList<String> dsCong;
    ArrayAdapter<String> adapterCong;

    TabHost tabHost;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        addControls();
        addEvents();

    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tieuDe = txtTieuDe.getText().toString();
                String noiDung = txtNoiDung.getText().toString();

                String s = tieuDe + "\n"+ noiDung;
                dsCong.add(s);

                boolean b1 = myDatabase.insertData(tieuDe, noiDung);
                if(b1){
                    Toast.makeText(BookActivity.this, "Thêm THành Công", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(BookActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void addControls() {
        txtTieuDe = (EditText) findViewById(R.id.txtTieuDe);
        txtNoiDung = (EditText) findViewById(R.id.txtNoiDung);
        btnLuu = (ImageButton) findViewById(R.id.btnLuu);
        btnThoat = (ImageButton) findViewById(R.id.btnThoat);


        tabHost = (TabHost) findViewById(R.id.tabHost);

        lvHistory = (ListView) findViewById(R.id.lvHistory);
        dsCong = new ArrayList<>();
        adapterCong = new ArrayAdapter<String>(
                BookActivity.this,
                android.R.layout.simple_list_item_1,
                dsCong
        );
        lvHistory.setAdapter(adapterCong);


        //tabHost tạo cái đựng
        tabHost.setup();
        TabHost.TabSpec tab1
                = tabHost.newTabSpec("t1");
        tab1.setIndicator("1. Thêm Danh Sách");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2
                = tabHost.newTabSpec("t2");
        tab2.setIndicator("2. Danh sách đi chợ");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

    }
}


