package com.example.dialogfragmentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void popwindow(View view) {
        Stroage_Frgment_ok_dialog stroage_frgment_ok_dialog = new Stroage_Frgment_ok_dialog();
        stroage_frgment_ok_dialog.show(getSupportFragmentManager(),"Stroage_Frgment_ok_dialog");

    }
}
