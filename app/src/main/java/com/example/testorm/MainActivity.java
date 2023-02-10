package com.example.testorm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import orm.Configure;
import orm.ISession;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new TestOrmBase().Run(getApplicationInfo(),getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}