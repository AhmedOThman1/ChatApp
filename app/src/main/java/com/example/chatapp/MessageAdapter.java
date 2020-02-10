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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import static com.example.chatapp.MainActivity.messages;

public class MessageAdapter extends ArrayAdapter<message> {

    private Activity activity;
    int prPos = -1;
    message currentmessage;
    TextView your_message, his_message;
    Chronometer your_voice_time, his_voice_time;
    ImageView his_image, play_his, play_your;
    ProgressBar your_prog, his_prog;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        currentmessage = getItem(position);

        View view = convertView;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);

            if (currentmessage.isMe_or_him()) {
                if (currentmessage.getMessage_type().equals("text")) {
                    // Me , Text
                    view = layoutInflater.inflate(R.layout.your_message_design, parent, false);

                } else if (currentmessage.getMessage_type().equals("voice")) {
                    // me  ,  voice
                    view = layoutInflater.inflate(R.layout.your_voice_message, parent, false);

                }

            } else {
                if (currentmessage.getMessage_type().equals("text")) {
                    // him , Text
                    view = layoutInflater.inflate(R.layout.his_message_design, parent, false);
                } else {
                    //him , voice
                    view = layoutInflater.inflate(R.layout.his_voice_message, parent, false);
                }
            }
        }


        if (currentmessage.isMe_or_him()) {
            if (currentmessage.getMessage_type().equals("text")) {
                // Me , Text

                your_message = view.findViewById(R.id.your_message);

                your_message.setText(currentmessage.getMessage_text());

            } else if (currentmessage.getMessage_type().equals("voice")) {
                // me  ,  voice
                your_voice_time = view.findViewById(R.id.your_voice_time);
                play_your = view.findViewById(R.id.play_your);
                your_prog = view.findViewById(R.id.voice_progress_your);


                your_prog.setMax(currentmessage.getVoice_time() / 1000);
                your_voice_time.setText("00:" + (currentmessage.getVoice_time() / 1000));
                play_your.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!currentmessage.isVoice_message_running()) {

                            your_voice_time.setBase(SystemClock.elapsedRealtime() - currentmessage.getBaseoffset());
                            your_voice_time.start();
                            currentmessage.setVoice_message_running(true);
                            play_your.setImageResource(R.drawable.pause_yours);

                        } else {
                            your_voice_time.stop();
                            currentmessage.setBaseoffset(SystemClock.elapsedRealtime() - your_voice_time.getBase());
                            currentmessage.setVoice_message_running(false);
                            play_your.setImageResource(R.drawable.play_yours);
                        }

                    }
                });

                your_voice_time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        int time = currentmessage.getVoice_time(),
                                currentTime = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000);

                        your_prog.setProgress(currentTime);

                        if (time > 0 && (SystemClock.elapsedRealtime() - chronometer.getBase()) >= time) {
                            Log.i("test", "" + SystemClock.elapsedRealtime() + "   ,  time :     " + time + "      ,    f  =   " + (SystemClock.elapsedRealtime() - chronometer.getBase()));
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            your_voice_time.stop();
                            currentmessage.setVoice_message_running(false);
                            play_your.setImageResource(R.drawable.play_yours);
                            your_voice_time.setText("00:" + (currentmessage.getVoice_time() / 1000));

                        }
                    }
                });


            } else if (currentmessage.getMessage_type().equals("photo")) {
                //TODO photo here
            }

        } else {
            if (currentmessage.getMessage_type().equals("text")) {
                // him , Text
                his_image = view.findViewById(R.id.his_image);
                his_message = view.findViewById(R.id.his_message);


                his_message.setText(currentmessage.getMessage_text());
                his_image.setImageResource(currentmessage.getHis_profile_img());

            } else if (currentmessage.getMessage_type().equals("voice")) {
                //him , voice
                his_voice_time = view.findViewById(R.id.his_voice_time);
                play_his = view.findViewById(R.id.play_his);
                his_prog = view.findViewById(R.id.voice_progress_his);
                his_image = view.findViewById(R.id.his_image);


                his_prog.setMax(currentmessage.getVoice_time() / 1000);
                his_voice_time.setText("00:" + (currentmessage.getVoice_time() / 1000));
                his_image.setImageResource(currentmessage.getHis_profile_img());
                play_his.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!currentmessage.isVoice_message_running()) {
                            Toast.makeText(activity, "Test", Toast.LENGTH_SHORT).show();
                            his_voice_time.setBase(SystemClock.elapsedRealtime() - currentmessage.getBaseoffset());
                            his_voice_time.start();
                            currentmessage.setVoice_message_running(true);
                            play_his.setImageResource(R.drawable.pause_him);
                        } else {
                            his_voice_time.stop();
                            currentmessage.setBaseoffset(SystemClock.elapsedRealtime() - his_voice_time.getBase());
                            currentmessage.setVoice_message_running(false);
                            play_his.setImageResource(R.drawable.play_him);
                        }

                    }
                });

                his_voice_time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        int time = currentmessage.getVoice_time(),
                                currentTime = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000);

                        his_prog.setProgress(currentTime);

                        if (time > 0 && (SystemClock.elapsedRealtime() - chronometer.getBase()) >= time) {
                            Log.i("test", "" + SystemClock.elapsedRealtime() + "   ,  time2 :     " + time + "      ,    f2  =   " + (SystemClock.elapsedRealtime() - chronometer.getBase()));
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            his_voice_time.stop();
                            currentmessage.setVoice_message_running(false);
                            play_his.setImageResource(R.drawable.play_him);
                            his_voice_time.setText("00:" + (currentmessage.getVoice_time() / 1000));

                        }
                    }
                });


            } else if (currentmessage.getMessage_type().equals("photo")) {
                //TODO photo here
            }
        }


        // Toast.makeText(activity, "poo "+ mPosition, Toast.LENGTH_SHORT).show();
        // postViewHolder.vProfile_image.setImageResource(currentPost.getProfile_image());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView



        return view;
    }

}
