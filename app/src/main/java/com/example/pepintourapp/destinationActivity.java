package com.example.pepintourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class destinationActivity extends AppCompatActivity {

    destination currDest = new destination("temp", "temp");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView img= (ImageView) findViewById(R.id.imageView);
        img.setImageResource(currDest.getImage());
        EditText myTextField = (EditText) findViewById(R.id.editText);
        myTextField.setText(currDest.getDescription());
        setContentView(R.layout.activity_destination);
    }

    public void backFunction(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showFunction(View view) {
        //implement once map page is made
        /*
        * Intent intent = new Intent (this, Main2Activity.class);
            intent.putExtra("message", s);
            startActivity(intent);
         */
    }
}
