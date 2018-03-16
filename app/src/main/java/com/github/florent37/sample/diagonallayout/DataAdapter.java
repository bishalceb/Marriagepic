package com.github.florent37.sample.diagonallayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by BISHAL on 13-03-2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Adapterimage> android_versions;
    public Context context;
    int lastPosition = -1;

    public DataAdapter(Context context,ArrayList<Adapterimage> adapterimage) {
        this.context = context;
        this.android_versions = adapterimage;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_image, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
      //  viewHolder.tv_android.setText(android_versions.get(position).getAndroid_version_name());
      //  Picasso.with(context).load(R.mipmap.ic_launcher).resize(120, 60).into(viewHolder.img_android);
        Glide.with(context)
                .load(android_versions.get(position).getAndroid_image_url()) // image url
                .placeholder(R.mipmap.ic_launcher) // any placeholder to load at start
                .error(R.mipmap.ic_launcher)  // any image in case of error
                .centerCrop()
                .into(viewHolder.img_android);
       // if(position >lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.image_animation);
            viewHolder.itemView.startAnimation(animation);
            lastPosition = position;
     //   }
    }

    @Override
    public int getItemCount() {
        return android_versions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //TextView tv_android;
        KenBurnsView img_android;
        public ViewHolder(View view) {
            super(view);

         //   tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (KenBurnsView)view.findViewById(R.id.img_android);
        }
    }

    public interface ClickListener{
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private DataAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final DataAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
