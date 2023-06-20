package com.example.androidvolly;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity
{
        EditText log_email,log_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_email = (EditText) findViewById(R.id.log_email);
        log_pass = (EditText) findViewById(R.id.log_pass);
    }

    public void loginUser(View view)
    {
        String email1 = log_email.getText().toString();
        String pass1 = log_pass.getText().toString();

        RequestQueue rq = Volley.newRequestQueue(this);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading....");
        progressDialog.setMessage("please wait...!!");
        progressDialog.show();
        //for java api -> "http://192.168.1.17:9494/ApiDemo/Login"
        //to call php api from single page-> "http://192.168.1.17/Login2.php"
        //for php one page  -> "http://192.168.1.17:9494/LoginAndRegister.php/login"
        StringRequest sq = new StringRequest(
                Request.Method.POST,
                UrlPaths.LOGIN_URL,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progressDialog.hide();
               // Toast.makeText(Login.this, ""+response, Toast.LENGTH_SHORT).show();
                if(response.trim().equals("fail"))
                {
                    Log.d("Responce", ""+response);
                    Toast.makeText(Login.this, "Email ID n password Didn't match", Toast.LENGTH_SHORT).show();
                    //code to go on Profile page

                }
                else
                {
                    Log.d("Responceelse", ""+response);
                    Toast.makeText(Login.this, ""+response, Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.hide();
                        Log.d("ERRoorrr", ""+error);
                        Toast.makeText(Login.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap <String,String> hm = new HashMap<>();
                hm.put("key_email",email1);
                hm.put("key_pass",pass1);
                return hm;
            }
        };

        rq.add(sq);

    }
}