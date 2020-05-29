package com.example.tugasdatabasesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TampilActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private String stb,nama,alamat;
    private Integer angkatan;
    private TableLayout tb;
    private TableRow tr;
    private TextView t;
    private TextView col1,col2,col3,col4;
    private Button btn_update,btn_delete,btn_exit;
    private ArrayList<Mahasiswa>arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);
        dbHelper = new DbHelper(this);

        getSupportActionBar().setTitle("DATA");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb = findViewById(R.id.tb_mhs);
        btn_update = findViewById(R.id.button);
        btn_delete = findViewById(R.id.button1);
        btn_exit = findViewById(R.id.button2);
        Tampil();

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.hapusData(dbHelper.getWritableDatabase(),nama);
                    Tampil();
                }
            });

            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("stb",stb);
                    intent.putExtra("nama", nama);
                    intent.putExtra("alamat", alamat);
                    intent.putExtra("angkatan",angkatan);
                    dbHelper.close();
                    setResult(1,intent);
                    finish();
                }
            });
            btn_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }
    public void Tampil(){
        tb.removeAllViews();
        arrayList = dbHelper.getArrayList(dbHelper.getWritableDatabase());
        tr = new TableRow( this);
        col1 = new TextView( this);
        col2 = new TextView( this);
        col3 = new TextView( this);
        col4 = new TextView( this);

        col1.setWidth(250);
        col2.setWidth(300);
        col3.setWidth(200);
        col4.setWidth(200);

        col1.setText("STB");
        col2.setText("NAMA");
        col3.setText("ANGKATAN");
        col4.setText("ALAMAT");

        tr.addView(col1);
        tr.addView(col2);
        tr.addView(col3);
        tr.addView(col4);

        tb.addView(tr);

        for (final Mahasiswa mhs : arrayList) {
            tr = new TableRow(this);
            col1 = new TextView(this);
            col2 = new TextView(this);
            col3 = new TextView(this);
            col4 = new TextView(this);

            col1.setText(mhs.getStb());
            col2.setText(mhs.getNama());
            col3.setText(String.valueOf(mhs.getAngkatan()));
            col4.setText(mhs.getAlamat());

            col1.setWidth(250);
            col2.setWidth(300);
            col3.setWidth(150);
            col4.setWidth(200);

            tr.addView(col1);
            tr.addView(col2);
            tr.addView(col3);
            tr.addView(col4);

            tb.addView(tr);

            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < tb.getChildCount(); i++) {
                        stb = mhs.getStb();
                        nama = mhs.getNama();
                        alamat = mhs.getAlamat();
                        angkatan = mhs.getAngkatan();
                        if (tb.getChildAt(i) == v) {
                            tb.getChildAt(i).setBackgroundColor(Color.BLUE);
                        } else
                            tb.getChildAt(i).setBackgroundColor(Color.WHITE);
                    }
                }
            });
        }
    }
    }

