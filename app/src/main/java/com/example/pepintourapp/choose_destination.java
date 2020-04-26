package com.example.pepintourapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class choose_destination extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private destinationAdaptor dAdaptor;
    public List<destination> dList = new ArrayList<>();
    private TextSwitcher dtitle;





    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_destination);

        // Bundle bundle = getIntent().getExtras();
        initData();
        dtitle = findViewById(R.id.title);
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

        dAdaptor = new destinationAdaptor(dList, this);
        coverFlow = (FeatureCoverFlow)findViewById(R.id.coverFlow);
        coverFlow.setAdapter(dAdaptor);
        //Button moreButton = (Button) super.findViewById(R.id.more);

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                dtitle.setText(dList.get(position).getName());
            }

            @Override
            public void onScrolling() {

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

    public void onMoreClick(View v){
        Intent gotoActivity = new Intent(this, destinationActivity.class);
        startActivity(gotoActivity);

           }



 private void initData(){
     dList.add(new destination("little cabin", "https://static.wixstatic.com/media/8dcdcf_c34dbfd10b9f4860a47974bdefdfcca2~mv2.jpg"));
     dList.add(new destination("lake","https://lh3.googleusercontent.com/proxy/46YlFxMIsl9CvvF8Xk5gAJ0mXfQAnbahKQjTdhUL2i8N6ac6M8ebG99-634kv6bmHjcxkNl1OGCKmatnp18-Dw"));
     dList.add(new destination("little cabin", "https://static.wixstatic.com/media/68103f_5e2be2a3ad074bed92dcafe92e4fa272~mv2.jpg"));
 }


}