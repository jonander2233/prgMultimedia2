package com.jonander2233.test01examen01.utils.parsers;

import android.content.Context;

import com.jonander2233.test01examen01.R;
import com.jonander2233.test01examen01.utils.models.Card;
import com.jonander2233.test01examen01.utils.models.Competition;
import com.jonander2233.test01examen01.utils.models.Match;
import com.jonander2233.test01examen01.utils.models.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CompetitionParser {
    private Competition competition;
    private final InputStream competitionFile;
    private static SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public CompetitionParser(Context context) {
        this.competitionFile = context.getResources().openRawResource(R.raw.competition);
    }
    public boolean parse(){
        boolean parsed = false;
        String json = null;
        try {
            int size = competitionFile.available();
            byte[] buffer = new byte[size];
            competitionFile.read(buffer);
            competitionFile.close();
            json = new String (buffer, StandardCharsets.UTF_8);

            JSONTokener tokener = new JSONTokener(json);
            JSONObject rootObject = new JSONObject(tokener);

            int id = rootObject.getInt("id");
            String name = rootObject.getString("name");

            //cards array
            JSONArray cardsArray = rootObject.getJSONArray("cards");
            List<Card> cards = new ArrayList<>();
            for (int i = 0; i < cardsArray.length(); i++) {
                JSONObject card = cardsArray.getJSONObject(i);
                int arena = card.getInt("arena");
                String description = card.getString("description");
                int elixir = card.getInt("elixir");
                int hitSpeed = card.getInt("hitSpeed");
                long idCard = card.getLong("id");
                String image = card.getString("image");
                String nameCard = card.getString("name");
                int range = card.getInt("range");
                String rarity = card.getString("rarity");
                int speed = card.getInt("speed");
                String type = card.getString("type");
                cards.add(new Card(idCard,nameCard,elixir,type,rarity,arena,image,description,speed,hitSpeed,range));
            }
            //players array
            JSONArray playersArray = rootObject.getJSONArray("players");
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < playersArray.length(); i++) {
                JSONObject player = playersArray.getJSONObject(i);
                String birthdate = player.getString("birthdate");
                String country = player.getString("country");
                String flag = player.getString("flag");
                int idPlayer = player.getInt("id");
                String playerName = player.getString("name");
                String playerSurname = player.getString("surname");
                String team = player.getString("team");
                players.add(new Player(idPlayer,playerName,playerSurname,birthdate,country,flag,team));
            }
            JSONArray matchesArray = rootObject.getJSONArray("matches");
            List<Match> matches = new ArrayList<>();
            for (int i = 0; i < matchesArray.length(); i++) {
                JSONObject match = matchesArray.getJSONObject(i);
                int matchId = match.getInt("id");
                int crownsPlayer1 = match.getInt("crownsPlayer1");
                int crownsPlayer2 = match.getInt("crownsPlayer2");
                int idPlayer1 = match.getInt("idPlayer1");
                int idPlayer2 = match.getInt("idPlayer2");
                matches.add(new Match(matchId,idPlayer1,crownsPlayer1,idPlayer2,crownsPlayer2));
            }
            competition = new Competition(id,name,players,cards,matches);
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public Competition getCompetition() {
        return competition;
    }
}
