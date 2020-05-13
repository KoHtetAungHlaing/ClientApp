package com.bayintnaung.clientapp;

public class EpisodeModel {
    public String episodeName,episodeVideo,episodeSeries,createdAt;

    public EpisodeModel(String episodeName, String episodeVideo, String episodeSeries) {
        this.episodeName = episodeName;
        this.episodeVideo = episodeVideo;
        this.episodeSeries = episodeSeries;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public EpisodeModel(String episodeName, String episodeVideo, String episodeSeries, String createdAt) {
        this.episodeName = episodeName;
        this.episodeVideo = episodeVideo;
        this.episodeSeries = episodeSeries;
        this.createdAt = createdAt;
    }

    public EpisodeModel() {
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getEpisodeVideo() {
        return episodeVideo;
    }

    public void setEpisodeVideo(String episodeVideo) {
        this.episodeVideo = episodeVideo;
    }

    public String getEpisodeSeries() {
        return episodeSeries;
    }

    public void setEpisodeSeries(String episodeSeries) {
        this.episodeSeries = episodeSeries;
    }
}
