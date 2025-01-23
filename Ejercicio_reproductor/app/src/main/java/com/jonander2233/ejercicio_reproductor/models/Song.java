package com.jonander2233.ejercicio_reproductor.models;

import android.graphics.Bitmap;

public class Song {
    private final String title;
    private final String artist;
    private final String album;
    private final String duration;
    private final Bitmap imageCover;
    private final int resource;

    public Song(String title, String artist, String album, String duration, Bitmap imageCover, int resource) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.imageCover = imageCover;
        this.resource = resource;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getDuration() {
        return duration;
    }

    public Bitmap getImageCover() {
        return imageCover;
    }

    public int getResource() {
        return resource;
    }
}
