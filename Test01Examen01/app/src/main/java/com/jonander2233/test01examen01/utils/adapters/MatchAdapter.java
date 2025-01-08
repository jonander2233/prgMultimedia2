package com.jonander2233.test01examen01.utils.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonander2233.test01examen01.R;
import com.jonander2233.test01examen01.utils.SortObjects;
import com.jonander2233.test01examen01.utils.models.Competition;
import com.jonander2233.test01examen01.utils.models.Match;
import com.jonander2233.test01examen01.utils.models.Player;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchHolder> {
    public interface MatchDataListener{
        Competition getCompetition();
    }
    private MatchDataListener matchDataListener;
    private Competition competition;
    private List<Player> players;
    public MatchAdapter(MatchDataListener matchDataListener) {
        this.matchDataListener = matchDataListener;
        this.players = new ArrayList<>();
        loadCompetition();
        loadPlayers();
    }

    private void loadCompetition(){
        if(matchDataListener != null)
            this.competition = matchDataListener.getCompetition();
    }

    private void loadPlayers(){
        if(this.competition != null)
            this.players = competition.getPlayers();
    }
    @NonNull
    @Override
    public MatchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking,null);
        MatchHolder matchHolder = new MatchHolder(itemView);
        return (MatchHolder) matchHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchHolder holder, int position) {
        holder.bind(position +1,players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class MatchHolder extends RecyclerView.ViewHolder{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaActual;
        LocalDate fechaNac;
        ImageView ivCountryFlag;
        TextView tvRank;
        TextView tvTeam;
        TextView tvScore;
        TextView tvPlayer;
        TextView tvAge;
        public MatchHolder(@NonNull View itemView) {
            super(itemView);
            this.ivCountryFlag = itemView.findViewById(R.id.ivCountryFlag);
            this.tvRank = itemView.findViewById(R.id.tvRank);
            this.tvTeam = itemView.findViewById(R.id.tvTeam);
            this.tvScore = itemView.findViewById(R.id.tvScore);
            this.tvPlayer = itemView.findViewById(R.id.tvPlayer);
            this.tvAge = itemView.findViewById(R.id.tvAge);

        }
        public void bind(int rank ,Player player){
            String imageString = "_" + player.getFlag();
            int imageResourceId = itemView.getResources().getIdentifier(imageString,"drawable",itemView.getContext().getPackageName());
            this.ivCountryFlag.setImageResource(imageResourceId);
            this.tvRank.setText(String.valueOf(rank));
            this.tvTeam.setText(player.getTeam());
            this.tvScore.setText(String.valueOf(SortObjects.getWins(player)));
            this.tvPlayer.setText(player.getName());
            String fechaNacStr = player.getBirthdate();
            fechaNac = LocalDate.parse(fechaNacStr,formatter);
            fechaActual = LocalDate.now();
            int edad = Period.between(fechaNac,fechaActual).getYears();
            this.tvAge.setText(String.valueOf(edad));
        }

    }
}
