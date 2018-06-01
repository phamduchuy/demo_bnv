package com.github.gcacace.signaturepad.expandableRecyclerView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import it.gcacace.signaturepad.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void ClickLogin(View view) {
        startActivity(new Intent(this, RequestActivity.class));
    }
}
