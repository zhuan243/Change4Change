package com.example.hw6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class charity extends AppCompatActivity {

    HashMap<String, Integer> match;	 //for getting the best match
    HashMap<String, ArrayList<String>> categories; //for populating categories and charities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity);

        final Intent iCurr = getIntent();

        Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(charity.this, dwb.class);
                intent.putExtra("id",iCurr.getStringExtra("id"));
                startActivity(intent);
            }
        });

        final TextView output = findViewById(R.id.chars);
        EditText enterText = findViewById(R.id.tags);
        populate();
        enterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    List<String> test = new ArrayList<>();
                    test=Arrays.asList(charSequence.toString().split(","));

                    output.setText(returnCharity(test));

                    Toast.makeText(charity.this, "Saved your text", Toast.LENGTH_LONG).show();
                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    List<String> test = new ArrayList<>();
                    test=Arrays.asList(editable.subSequence(0,editable.length()).toString().split(","));

                    output.setText(returnCharity(test));

                    Toast.makeText(charity.this, "Saved your text", Toast.LENGTH_LONG).show();
                } catch (Exception e) {

                }
            }
        });
    }
    private void populate() {

        categories = new HashMap<>();
        try {
            InputStream fis = getResources().openRawResource(R.raw.test);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line = br.readLine();
            while (line != null) {
                String[] data = line.split(",", 0);

                for (int i = 1; i < data.length; i++) {
                    if (categories.containsKey(data[i].toLowerCase())) {
                        categories.get(data[i].toLowerCase()).add(data[0]);
                    } else {
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(data[0]);
                        categories.put(data[i].toLowerCase(), temp);
                    }
                }
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {

        }

    }

    private String returnCharity(List<String> input) {

        match = new HashMap<>();
        String charity = "No Charities Exist";
        int largest = 0;

        for(String i: input) {
            //i is the category, or the potential key for categories Hashmap
            if(categories.containsKey(i.toLowerCase())) {

                for(String j: categories.get(i.toLowerCase())) {

                    if(match.containsKey(j)) {
                        int inc = match.get(j)+1;
                        match.put(j, inc);
                    }else{
                        match.put(j, 1);
                    }
                }

            }else {
                System.out.println("category doesnt exist: " + i);
            }
        }

        for(String key: match.keySet()) {

            if(match.get(key) > largest) {
                largest = match.get(key);
                charity = key;
            }

        }


        return charity;
    }
}
