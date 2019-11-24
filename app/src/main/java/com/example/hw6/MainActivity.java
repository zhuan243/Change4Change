package com.example.hw6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "OrderActivity";
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private DatabaseReference ref;
    private FirebaseDatabase database;

    private EditText emailInput;
    private EditText passwordInput;
    private EditText changeLimitInput;

    private String uid;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //Toast.makeText(MainActivity.this, currentUser.getEmail(),
                //Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        //final Retrofit retrofit = new Retrofit.Builder()
          //      .baseUrl("http://api.reimaginebanking.com/").addConverterFactory(GsonConverterFactory.create()).build();

        Button button1 = (Button)findViewById(R.id.button);
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calls service = retrofit.create(Calls.class);
                Call<List<purchases>> call = service.listpurchases("5dd94abd322fa016762f36ce",
                        "2f9b63a5fafc967d3ebdafa87b9b480c");
                call.enqueue(new Callback<List<purchases>>() {
                    @Override
                    public void onResponse(Call<List<purchases>> call, Response<List<purchases>> response) {
                        List<purchases> purchases = response.body();
                        Log.d("test",purchases.get(0).amount+"");
                        TextView tv = findViewById(R.id.textView);
                        tv.setText(purchases.toString());
                    }

                    @Override
                    public void onFailure(Call<List<purchases>> call, Throwable t) {

                    }
                });
            }
        });*/
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref=FirebaseDatabase.getInstance().getReference().child("9DUdIFzXQbc4UcjBo4P7C3IdUJ53");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String stringValue = dataSnapshot.child("capitalOneID").getValue(String.class);
                        Log.d("id1234",stringValue);
                        Intent intent = new Intent(MainActivity.this, dwb.class);
                        intent.putExtra("id",stringValue);
                        startActivity(intent);
                    }
                    @Override
                    public void onCancelled(DatabaseError e){
                    }
                });
                /*
                String stringValue = ref.child("capitalOneID").toString();
                Log.d("id123",stringValue);
                Intent intent = new Intent(MainActivity.this, dwb.class);
                intent.putExtra("id",stringValue);
                startActivity(intent);
                mAuth.signInWithEmailAndPassword("user@dil.com", "password")
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(Task<AuthResult> task) {
                                        userLogin(task);
                                    }
                });

                 */
            }
        });
    }

    public void userLogin(Task<AuthResult> task){
        if (task.isSuccessful()) {
            Log.d(TAG, "signInWithEmail:success");
            FirebaseUser user = mAuth.getCurrentUser();
            uid=user.getUid();


            Toast.makeText(MainActivity.this, "Logged in!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Log.w(TAG, "signInWithEmail:failure", task.getException());
            Toast.makeText(MainActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    123);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    123);
        }
    }
}
