package com.example.chatapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static public ArrayList<message> messages = new ArrayList<message>();
    ImageView cam , gallery , mic , hide ,like;
    CircleImageView profile_image ;
    TextView acc_name , last_seen;
    EditText message;
    LinearLayout inner_container;
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
        mic = findViewById(R.id.mic);
        like = findViewById(R.id.like);
        hide = findViewById(R.id.hide);
        inner_container = findViewById(R.id.inner_container);

        message = findViewById(R.id.message);

        profile_image = findViewById(R.id.profile_image);
        acc_name = findViewById(R.id.acc_name);
        last_seen = findViewById(R.id.last_seen);

        profile_image.setImageResource(R.drawable.osman);
        acc_name.setText("Ahmed Osman");
        last_seen.setText("57m ago");

//        Intent i = getIntent();
//
//        profile_image.setImageBitmap(StringToBitMap(i.getStringExtra("profile_image")) );
//        acc_name.setText(i.getStringExtra("acc_name"));
//        last_seen.setText(i.getStringExtra("last_seen"));

        list = findViewById(R.id.list);

        messages.clear();
        messages.add(new message("text","this is a demo message from me i'm the programmer ho made this fucking app !111" ) );
        messages.add(new message("voice",43,0,R.drawable.shawky) );
        messages.add(new message("text"  , "this message from me !333",R.drawable.osman) );
        messages.add(new message("text","this is a  message from me i'm the programmer ho made this  app !444",R.drawable.osman) );
        messages.add(new message("text","this message from me !555") );
        messages.add(new message("voice",23,0) );
        messages.add(new message("text","this is a demo message from me i'm the programmer ho made this fucking app !777") );
        messages.add(new message("text","this is a demo message from me i'm the programmer ho made this fucking app !888",R.drawable.samy) );
        messages.add(new message("text","this is a demo message from me i'm the programmer ho made this fucking app !999") );
        messages.add(new message("voice",23,0,R.drawable.ziad) );
        messages.add(new message("voice",25,0) );
        messages.add(new message("voice",3,0) );
        messages.add(new message("voice",53,0,R.drawable.shawky) );
        messages.add(new message("text","this is a demo message from me i'm the programmer ho made this fucking app !1414") );
        messages.add(new message("text","this is a demo message from me i'm the programmer ho made this fucking app !1515") );
        messages.add(new message("voice",23,0) );
        messages.add(new message("voice",32,0,R.drawable.samar) );
        messages.add(new message("text","this is a demo message from me i'm the programmer ho made this fucking app !1818") );
        messages.add(new message("text","this is a demo message from me i'm the programmer ho made this fucking app !1919") );
        messages.add(new message("text","this is a demo message from me i'm the programmer ho made this fucking app !2020") );


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

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mic.setVisibility(View.GONE);
                cam.setVisibility(View.GONE);
                gallery.setVisibility(View.GONE);
                hide.setVisibility(View.VISIBLE);
                //inner_container.setLayoutParams(new TableLayout.LayoutParams( 0 , LinearLayout.LayoutParams.WRAP_CONTENT,0.8f));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(message.getText().toString().isEmpty())
                        {
                            mic.setVisibility(View.VISIBLE);
                            cam.setVisibility(View.VISIBLE);
                            gallery.setVisibility(View.VISIBLE);
                            hide.setVisibility(View.GONE);
                           // inner_container.setLayoutParams(new TableLayout.LayoutParams( 0 , LinearLayout.LayoutParams.WRAP_CONTENT,0.6f));

                        }
                    }
                }, 3000);
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mic.setVisibility(View.VISIBLE);
                cam.setVisibility(View.VISIBLE);
                gallery.setVisibility(View.VISIBLE);
                hide.setVisibility(View.GONE);
               // inner_container.setLayoutParams(new TableLayout.LayoutParams( 0 , LinearLayout.LayoutParams.WRAP_CONTENT,6));

            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(message.getText().toString().isEmpty())
                {
                    //messages.add(new message("icon" , R.drawable.like ))
                }
                else
                {
                    messages.add(new message("text","this is a demo message from me i'm the programmer ho made this fucking app !111" ) );

                    //messages.add(new message("text",false, message.getText().toString(),0));
                    adapter.notifyDataSetChanged();
                    message.setText("");
                    //list.setSelection(adapter.getCount()-1);
                }
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

    /**
     * convert Bitmap to String
     **/
    static public String encodeImage(Bitmap imagee) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagee.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }



    /**
     * to convert string to Bitmap
     **/
    static public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


}
