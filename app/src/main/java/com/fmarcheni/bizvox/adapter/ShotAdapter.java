package com.fmarcheni.bizvox.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fmarcheni.bizvox.R;
import com.fmarcheni.bizvox.entity.Shot;
import com.fmarcheni.bizvox.util.StringUtils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Flavio on 03/07/2015.
 */
public class ShotAdapter extends RecyclerView.Adapter<ShotAdapter.ShotViewHolder> {

    List<Shot> shots =  new ArrayList<>();
    RecyclerViewAdapterCallBack callback;

    public ShotAdapter(RecyclerViewAdapterCallBack callback) {
        this.callback = callback;
    }

    public interface RecyclerViewAdapterCallBack {
        void onRecyclerViewElementClicked(View view, int position, Shot element);
    }
    public void addOShots(List<Shot> shots) {
        for (Shot s : shots) {
            if (!this.shots.contains(s))
                this.shots.add(s);
        }
        super.notifyDataSetChanged();
    }

    public void setShots(List<Shot> shots) {
        this.shots = shots;
        super.notifyDataSetChanged();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ShotViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_item, parent, false);
        return new ShotViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(ShotViewHolder shotViewHolder, final int position) {
         shotViewHolder.bind(shots.get(position));
    }


    public interface OnEventListner{
            public void onClick(Shot shot);
    }


    @Override
    public int getItemCount() {
        return shots.size();
    }

    public  class ShotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecyclerViewAdapterCallBack callback;
        private Shot shot;

        @InjectView(R.id.views_count)
        TextView viewsCount;
        @InjectView(R.id.tittle)
        TextView title;
        @InjectView(R.id.created_at)
        TextView createdAt;
        @InjectView(R.id.shot_photo_small)
        ImageView image;

        public ShotViewHolder(View itemView, RecyclerViewAdapterCallBack callback) {
            super(itemView);
            this.callback = callback;
            ButterKnife.inject(this, itemView);
            this.itemView.setOnClickListener(this);
        }
        public void bind(Shot shot) {
            this.shot = shot;
            Picasso.with(image.getContext()).load(shot.getImages().getNormal()).centerCrop().fit().into(image);
            viewsCount.setText(shot.getViews_count().toString());
            title.setText(shot.getTitle());
            createdAt.setText(StringUtils.getFormattedDate(shot.getCreated_at(), "yyyy-MM-dd'T'HH:mm:ss'Z'"));
        }

        @Override
        public void onClick(View v) {
            this.callback.onRecyclerViewElementClicked(this.itemView, this.getAdapterPosition(), shot);
        }
    }


}



