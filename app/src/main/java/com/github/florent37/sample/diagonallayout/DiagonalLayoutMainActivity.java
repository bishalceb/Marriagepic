package com.github.florent37.sample.diagonallayout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DiagonalLayoutMainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private final String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };

    private final String android_image_urls[] = {
         /*   "https://api.androidhive.info/images/glide/small/deadpool.jpg",
            "https://api.androidhive.info/images/glide/small/bvs.jpg",
            "https://api.androidhive.info/images/glide/small/cacw.jpg",
            "https://api.androidhive.info/images/glide/small/bourne.jpg",
            "https://api.androidhive.info/images/glide/small/squad.jpg",
            "https://api.androidhive.info/images/glide/small/doctor.jpg",
            "https://api.androidhive.info/images/glide/small/dory.jpg",
            "https://api.androidhive.info/images/glide/small/hunger.jpg",
            "https://api.androidhive.info/images/glide/small/hours.jpg",
            "https://api.androidhive.info/images/glide/small/ipman3.jpg"*/
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagonallayout_activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=(RecyclerView) findViewById(R.id.recyler_image);
        recyclerView.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        final ArrayList adapterimage=prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(),adapterimage);
      //  GalleryAdapter adapter=new GalleryAdapter(getApplicationContext(),adapterimage);
        recyclerView.setAdapter(adapter);


   recyclerView.addOnItemTouchListener(new DataAdapter.RecyclerTouchListener(getApplicationContext(),recyclerView, new DataAdapter.ClickListener() {
       @Override
       public void onClick(View view, int position) {
           Bundle bundle=new Bundle();
           bundle.putSerializable("images",adapterimage);
           bundle.putInt("position",position);
           FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
           SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
           newFragment.setArguments(bundle);
          newFragment.show(ft,"d");

           //newFragment.show()
       }

       @Override
       public void onLongClick(View view, int position) {

       }
   }));


    }
    private ArrayList prepareData() {
        ArrayList adapter_image = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            Adapterimage adaptrerimage = new Adapterimage();
            adaptrerimage.setAndroid_version_name(android_version_names[i]);
            adaptrerimage.setAndroid_image_url(android_image_urls[i]);
            adapter_image.add(adaptrerimage);
        }
        return adapter_image;
    }
}
