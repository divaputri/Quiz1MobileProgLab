package com.example.quizlab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class Details extends AppCompatActivity{

    private TextView id;
    private ImageView imageView;
    private TextView first_name;
    private TextView last_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        id = findViewById(R.id.id_text);
        imageView = findViewById(R.id.image_view);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);

        Bundle extras = getIntent().getExtras();

        int userID = extras.getInt("Id");
        String userFirstName = extras.getString("firstName");
        String userLastName = extras.getString("lastName");
        String userAvatar = extras.getString("avatar");

        Glide.with(this)
                .load(userAvatar)
                .into(imageView);

        id.setText(String.valueOf(userID));
        first_name.setText(userFirstName);
        last_name.setText(userLastName);
    }
}
