package com.example.hw6;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class dwb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dwb);
        final TextView tv = findViewById(R.id.textView3);

        final Intent iCurr = getIntent();

        Retrofit retrofit = new Retrofit.Builder()
              .baseUrl("http://api.reimaginebanking.com/").addConverterFactory(GsonConverterFactory.create()).build();

        Calls service = retrofit.create(Calls.class);
        Call<List<purchases>> call = service.listpurchases(iCurr.getStringExtra("id"),
                "2f9b63a5fafc967d3ebdafa87b9b480c");
        call.enqueue(new Callback<List<purchases>>() {
            @Override
            public void onResponse(Call<List<purchases>> call, Response<List<purchases>> response) {
                List<purchases> purchasesList = response.body();
                Log.d("test",purchasesList.get(0).amount+"");
                double count=0;
                for (purchases x: purchasesList) {
                    count+=change(x.amount);
                }
                tv.setText((new DecimalFormat("#.##")).format(count)+"");
            }

            @Override
            public void onFailure(Call<List<purchases>> call, Throwable t) {

            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dwb.this, dnb.class);
                intent.putExtra("id",iCurr.getStringExtra("id"));
                startActivity(intent);
            }
        });

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dwb.this, breakdown.class);
                intent.putExtra("id",iCurr.getStringExtra("id"));
                startActivity(intent);
            }
        });

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dwb.this, sett.class);
                intent.putExtra("id",iCurr.getStringExtra("id"));
                startActivity(intent);
            }
        });
    }

    public double change(double price){
        if (price%1==0)
            return 1;
        Log.d("count",price+" "+(Math.ceil(price)-price)+"");
        return Math.ceil(price)-price;
    }
}
