package com.bhushansingh.pictureinpicturemodeexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bhushansingh.pictureinpicturemodeexample.model.VideoModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityAdapter.ItemClickListener {


    private ArrayList<VideoModel> videosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateVideoList();

        recyclerView.setAdapter(new MainActivityAdapter(videosList, this));

    }


    private void updateVideoList() {
        videosList.add(new VideoModel("Big Buck Bunny", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        videosList.add(new VideoModel("We are going on bull run", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"));
        videosList.add(new VideoModel("Volkswagen GTI Review", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4"));
        videosList.add(new VideoModel("For Bigger Blazes", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"));
        videosList.add(new VideoModel("Subaru Outback On Street And Dirt", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"));
        videosList.add(new VideoModel("What care can you get for ten grand?", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"));
    }

    @Override
    public void itemClick(VideoModel model) {

        Intent intent = new Intent(this, PictureInPictureActivity.class).putExtra("videoURL", model.getVideoUrl());
        startActivity(intent);
    }
}
