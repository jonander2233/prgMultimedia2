package com.jonander2233.test01examen01.utils.models;

import java.io.Serializable;

/**
 * Match
 * License: 🅮 Public Domain
 * APIRoyal
 * @author Germán Gascón <ggascon@gmail.com>
 * @version 0.1, 2024-02-21
 * @since 0.1, 2024-02-21
 **/
public class Match implements Serializable {
    private final int id;
    private final int idPlayer1;
    private final int crownsPlayer1;
    private final int idPlayer2;
    private final int crownsPlayer2;

    public Match(int id, int idPlayer1, int crownsPlayer1, int idPlayer2, int crownsPlayer2) {
        this.id = id;
        this.idPlayer1 = idPlayer1;
        this.crownsPlayer1 = crownsPlayer1;
        this.idPlayer2 = idPlayer2;
        this.crownsPlayer2 = crownsPlayer2;
    }

    public int getId() {
        return id;
    }

    public int getIdPlayer1() {
        return idPlayer1;
    }

    public int getCrownsPlayer1() {
        return crownsPlayer1;
    }

    public int getIdPlayer2() {
        return idPlayer2;
    }

    public int getCrownsPlayer2() {
        return crownsPlayer2;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", idPlayer1=" + idPlayer1 +
                ", crownsPlayer1=" + crownsPlayer1 +
                ", idPlayer2=" + idPlayer2 +
                ", crownsPlayer2=" + crownsPlayer2 +
                '}';
    }
}
