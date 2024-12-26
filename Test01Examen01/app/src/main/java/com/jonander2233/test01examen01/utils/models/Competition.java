package com.jonander2233.test01examen01.utils.models;

import java.io.Serializable;
import java.util.List;

/**
 * Competition
 * License: 🅮 Public Domain
 *
 * @author Germán Gascón <ggascon@gmail.com>
 * @version 0.1, 2024-02-21
 * @since 0.1, 2024-02-21
 **/
public class Competition implements Serializable {
    private final int id;
    private final String name;
    private final List<Card> cards;
    private final List<Player> players;
    private final List<Match> matches;

    public Competition(int id, String name, List<Player> players, List<Card> cards, List<Match> matches) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.cards = cards;
        this.matches = matches;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", players=" + players +
                ", cards=" + cards +
                ", matches=" + matches +
                '}';
    }
}
