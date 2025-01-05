package com.jonander2233.test01examen01.utils.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonander2233.test01examen01.MainActivity;
import com.jonander2233.test01examen01.R;
import com.jonander2233.test01examen01.utils.models.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * CardAdapter
 * "bindea" los datos de las cartas en la vista
 * además obtiene las cartas desde el listener
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder>{
    //interfaz para obtener las cartas desde el listener
    public interface CardsDataListener{
        List<Card> getCards();
    }

    private List<Card> cards;
    //listener para obtener las cartas
    private CardsDataListener cardsDataListener;

    /**
     * Constructor que recibe el listener
     * @param listener es la clase que implementa la interfaz CardsDataListener, en este caso MainActivity
     */
    public CardAdapter(CardsDataListener listener) {
        this.cardsDataListener = listener;
        this.cards = new ArrayList<>(); // Inicializamos una lista vacía
        loadCards(); // Cargamos las cartas desde el listener, aqui se llama al metodo getCards() de la clase MainActivity
    }

    /**
     * Metodo que actualiza las cartas, aqui se llama al metodo getCards() de la clase MainActivity
     */
    public void updateCards() {
        if (cardsDataListener != null) {
            this.cards = cardsDataListener.getCards(); // Obtén las cartas desde el listener
            notifyDataSetChanged(); // Notifica los cambios al RecyclerView
        }
    }

    private void loadCards() {
        if (cardsDataListener != null) {
            this.cards = cardsDataListener.getCards(); // Inicializa las cartas desde el listener
        }
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,null);
        CardHolder cardHolder = new CardHolder(itemView);
        return (CardHolder) cardHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        holder.bind(cards.get(position));
    }


    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class CardHolder extends RecyclerView.ViewHolder{
        ImageView ivCard;
        TextView tvCardName;
        TextView tvRarity;
        TextView tvArena;
        TextView tvElixir;
        TextView tvType;

        public CardHolder(@NonNull View itemView) {
            super(itemView);
            this.ivCard = itemView.findViewById(R.id.ivCard);
            this.tvCardName = itemView.findViewById(R.id.tvCardName);
            this.tvRarity = itemView.findViewById(R.id.tvRarity);
            this.tvArena = itemView.findViewById(R.id.tvArena);
            this.tvElixir = itemView.findViewById(R.id.tvElixir);
            this.tvType = itemView.findViewById(R.id.tvType);
        }

        public void bind(Card card){
            String imageString = card.getImage();
            int imageResourceId = itemView.getResources().getIdentifier(imageString, "drawable", itemView.getContext().getPackageName());
            ivCard.setImageResource(imageResourceId);
            tvCardName.setText(card.getName());
            tvRarity.setText(card.getRarity());
            tvArena.setText(String.valueOf(card.getArena()));
            tvElixir.setText(String.valueOf(card.getElixir()));
            tvType.setText(card.getType());

        }
    }
}
