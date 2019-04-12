package com.bhushansingh.pictureinpicturemodeexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhushansingh.pictureinpicturemodeexample.model.VideoModel;

import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    private List<VideoModel> videoList;
    private ItemClickListener itemClickListener;


    public MainActivityAdapter(List<VideoModel> list, ItemClickListener itemClickListener) {

        this.videoList = list;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MainActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item_layout, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityAdapter.ViewHolder viewHolder, int i) {

        final VideoModel videoModel = videoList.get(i);

        viewHolder.title.setText(videoModel.getVideoTitle());
        viewHolder.url.setText(videoModel.getVideoUrl());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.itemClick(videoModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, url;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textView_title);
            url = itemView.findViewById(R.id.textView_video_url);
        }
    }


    public interface ItemClickListener {

        void itemClick(VideoModel model);
    }
}
