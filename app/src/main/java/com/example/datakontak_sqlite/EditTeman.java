package com.example.datakontak_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.datakontak_sqlite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditTeman extends AppCompatActivity {

    TextView idText;
    TextInputEditText edNama,edTelpon;
    Button editBtn;
    String id,nm,tlp,namaEd,telponEd;
    int sukses;

    private static String url_update = "http://10.0.2.2/umyTI/updatetm.php";
    public static final String TAG = EditTeman.class.getSimpleName();
    public static final String TAG_SUCCES ="success";



//    SQL LITE
//    protected Cursor cursor;
//    private TextInputEditText eNama,eTelpon;
//    private Button editBtn;
//    String enm,etlp,id;
//    DBController controller = new DBController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teman);


        idText = findViewById(R.id.textId);
        edNama = findViewById(R.id.editNm);
        edTelpon = findViewById(R.id.editTlp);
        editBtn = findViewById(R.id.buttonEdit);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("kunci1");
        nm = bundle.getString("kunci2");
        tlp = bundle.getString("kunci3");

        idText.setText("id : "+id);
        edNama.setText(nm);
        edTelpon.setText(tlp);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                EditData();
            }
        });
//        SQL LITE
//        eNama = (TextInputEditText)findViewById(R.id.edtNamatxt);
//        editBtn = (Button)findViewById(R.id.buttonEdit);
//        eTelpon = (TextInputEditText)findViewById(R.id.edtTelpontxt);
//        id = getIntent().getStringExtra("id");
//        enm = getIntent().getStringExtra("nama");
//        etlp = getIntent().getStringExtra("telpon");
//
//        setTitle("Edit Data");
//        eNama.setText(enm);
//        eTelpon.setText(etlp);
//        editBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(eNama.getText().toString().equals("") || eTelpon.getText().toString().equals("")){
//                    Toast.makeText(getApplicationContext(),"Data belum lengkap!",Toast.LENGTH_LONG).show();
//                }else{
//                    enm = eNama.getText().toString();
//                    etlp = eTelpon.getText().toString();
//                    HashMap<String,String> values = new HashMap<>();
//                    values.put("id",id);
//                    values.put("nama",enm);
//                    values.put("telpon",etlp);
//                    callHome();
//                }
//            }
//        });
//    }
//    public void callHome(){
//        Intent intent = new Intent(EditTeman.this,MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
    }

    public void EditData(){
        namaEd = edNama.getText().toString();
        telponEd = edTelpon.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringReq = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Respon: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    sukses = jObj.getInt(TAG_SUCCES);
                    if (sukses == 1) {
                        Toast.makeText(EditTeman.this, "Sukses edit data", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(EditTeman.this, "GAGAL edit data", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Erros : "+error.getMessage());
                Toast.makeText(EditTeman.this,"gagal edit data",Toast.LENGTH_SHORT).show();


        }
    })

        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                params.put("nama",namaEd);
                params.put("telpon",telponEd);
                return params;
            }
        };
        requestQueue.add(stringReq);
        CallHomeActivity();
    }

    public void CallHomeActivity(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}