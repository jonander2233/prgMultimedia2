package com.jonander2233.ejercicio_reproductor;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonander2233.ejercicio_reproductor.adapters.SongAdapter;
import com.jonander2233.ejercicio_reproductor.models.Song;
import com.jonander2233.ejercicio_reproductor.parsers.SongParser;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SongAdapter.OnSelectedSongClickListener {
    private List<Song> songs;
    private SongParser songParser;
    private Song selectedSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songParser= new  SongParser(this);
        if(songParser.parse()){
            songs = songParser.getSongs();
        }
        setupRecyclerView();
    }

    @Override
    public void onSelectedSong(Song song) {
        this.selectedSong = song;
    }
    private void setPlayer(){

    }
    private void setupRecyclerView() {
        RecyclerView rvSongList = findViewById(R.id.rvSongs);
        SongAdapter songAdapter = new SongAdapter(songs,this);
        rvSongList.setAdapter(songAdapter);
        rvSongList.setHasFixedSize(true);
        rvSongList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}