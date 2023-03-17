package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {



    ArrayList<Item> notes;
    Dialog dialog;
    RecyclerView recyclerView;
    EditText txt;
    private static final String KEY_ITEM_LIST = "item_list";

    noteadapter adapter = new noteadapter(notes,this)   ;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recycler_view);
        notes = new ArrayList<Item>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        recyclerView.setAdapter(new noteadapter(notes,this));
//

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogue);
        Button btnValider = dialog.findViewById(R.id.button);
        txt = dialog.findViewById(R.id.textView7);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                notes.add(new Item("Amira Mekki",txt.getText().toString(),R.drawable.girl));

                notes.add(new Item("AISSOU Souha",txt.getText().toString(),R.drawable.pfp));
                recyclerView.setAdapter(new noteadapter(notes,MainActivity2.this));
                dialog.dismiss();}
        });

        if (savedInstanceState != null) {
            notes = savedInstanceState.getParcelableArrayList("myKey");
        }


    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_setting){
            dialog.show();
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_ITEM_LIST,  notes);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ArrayList<Item> itemList = savedInstanceState.getParcelableArrayList(KEY_ITEM_LIST);




        if (itemList != null) {

            recyclerView.setAdapter(new noteadapter(itemList, this));
        }


    }





}

