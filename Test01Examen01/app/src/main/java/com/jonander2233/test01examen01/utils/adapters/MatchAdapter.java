package com.jonander2233.test01examen01.utils.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonander2233.test01examen01.R;
import com.jonander2233.test01examen01.utils.models.Competition;
import com.jonander2233.test01examen01.utils.models.Match;
import com.jonander2233.test01examen01.utils.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchHolder> {
    public interface MatchDataListener{
        Competition getCompetition();
    }
    private MatchDataListener matchDataListener;
    private Competition competition;
    private List<Match> matches;
    public MatchAdapter(MatchDataListener matchDataListener) {
        this.matchDataListener = matchDataListener;
        this.matches = new ArrayList<>();
        loadCompetition();
        loadMatches();
    }

    private void loadCompetition(){
        if(matchDataListener != null)
            this.competition = matchDataListener.getCompetition();
    }

    private void loadMatches(){
        if(this.competition != null)
            this.matches = competition.getMatches();
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
        holder.bind(matches.get(position));
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class MatchHolder extends RecyclerView.ViewHolder{
        ArrayList<Player> players = new ArrayList<>();
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
        public void bind(Match match){
        }

    }
}
