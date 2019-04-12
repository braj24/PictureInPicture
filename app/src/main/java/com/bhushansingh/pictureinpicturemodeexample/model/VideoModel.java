package com.bhushansingh.pictureinpicturemodeexample.model;

public class VideoModel {

    private String videoTitle;
    private String videoUrl;

    public VideoModel(String videoTitle, String videoUrl) {
        this.videoTitle = videoTitle;
        this.videoUrl = videoUrl;
    }


    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
