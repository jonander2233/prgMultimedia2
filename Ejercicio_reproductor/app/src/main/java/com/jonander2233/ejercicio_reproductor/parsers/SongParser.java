package com.jonander2233.ejercicio_reproductor.parsers;

import static androidx.core.util.TimeUtils.formatDuration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;

import com.jonander2233.ejercicio_reproductor.R;
import com.jonander2233.ejercicio_reproductor.models.Song;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SongParser {
    private Resources resources;
    private List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }

    public SongParser(Context context) {
        this.songs = new ArrayList<Song>();
        this.resources = context.getResources();
    }

    public boolean parse(){
        boolean parsed = false;
        Field[] fields = R.raw.class.getFields();
        for(Field field : fields){
            try {
                int rawResId = field.getInt(null);
                Song song = retrieveSongFromRaw(rawResId);
                songs.add(song);
                parsed = true;
            } catch (IllegalAccessException | IOException e) {
                e.printStackTrace();
            }
        }
        return parsed;
    }

    public Song retrieveSongFromRaw(int rawResId) throws IOException {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();

        try {
            // Configura el archivo de audio
            try (android.content.res.AssetFileDescriptor afd = resources.openRawResourceFd(rawResId)) {
                if (afd == null) {
                    throw new IOException("No se pudo abrir el recurso raw.");
                }
                mmr.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            }

            // Extrae los metadatos
            String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            String album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            String durationMs = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            byte[] coverImage = mmr.getEmbeddedPicture();

            Bitmap coverBitmap = null;
            if(coverImage != null) {
                coverBitmap = BitmapFactory.decodeByteArray(coverImage, 0, coverImage.length);
            }

            // Formatea la duración
            String formattedDuration = formatDuration(durationMs);

            // Crea y retorna el objeto Song
            return new Song(
                    title != null ? title : "Unknown Title",
                    artist != null ? artist : "Unknown Artist",
                    album != null ? album : "Unknown Album",
                    formattedDuration,
                    coverBitmap,
                    rawResId
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error al procesar los metadatos del recurso raw.", e);
        } finally {
            mmr.release();
        }
    }
    private static String formatDuration(String durationMs) {
        if (durationMs == null) return "00:00";

        try {
            int duration = Integer.parseInt(durationMs);
            int minutes = duration / 1000 / 60;
            int seconds = (duration / 1000) % 60;
            return String.format("%02d:%02d", minutes, seconds);
        } catch (NumberFormatException e) {
            return "00:00";
        }
    }
}
