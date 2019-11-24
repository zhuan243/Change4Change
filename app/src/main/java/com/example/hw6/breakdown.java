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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class breakdown extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakdown);

        final List<List<TextView>> tv = new ArrayList<>();
        tv.add(new ArrayList<TextView>());
        tv.get(0).add((TextView)findViewById(R.id.textView8));
        tv.get(0).add((TextView)findViewById(R.id.textView9));
        tv.get(0).add((TextView)findViewById(R.id.textView10));
        tv.get(0).add((TextView)findViewById(R.id.textView11));
        tv.add(new ArrayList<TextView>());
        tv.get(1).add((TextView)findViewById(R.id.textView12));
        tv.get(1).add((TextView)findViewById(R.id.textView13));
        tv.get(1).add((TextView)findViewById(R.id.textView14));
        tv.get(1).add((TextView)findViewById(R.id.textView15));
        tv.add(new ArrayList<TextView>());
        tv.get(2).add((TextView)findViewById(R.id.textView16));
        tv.get(2).add((TextView)findViewById(R.id.textView17));
        tv.get(2).add((TextView)findViewById(R.id.textView18));
        tv.get(2).add((TextView)findViewById(R.id.textView19));
        tv.add(new ArrayList<TextView>());
        tv.get(3).add((TextView)findViewById(R.id.textView20));
        tv.get(3).add((TextView)findViewById(R.id.textView21));
        tv.get(3).add((TextView)findViewById(R.id.textView22));
        tv.get(3).add((TextView)findViewById(R.id.textView23));
        final Intent iCurr = getIntent();

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(breakdown.this, dwb.class);
                intent.putExtra("id",iCurr.getStringExtra("id"));
                startActivity(intent);
            }
        });

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(breakdown.this, sett.class);
                intent.putExtra("id",iCurr.getStringExtra("id"));
                startActivity(intent);
            }
        });

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
                int count=0;
                for (purchases x: purchasesList) {
                    if (count<4){
                        tv.get(count).get(0).setText("$"+(new DecimalFormat("#.##")).format(change(x.amount)));
                        tv.get(count).get(1).setText("Status: "+x.status);
                        tv.get(count).get(2).setText("$"+x.amount+"≈"+(x.amount%1==0?x.amount+1:Math.ceil(x.amount)));
                        tv.get(count).get(3).setText("Date: " + x.date);
                        count++;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<purchases>> call, Throwable t) {

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
