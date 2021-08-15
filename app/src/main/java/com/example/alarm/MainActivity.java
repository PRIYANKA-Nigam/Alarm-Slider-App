package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public void generate(int timesTable){
        ArrayList<String> strings=new ArrayList<String>();
        for (int i=1;i<=10;i++){
            strings.add(Integer.toString(i*timesTable));
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strings);
        listView.setAdapter(arrayAdapter);
    }
TextView textView;
    SeekBar seekBar;
    ListView listView;
    EditText editText;
    Button button,button2;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView2);
        listView=(ListView)findViewById(R.id.lll);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(20);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min=1;int timetable;
                if (progress<min){
                    timetable=min;seekBar.setProgress(min);
                }else {
                    timetable=progress;
                }
                generate(timetable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        generate(10);
        editText=(EditText)findViewById(R.id.editTextTextPersonName);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button3);
        mediaPlayer=MediaPlayer.create(this,R.raw.sample1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time=Integer.parseInt(editText.getText().toString());
                final int milli=time*1000;
                new CountDownTimer(milli,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        textView.setText("00.0"+String.valueOf(millisUntilFinished/1000));
                    }

                    @Override
                    public void onFinish() {
                        textView.setText("Alarm");
                        mediaPlayer.start();
                    }
                }.start();
            }
        });
      button2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mediaPlayer.stop();
          }
      });
    }
}