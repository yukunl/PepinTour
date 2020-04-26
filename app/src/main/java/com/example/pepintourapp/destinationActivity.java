package com.example.pepintourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class destinationActivity extends AppCompatActivity {

    ImageView image;
    TextView txt;
    destination currDest = new destination("temp");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_destination);

        //set image
        image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(currDest.getFileName());

        //set text
        txt = (TextView) findViewById(R.id.textView);
        //txt.setText("this is trash");

        String text = "N/A";
        try{
            InputStream inputStream = getAssets().open("file/t806.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        txt.setText(text);
        //Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();
    }

    public void goToDestinations(View view) {
        //Intent intent = new Intent(this, choose_destination.class);
        //startActivity(intent);
        Toast.makeText(this, "go to destination", Toast.LENGTH_SHORT).show();
    }

    public void goToMap(View view) {
        //implement once map page is made
        /*
        * Intent intent = new Intent (this, Main2Activity.class);
            intent.putExtra("message", s);
            startActivity(intent);
         */
        Toast.makeText(this, "go to map", Toast.LENGTH_SHORT).show();
    }
}

//package com.example.pepintourapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//public class destinationActivity extends AppCompatActivity {
//
//    destination currDest = new destination("temp");
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //set image
//        ImageView img= (ImageView) findViewById(R.id.imageView);
//        img.setImageResource();
//
//        // set text
//        EditText myTextField = (EditText) findViewById(R.id.editText);
//        myTextField.setText(currDest.getDescription());
//        setContentView(R.layout.activity_destination);
//    }
//
//    public void backFunction(View view) {
//        Intent intent = new Intent(this, choose_destination.class);
//        startActivity(intent);
//    }
//
//     public void goToDestinations(View view) {
//        //Intent intent = new Intent(this, choose_destination.class);
//        //startActivity(intent);
//        Toast.makeText(this, "go to destination", Toast.LENGTH_SHORT).show();
//    }
//
//    public void showFunction(View view) {
//        //implement once map page is made
//        /*
//        * Intent intent = new Intent (this, Main2Activity.class);
//            intent.putExtra("message", s);
//            startActivity(intent);
//         */
//    }
//        public void goToMap(View view) {
//        //implement once map page is made
//        /*
//        * Intent intent = new Intent (this, Main2Activity.class);
//            intent.putExtra("message", s);
//            startActivity(intent);
//         */
//        Toast.makeText(this, "go to map", Toast.LENGTH_SHORT).show();
//    }
//
//}