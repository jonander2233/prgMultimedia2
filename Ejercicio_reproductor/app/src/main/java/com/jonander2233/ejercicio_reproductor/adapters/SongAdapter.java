package com.jonander2233.ejercicio_reproductor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jonander2233.ejercicio_reproductor.R;
import com.jonander2233.ejercicio_reproductor.models.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private List<Song> songs;
    private Context context;

    public SongAdapter(List<Song> songs, Context context) {
        this.songs = songs;
        this.context = context;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.bind(song);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView infoSong;
        private TextView artist;
        private TextView album;

        public SongViewHolder(View itemView) {
            super(itemView);
            infoSong = itemView.findViewById(R.id.info_song);
        }

        public void bind(Song song) {
            infoSong.setText(song.getTitle());
            artist.setText(song.getArtist());
            album.setText(song.getAlbum());
        }
    }
}
