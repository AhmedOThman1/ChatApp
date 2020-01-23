package com.example.chatapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static public ArrayList<message> messages = new ArrayList<message>();
    ImageView cam , gallery , more ,like;
    EditText message;
    ListView list;
    final static int CODE1 = 0 , CODE2 = 1 , SEND = 010 , LIKE = 011;
    int main_button_val ;
    MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cam = findViewById(R.id.cam);
        gallery = findViewById(R.id.gallery);
        more = findViewById(R.id.more);
        like = findViewById(R.id.like);

        message = findViewById(R.id.message);

        list = findViewById(R.id.list);

        messages.clear();
        messages.add(new message("this is a demo message from me i'm the programmer ho made this fucking app !111",false,0,true,0) );
        messages.add(new message("",true,R.drawable.profile,false,40) );
        messages.add(new message("this message from me !333",false,0,true,0) );
        messages.add(new message("this is a  message from me i'm the programmer ho made this  app !444",true,R.drawable.osman,true,0) );
        messages.add(new message("this message from me !555",false,0,true,0) );
        messages.add(new message("",false,0,false,23) );
        messages.add(new message("this is a demo message from me i'm the programmer ho made this fucking app !777",false,0,true,0) );
        messages.add(new message("this is a demo message from me i'm the programmer ho made this fucking app !888",true,R.drawable.samy,true,0) );
        messages.add(new message("this is a demo message from me i'm the programmer ho made this fucking app !999",false,0,true,0) );
        messages.add(new message("",true,R.drawable.ziad,false,14) );
        messages.add(new message("",false,0,false,25) );
        messages.add(new message("",false,0,false,03) );
        messages.add(new message("",true,R.drawable.shawky,false,53) );
        messages.add(new message("this is a demo message from me i'm the programmer ho made this fucking app !1414",false,0,true,0) );
        messages.add(new message("this is a demo message from me i'm the programmer ho made this fucking app !1515",false,0,true,0) );
        messages.add(new message("",false,0,false,23) );
        messages.add(new message("",true,R.drawable.samar,false,32) );
        messages.add(new message("this is a demo message from me i'm the programmer ho made this fucking app !1818",false,0,true,0) );
        messages.add(new message("this is a demo message from me i'm the programmer ho made this fucking app !1919",false,0,true,0) );
        messages.add(new message("this is a demo message from me i'm the programmer ho made this fucking app !2020",false,0,true,0) );


        adapter = new MessageAdapter(MainActivity.this,messages);

        list.setAdapter(adapter);

        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Toast.makeText(MainActivity.this, "before", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(message.getText().toString().isEmpty())
                {
                    like.setImageResource(R.drawable.like);
                    main_button_val = LIKE;

                }
                else
                {
                    like.setImageResource(R.drawable.send);
                    main_button_val = SEND;
                }

                Toast.makeText(MainActivity.this, "during", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

                Toast.makeText(MainActivity.this, "after", Toast.LENGTH_SHORT).show();

            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,CODE1);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gal = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gal ,"select image"),CODE2);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.messages_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==CODE1 && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                //img.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(requestCode ==CODE2 && resultCode == RESULT_OK) {
            Uri imgUri = data.getData();
            try {
                Bitmap bitmap = (Bitmap) MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                //img.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
