package com.example.hw6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dnb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnb);

        final Intent iCurr = getIntent();

        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dnb.this, breakdown.class);
                intent.putExtra("id",iCurr.getStringExtra("id"));
                startActivity(intent);
            }
        });

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dnb.this, sett.class);
                intent.putExtra("id",iCurr.getStringExtra("id"));
                startActivity(intent);
            }
        });

    }
}
