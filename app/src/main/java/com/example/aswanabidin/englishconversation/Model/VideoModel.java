package com.example.aswanabidin.englishconversation.Model;

/**
 * Created by aswanabidin on 10/20/17.
 */

public class VideoModel {

    String videoUrl;

    public VideoModel(){

    }

    public VideoModel(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
