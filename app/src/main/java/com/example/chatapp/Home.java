package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.chatapp.MainActivity.encodeImage;

public class Home extends AppCompatActivity {
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        b = findViewById(R.id.btn);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Home.this,MainActivity.class);
//                home.putExtra("profile_image", encodeImage(BitmapFactory.decodeResource(getResources(),R.drawable.osman) ) );
//                home.putExtra("acc_name" ,"Ahmed Osman");
//                home.putExtra("last_seen" ,"57m ago");
                startActivity(home);
            }
        });

    }
}
