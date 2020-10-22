package net.xdclass.online_xdclass.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoOrderRequest {

    @JsonProperty("video_id")
    private Integer videoId;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}
