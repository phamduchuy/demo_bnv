package com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.editDoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.gcacace.signaturepad.DrawOnBitmapActivity;

import it.gcacace.signaturepad.R;

public class EditDocumentActivity extends AppCompatActivity
{
    private ImageView imgEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_document);

        imgEdit = (ImageView) findViewById(R.id.imgEdit);

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DrawOnBitmapActivity.class));
            }
        });
    }
}
