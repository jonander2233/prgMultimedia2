package com.jonander2233.test01examen01.utils;

import com.jonander2233.test01examen01.utils.models.Card;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortObjects {
    private static Map<String, Integer> ordenRarezas;
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
}
