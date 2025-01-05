package com.jonander2233.test01examen01.utils.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonander2233.test01examen01.R;
import com.jonander2233.test01examen01.utils.models.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchHolder> {
    public interface MatchDataListener{
        List<Match> getMatches();
    }
    private List<Match> matches;
    private MatchDataListener matchDataListener;

    public MatchAdapter(MatchDataListener matchDataListener) {
        this.matchDataListener = matchDataListener;
        this.matches = new ArrayList<>();
        loadMatches();
    }
    private void loadMatches(){
        if(matchDataListener != null)
            this.matches = matchDataListener.getMatches();
    }
    @NonNull
    @Override
    public MatchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MatchHolder extends RecyclerView.ViewHolder{
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
