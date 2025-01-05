package com.jonander2233.test01examen01.utils;

import com.jonander2233.test01examen01.utils.models.Card;
import com.jonander2233.test01examen01.utils.models.Competition;
import com.jonander2233.test01examen01.utils.models.Match;
import com.jonander2233.test01examen01.utils.models.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortObjects {
    private static Map<String, Integer> ordenRarezas;
    private static Map<Player, Integer> victories;
    static {

        ordenRarezas = new HashMap<>();
        ordenRarezas.put("Champion", 5);
        ordenRarezas.put("Legendary", 4);
        ordenRarezas.put("Epic", 3);
        ordenRarezas.put("Rare", 2);
        ordenRarezas.put("Common", 1);
    }
    public static void orderCards(List<Card> cardList){
        cardList.sort(Comparator.comparingInt(Card::getElixir).thenComparing(o -> ordenRarezas.get(o.getRarity())).thenComparing(Card::getName));
    }
    public static void orderPlayers(Competition competition){
        List<Match> matches = competition.getMatches();
        List<Player> players = competition.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            Player actualP = players.get(i);
        }
    }
}
