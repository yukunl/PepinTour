package com.example.pepintourapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
 private List<destination> dList = new ArrayList<>();
 private TextSwitcher dtitle;



 protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     setContentView(R.layout.choose_destination);
    // Bundle bundle = getIntent().getExtras();
     //initData();
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

     coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
         @Override
         public void onScrolledToPosition(int position) {
             dtitle.setText(dList.get(position).getName());
         }

         @Override
         public void onScrolling() {

         }
     });

 }

/*
 private void initData(){
     dList.add(new destination("little cabin", "https://sitesandstories.wordpress.com/2011/08/02/looking-for-laura/pepin-cabin-side-view/"));
     dList.add(new destination("little cabin", "https://sitesandstories.wordpress.com/2011/08/02/looking-for-laura/pepin-cabin-side-view/"));
     dList.add(new destination("little cabin", "https://sitesandstories.wordpress.com/2011/08/02/looking-for-laura/pepin-cabin-side-view/"));

 }
*/

}
