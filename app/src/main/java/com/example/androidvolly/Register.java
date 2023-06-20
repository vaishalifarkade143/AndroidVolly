package com.example.androidvolly;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import  com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText reg_name,reg_email,reg_pass,reg_gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_name = (EditText) findViewById(R.id.reg_name);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_pass = (EditText) findViewById(R.id.reg_pass);
        reg_gender = (EditText) findViewById(R.id.reg_gender);
    }

    public void registerUser(View view)
    {
        String name1 = reg_name.getText().toString();
        String email1 = reg_email.getText().toString();
        String pass1 = reg_pass.getText().toString();
        String gender1 = reg_gender.getText().toString();

       RequestQueue rq = Volley.newRequestQueue(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Loading");
        builder.setMessage("please wait...");
        builder.show();
        AlertDialog alertDialog = builder.create();
        //for java api -> "http://192.168.1.17:9494/ApiDemo/Register"
        //to call php api from single page-> "http://192.168.1.17/Register2.php"
       StringRequest stringRequest = new StringRequest(Request.Method.POST,
               UrlPaths.REGISTER_URL,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response)
                   {
                       alertDialog.hide();
                       Log.d("responce","onResponce() is being executed");
                       Toast.makeText(Register.this, ""+response, Toast.LENGTH_SHORT).show();
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error)
                   {
                       alertDialog.hide();
                       Log.d("error",""+error);
                       Toast.makeText(Register.this, ""+error, Toast.LENGTH_SHORT).show();
                   }
               }){
           @Nullable
           @Override
           protected Map<String, String> getParams() throws AuthFailureError
           {
               HashMap<String,String> hm = new HashMap<>();

               hm.put("key_name",name1);
               hm.put("key_email",email1);
               hm.put("key_pass",pass1);
               hm.put("key_gender",gender1);

               return hm;
           }
       };
       stringRequest.setRetryPolicy(new DefaultRetryPolicy(3*DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 0));
       rq.add(stringRequest);
    }
}