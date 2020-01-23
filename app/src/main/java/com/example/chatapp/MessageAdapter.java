package com.example.chatapp;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import static com.example.chatapp.MainActivity.messages;

public class MessageAdapter extends ArrayAdapter<message> {

    private Activity activity;

    int mPosition;
    message currentmessage;
    public MessageAdapter(Activity context, List<message> messages) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, messages);
        activity = context;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public message getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //    public void updateSelectedPosition (int selectedPosition) {
//       // this.selectedPosition = selectedPosition;
//        notifyDataSetChanged();
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

        mPosition = position;
        View listItemViewm1 = null, listItemViewm2= null ,listItemViewv1 = null, listItemViewv2= null , listItemView;
        final messageViewHolder messageHolder;



        currentmessage = getItem(mPosition);
        Toast.makeText(activity, "before if : "+position, Toast.LENGTH_SHORT).show();

        if (convertView == null) {

            /*
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.post, parent, false);
            */

            // TODO add menu in every post
            // TODO add dropback and report this post as duplicate and so on

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);

            listItemViewm1 = layoutInflater.inflate(R.layout.your_message_design, parent, false);
            listItemViewm2 = layoutInflater.inflate(R.layout.his_message_design, parent, false);
            listItemViewv1 = layoutInflater.inflate(R.layout.your_voice_message, parent, false);
            listItemViewv2 = layoutInflater.inflate(R.layout.his_voice_message, parent, false);



            messageHolder = new messageViewHolder();
            messageHolder.his_image = listItemViewm2.findViewById(R.id.his_image);
            messageHolder.his_message = listItemViewm2.findViewById(R.id.his_message);

            messageHolder.his_voice_time = listItemViewv2.findViewById(R.id.his_voice_time);
            messageHolder.play_his = listItemViewv2.findViewById(R.id.play_his);

            messageHolder.your_message = listItemViewm1.findViewById(R.id.your_message);

            messageHolder.your_voice_time = listItemViewv1.findViewById(R.id.your_voice_time);
            messageHolder.play_your = listItemViewv1.findViewById(R.id.play_your);

           // currentmessage = getItem(mPosition);

            Toast.makeText(activity, "inside if : "+position, Toast.LENGTH_SHORT).show();
            if(!currentmessage.isMeORyou())
            {
                if(currentmessage.isVoiceORtext())
                {
                    // Me , Text
                    listItemViewm1.setTag(messageHolder);

                }
                else
                {
                    // me  ,  voice
                    listItemViewv1.setTag(messageHolder);

                }
            }
            else
            {
                if(currentmessage.isVoiceORtext())
                {
                    // him , Text
                    listItemViewm2.setTag(messageHolder);

                }
                else
                {
                    //him , voice
                    listItemViewv2.setTag(messageHolder);

                }
            }

        } else {



            if(!currentmessage.isMeORyou())
            {
                if(currentmessage.isVoiceORtext())
                {
                    // Me , Text
                    listItemViewm1 = convertView;
                    messageHolder = (messageViewHolder) listItemViewm1.getTag();


                }
                else
                {
                    // me  ,  voice
                    listItemViewv1 = convertView;
                    messageHolder = (messageViewHolder) listItemViewv1.getTag();


                }
            }
            else
            {
                if(currentmessage.isVoiceORtext())
                {
                    // him , Text
                    listItemViewm2 = convertView;
                    messageHolder = (messageViewHolder) listItemViewm2.getTag();


                }
                else
                {
                    //him , voice
                    listItemViewv2 = convertView;
                    messageHolder = (messageViewHolder) listItemViewv2.getTag();

                }
            }



        }
        Toast.makeText(activity, "outer if : "+position, Toast.LENGTH_SHORT).show();
        if(!currentmessage.isMeORyou())
        {
            if(currentmessage.isVoiceORtext())
            {
                // Me , Text
                messageHolder.your_message.setText(currentmessage.getMessage_text());
            }
            else
            {
                // me  ,  voice
                messageHolder.your_voice_time.setText("00:"+(currentmessage.getvoice_time()/1000));
                messageHolder.play_your.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!currentmessage.voice_message_running)
                        {
                            messageHolder.your_voice_time.setBase(SystemClock.elapsedRealtime() - currentmessage.getBaseoffset());
                            messageHolder.your_voice_time.start();
                            currentmessage.voice_message_running = true;
                            messageHolder.play_your.setImageResource( R.drawable.pause_yours );
                        }
                        else
                        {
                            messageHolder.your_voice_time.stop();
                            currentmessage.setBaseoffset(SystemClock.elapsedRealtime() - messageHolder.your_voice_time.getBase());
                            currentmessage.voice_message_running = false;
                            messageHolder.play_your.setImageResource( R.drawable.play_yours );
                        }

                    }
                });

                messageHolder.your_voice_time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        int time = currentmessage.getvoice_time();
                        if(time>0&&(SystemClock.elapsedRealtime() - chronometer.getBase())>=time)
                        {
                            Log.i("test",""+SystemClock.elapsedRealtime() +"   ,  time :     "+ time + "      ,    f  =   "+(SystemClock.elapsedRealtime() - chronometer.getBase()));
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            messageHolder.your_voice_time.stop();
                            currentmessage.voice_message_running = false;
                            messageHolder.play_your.setImageResource( R.drawable.play_yours );
                            messageHolder.your_voice_time.setText("00:"+(currentmessage.getvoice_time()/1000) );

                        }
                    }
                });


            }
        }
        else
        {
            if(currentmessage.isVoiceORtext())
            {
                // him , Text
                messageHolder.his_message.setText(currentmessage.getMessage_text());
                messageHolder.his_image.setImageResource(currentmessage.getMessage_photo());

            }
            else
            {
                //him , voice
                messageHolder.his_voice_time.setText("00:"+ ( currentmessage.getvoice_time()/1000) );
                messageHolder.his_image.setImageResource(currentmessage.getMessage_photo());
                messageHolder.play_his.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!currentmessage.voice_message_running)
                        {
                            Toast.makeText(activity, "Test", Toast.LENGTH_SHORT).show();
                            messageHolder.his_voice_time.setBase(SystemClock.elapsedRealtime() - currentmessage.getBaseoffset());
                            messageHolder.his_voice_time.start();
                            currentmessage.voice_message_running = true;
                            messageHolder.play_his.setImageResource( R.drawable.pause_him );
                        }
                        else
                        {
                            messageHolder.his_voice_time.stop();
                            currentmessage.setBaseoffset(SystemClock.elapsedRealtime() - messageHolder.his_voice_time.getBase());
                            currentmessage.voice_message_running = false;
                            messageHolder.play_his.setImageResource( R.drawable.play_him );
                        }

                    }
                });

                messageHolder.his_voice_time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        final int time = 23000;
                        if((SystemClock.elapsedRealtime() - chronometer.getBase()) >= time)
                        {
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            messageHolder.his_voice_time.stop();
                            currentmessage.voice_message_running = false;
                            messageHolder.play_his.setImageResource( R.drawable.play_him );
                            messageHolder.his_voice_time.setText("00:"+ ( currentmessage.getvoice_time()/1000)  );

                        }
                    }
                });



            }
        }

        // Toast.makeText(activity, "poo "+ mPosition, Toast.LENGTH_SHORT).show();
       // postViewHolder.vProfile_image.setImageResource(currentPost.getProfile_image());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        if(!currentmessage.isMeORyou())
        {
            if(currentmessage.isVoiceORtext())
            {
                // Me , Text
                return listItemViewm1;

            }
            else
            {
                // me  ,  voice
                return listItemViewv1;

            }
        }
        else
        {
            if(currentmessage.isVoiceORtext())
            {
                // him , Text
                return listItemViewm2;

            }
            else
            {
                //him , voice
                return listItemViewv2;
            }
        }

    }

}
