package com.example.tugasdatabasesqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText stb, nama, angkatan, alamat;
    private Button bt_simpan,bt_muncul;
    private  DbHelper dbHelper;
    private Mahasiswa mhs;
    private TextView t;
    private Intent intentUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        stb = findViewById(R.id.mskstb);
        nama = findViewById(R.id.msknama);
        angkatan = findViewById(R.id.mskangkatan);
        alamat = findViewById(R.id.mskalamat);
        bt_simpan = findViewById(R.id.btn_simpan);
        bt_muncul = findViewById(R.id.btn_muncul);
        t = findViewById(R.id.textView);

        getSupportActionBar().setTitle("FORM PENDAFTARAN");

        bt_muncul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUpdate = null;
                Intent intent = new Intent(getApplicationContext(),TampilActivity.class);
                startActivityForResult(intent,1);
                dbHelper.close();
            }
        });
        bt_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentUpdate==null){
                    simpanData();
                }else{
                    updateData();
                }
            }
        });

    }

    private  void simpanData(){
        mhs = new Mahasiswa(stb.getText().toString(),
                nama.getText().toString(),
                Integer.parseInt(angkatan.getText().toString()),
                alamat.getText().toString());
        dbHelper.insertData(dbHelper.getWritableDatabase(),mhs);

        Toast.makeText(this,"DATA TERSIMPAN..",Toast.LENGTH_LONG).show();
        cleartext();
    }

    private void cleartext(){
        stb.setText("");
        alamat.setText("");
        nama.setText("");
        angkatan.setText("");
        intentUpdate = null;

        stb.requestFocus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            intentUpdate = data;
            stb.setText(data.getStringExtra("stb"));
            nama.setText(data.getStringExtra("nama"));
            alamat.setText(data.getStringExtra("alamat"));
            angkatan.setText(String.valueOf(data.getIntExtra("angkatan",0)));
        }
    }

    private void updateData(){
        mhs = new Mahasiswa(stb.getText().toString(),
                nama.getText().toString(),
                Integer.parseInt(angkatan.getText().toString()),
                alamat.getText().toString());
        dbHelper.updateDate(dbHelper.getWritableDatabase(),mhs,intentUpdate.getStringExtra("nama"));
        Toast.makeText(this,"DATA TERUPDATE...",Toast.LENGTH_LONG).show();
        cleartext();
    }
}
