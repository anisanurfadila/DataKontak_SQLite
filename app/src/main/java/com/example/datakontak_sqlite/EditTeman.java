package com.example.datakontak_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.datakontak_sqlite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class EditTeman extends AppCompatActivity {
    protected Cursor cursor;
    private TextInputEditText eNama,eTelpon;
    private Button editBtn;
    String enm,etlp,id;
    DBController controller = new DBController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teman);

        eNama = (TextInputEditText)findViewById(R.id.edtNamatxt);
        editBtn = (Button)findViewById(R.id.buttonEdit);
        eTelpon = (TextInputEditText)findViewById(R.id.edtTelpontxt);
        id = getIntent().getStringExtra("id");
        enm = getIntent().getStringExtra("nama");
        etlp = getIntent().getStringExtra("telpon");

        setTitle("Edit Data");
        eNama.setText(enm);
        eTelpon.setText(etlp);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eNama.getText().toString().equals("") || eTelpon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data belum lengkap!",Toast.LENGTH_LONG).show();
                }else{
                    enm = eNama.getText().toString();
                    etlp = eTelpon.getText().toString();
                    HashMap<String,String> values = new HashMap<>();
                    values.put("id",id);
                    values.put("nama",enm);
                    values.put("telpon",etlp);
                    callHome();
                }
            }
        });
    }
    public void callHome(){
        Intent intent = new Intent(EditTeman.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}