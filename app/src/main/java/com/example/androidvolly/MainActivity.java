package com.example.androidvolly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public  void openRegisterUser(View view )
    {
        startActivity(new Intent(MainActivity.this, Register.class));
    }

    public void openLoginUser(View view)
    {
        startActivity(new Intent(MainActivity.this, Login.class));
    }
}