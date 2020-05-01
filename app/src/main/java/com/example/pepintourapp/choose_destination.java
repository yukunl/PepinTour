package com.example.pepintourapp;

import android.content.Intent;
import android.graphics.Color;
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
    private TextSwitcher dtitle, ihour, iaddress, icontact;
    private String namedest;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_destination);

        // Bundle bundle = getIntent().getExtras();
        initData();
        dAdaptor = new destinationAdaptor(common.dList, this);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverFlow);
        dtitle = findViewById(R.id.destinationTitle);
        ihour = findViewById(R.id.hourinfo);
        iaddress = findViewById(R.id.addrInfo);
        icontact = findViewById(R.id.Contactinfo);
        dtitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(choose_destination.this);
                TextView txt = (TextView) inflater.inflate(R.layout.layout_title, null);
                return txt;
            }
        });

        ihour.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(choose_destination.this);
                TextView txt = (TextView) inflater.inflate(R.layout.text_layout, null);
                txt.setTextColor(Color.GRAY);
                return txt;
            }
        });
        iaddress.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(choose_destination.this);
                TextView txt = (TextView) inflater.inflate(R.layout.text_layout, null);
                txt.setTextColor(Color.GRAY);
                return txt;
            }
        });
        icontact.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(choose_destination.this);
                TextView txt = (TextView) inflater.inflate(R.layout.text_layout, null);
                txt.setTextColor(Color.GRAY);
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
                ihour.setText(common.dList.get(position).gethr());
                iaddress.setText(common.dList.get(position).getAddr());
                icontact.setText(common.dList.get(position).getContact());
                namedest = common.dList.get(position).getName();
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

        TextView text = (TextView) dtitle.getCurrentView();
        namedest = text.getText().toString();
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log.i(" destinat name", namedest);
                Toast.makeText(getBaseContext(), namedest + " is added ", Toast.LENGTH_SHORT).show();


            }
        });

    }
        private void initData () {
            common.dList.add(new destination("Laura Ingalls Wilder Museum", "https://live.staticflickr.com/4459/37978714501_d7b75fd7e2_b.jpg", "The Laura Ingalls Wilder Museum features many items Laura and her family would have recognized and recalls the era in which she lived.", "10:00am - 5:00 pm", "306 3rd St, Pepin, WI 54759", "715-513-6383"));
            common.dList.add(new destination("United Methodist Church", "https://faithstreet.s3.amazonaws.com/uploads/church/50083677e412b00d40041dd1/church_image/5229ce4aab9f99f20500009d/medium_a52d48f22a6a28557ec9.jpg", "The Methodist church building was built in 1875 to replace the original frame structure which had been built on the same block, over on Pine Street, in 1856.", "8:00am - 5:00 pm", "504 2nd St, Pepin, WI 54759", "715-442-3191"));
            common.dList.add(new destination("Dougieland Studios Pepin", "https://images.squarespace-cdn.com/content/v1/509d2862e4b05a733d0c6065/1587156549398-3Z8LALV1M81J7YEDCM06/ke17ZwdGBToddI8pDm48kEpVg-ILAPna1wRh-xAJ9fRZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwEv36x-EUL2-BSQ5feDhwGCbXuJBFqZ-erYzVouT8yOb9TwqchglLQOCYTRn7ZGxI/image-asset.jpeg", "The Masonic Lodge built this structure in 1887 to replace the smaller building on First Street. This building was built in the style of the period that Laura's family would have seen. ", "11:00am - 5:00 pm", "402 Second Street, Pepin, WI, 54759", "www.dougiepadilla.com"));
            common.dList.add(new destination("Harbor View Cafe", "https://chindeep.com/wp-content/uploads/2008/05/exterior-dsc_0024.jpg", "The oldest part of this building dates back to the 19th century. There was an inn on this corner as early as the 1850s, but early accounts suggest that the original building was replaced in the 1890s. Along the lakeshore, were the general store of Patrick McInerney and the residence and livery stable of P.N. Tuttle family.", "11:00am - 7:00 pm", "314 1st St, Pepin, WI 54759", "715-442-3893"));
            common.dList.add(new destination("404 Coffee Shop", "https://i.pinimg.com/originals/a6/04/97/a604973e1937bc6354a41d103daf2bf7.jpg", "Isabelle Richards had her boarding house and store built on the lakeshore in about 1880, and it was moved across the street when the railroad was built.", "8:00am - 2:00 pm", "404 1st St, Pepin, WI 54759", "715-215-0132"));
        }


    }
