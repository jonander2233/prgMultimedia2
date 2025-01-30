package com.jonander2233.ejercicio_reproductor;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

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
import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity implements SongAdapter.OnSelectedSongClickListener {
    private List<Song> songs;
    private SongParser songParser;
    private Song selectedSong;
    private MediaPlayer mediaPlayer;
    private ExecutorService executorService;
    private ImageButton playPauseButton;
    private ImageButton nextButton;
    private ImageButton backButton;
    private SeekBar seekBar;
    private ImageView songImageView;
    private TextView songInfoTextView;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable updateSeekBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songParser= new  SongParser(this);
        if(songParser.parse()){
            songs = songParser.getSongs();
        }
        setupRecyclerView();
        setupButtons();
        songImageView = findViewById(R.id.imageView);
        songInfoTextView = findViewById(R.id.info_song);
        setupSeekBar();
        if(!songs.isEmpty()){
            this.selectedSong = songs.get(0);
            setPlayer();
            updateSongInfo();
            mediaPlayer.pause();
        }

    }

    @Override
    public void onSelectedSong(Song song) {
        this.selectedSong = song;
        setPlayer();
        updateSongInfo();
        playPauseButton.setImageResource(R.drawable.pause);
    }

    private void setPlayer(){
        if (selectedSong != null) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

            mediaPlayer = MediaPlayer.create(this, selectedSong.getResource()); // Usar el recurso de raw
            mediaPlayer.start();

            // Listener para liberar el reproductor al terminar la canción
            mediaPlayer.setOnCompletionListener(mp -> {
                mediaPlayer.release();
                mediaPlayer = null;
                handler.removeCallbacks(updateSeekBar);
            });
            seekBar.setMax(mediaPlayer.getDuration());
            updateSeekBar();
        }
    }
    private void updateSeekBar() {
        if (mediaPlayer != null) {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(updateSeekBar, 1000);
        }
    }
    private void setupSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null) {
                    handler.removeCallbacks(updateSeekBar);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null) {
                    updateSeekBar();
                }
            }
        });

        updateSeekBar = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(this, 1000);
                }
            }
        };
    }
    private void updateSongInfo() {
        if (selectedSong != null) {
            songInfoTextView.setText(selectedSong.getArtist() + " - " + selectedSong.getTitle());
            if (selectedSong.getImageCover() != null) {
                songImageView.setImageBitmap(selectedSong.getImageCover());
            } else {
                songImageView.setImageResource(R.drawable.default_cover); // Imagen por defecto si no hay cover
            }
        }
    }
    private void setupButtons() {
        playPauseButton = findViewById(R.id.play_pause_button);
        nextButton = findViewById(R.id.next_button);
        backButton = findViewById(R.id.back_button);
        seekBar = findViewById(R.id.seekBar);

        playPauseButton.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playPauseButton.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    playPauseButton.setImageResource(R.drawable.pause);
                }
            }
        });

        nextButton.setOnClickListener(v -> {
            int currentIndex = songs.indexOf(selectedSong);
            if (currentIndex < songs.size() - 1) {
                onSelectedSong(songs.get(currentIndex + 1));
            }
        });

        backButton.setOnClickListener(v -> {
            int currentIndex = songs.indexOf(selectedSong);
            if (currentIndex > 0) {
                onSelectedSong(songs.get(currentIndex - 1));
            }
        });
    }
    private void setupRecyclerView() {
        RecyclerView rvSongList = findViewById(R.id.rvSongs);
        SongAdapter songAdapter = new SongAdapter(songs,this,this);
        rvSongList.setAdapter(songAdapter);
        rvSongList.setHasFixedSize(true);
        rvSongList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        executorService.shutdown();
        handler.removeCallbacks(updateSeekBar);
    }
}