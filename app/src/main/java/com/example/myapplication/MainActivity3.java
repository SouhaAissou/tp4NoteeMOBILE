package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {

    private TextView mNameTextView;
    private TextView mDescriptionTextView;
    private ImageView mImageView;

    private ImageButton mPlayButton;

    private TextToSpeech textToSpeech;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("nom");
        String date = intent.getStringExtra("date");
        String itemDescription = intent.getStringExtra("contenu");
        int itemImageResourceId = intent.getIntExtra("image", 0);

        mNameTextView = findViewById(R.id.name_text_view);
        mDescriptionTextView = findViewById(R.id.description_text_view);
        mImageView = findViewById(R.id.image_view);
        TextView dateTextView = findViewById(R.id.date);

        mNameTextView.setText(itemName);
        mDescriptionTextView.setText(itemDescription);
        mImageView.setImageResource(itemImageResourceId);
        mPlayButton = findViewById(R.id.vocal);
        dateTextView.setText(date);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.FRANCE);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e(TAG, "This Language is not supported");
                    } else {
                        textToSpeech.speak(itemName, TextToSpeech.QUEUE_FLUSH, null);
                    }
                } else {
                    Log.e(TAG, "Initilization Failed!");
                }
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = mDescriptionTextView.getText().toString();
                if (!TextUtils.isEmpty(description)) {
                    textToSpeech.speak(description, TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    Toast.makeText(MainActivity3.this, "NO TEXT", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        textToSpeech.stop();
    }
}