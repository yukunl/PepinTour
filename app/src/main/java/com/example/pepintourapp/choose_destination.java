package com.example.pepintourapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.internal.service.Common;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class choose_destination extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private destinationAdaptor dAdaptor;
  //  public List<destination> dList = new ArrayList<>();
    private TextSwitcher dtitle;
    private String namedest ;




    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_destination);

        // Bundle bundle = getIntent().getExtras();
        initData();
        dAdaptor = new destinationAdaptor(common.dList, this);
        coverFlow = (FeatureCoverFlow)findViewById(R.id.coverFlow);
        dtitle = findViewById(R.id.dtitle);
        dtitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(choose_destination.this);
                TextView txt = (TextView)inflater.inflate(R.layout.layout_title,null);
                return txt;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
        dtitle.setInAnimation(in);
        dtitle.setOutAnimation(out);


        coverFlow.setAdapter(dAdaptor);
        // dtitle = (TextSwitcher)findViewById(R.id.dtitle);
        //Button moreButton = (Button) super.findViewById(R.id.more);

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                dtitle.setText(common.dList.get(position).getName());
            }

            @Override
            public void onScrolling() {

            }
        });
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(choose_destination.this, destinationActivity.class);
                intent.putExtra("destination_index", position);
                startActivity(intent);
            }
        });
        Button add = findViewById(R.id.button);
        namedest = ((TextView) findViewById(R.id.description)).getText().toString();
          add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), namedest + " is added " , Toast.LENGTH_SHORT ).show();

            }
        });

//        moreButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(getActivity(), destinationActivity.class);
//                startActivity (in);
//            }
//        });

    }

//    public void onMoreClick(View v){
//        Intent gotoActivity = new Intent(this, destinationActivity.class);
//        startActivity(gotoActivity);
//
//           }



 private void initData(){
     common.dList.add(new destination("Laura Ingalls Wilder Museum", "https://live.staticflickr.com/4459/37978714501_d7b75fd7e2_b.jpg", "The Laura Ingalls Wilder Museum features many items Laura and her family would have recognized and recalls the era in which she lived."));
     common.dList.add(new destination("United Methodist Church","https://faithstreet.s3.amazonaws.com/uploads/church/50083677e412b00d40041dd1/church_image/5229ce4aab9f99f20500009d/medium_a52d48f22a6a28557ec9.jpg", "504 Second Street, United Methodist Church The Methodist church building was built in 1875 to replace the original frame structure which had been built on the same block, over on Pine Street, in 1856."));
     common.dList.add(new destination("Dougieland Studios Pepin", "https://images.squarespace-cdn.com/content/v1/509d2862e4b05a733d0c6065/1587156549398-3Z8LALV1M81J7YEDCM06/ke17ZwdGBToddI8pDm48kEpVg-ILAPna1wRh-xAJ9fRZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwEv36x-EUL2-BSQ5feDhwGCbXuJBFqZ-erYzVouT8yOb9TwqchglLQOCYTRn7ZGxI/image-asset.jpeg", "402 Second Street, Dougieland Studios The Masonic Lodge built this structure in 1887 to replace the smaller building on First Street. This building was built in the style of the period that Laura�s family would have seen. "));
     common.dList.add(new destination("Laura Ingalls Wilder Museum", "https://live.staticflickr.com/4459/37978714501_d7b75fd7e2_b.jpg", "The Laura Ingalls Wilder Museum features many items Laura and her family would have recognized and recalls the era in which she lived."));
     common.dList.add(new destination("404 Pepin Shop", "https://i.pinimg.com/originals/a6/04/97/a604973e1937bc6354a41d103daf2bf7.jpg", "400 First Street, 404 Coffee Shop Isabelle Richards had her boarding house and store built on the lakeshore in about 1880, and it was moved across the street when the railroad was built."));
 }


}