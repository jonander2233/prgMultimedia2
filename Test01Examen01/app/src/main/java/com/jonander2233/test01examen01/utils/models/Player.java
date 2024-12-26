package com.jonander2233.test01examen01.utils.models;

import java.io.Serializable;


/**
 * Player
 * License: 🅮 Public Domain
 * APIRoyal
 * @author Germán Gascón <ggascon@gmail.com>
 * @version 0.1, 2024-02-21
 * @since 0.1, 2024-02-21
 **/
public class Player implements Serializable {
    private final int id;
    private final String name;
    private final String surname;
    private final String birthdate;
    private final String team;
    private final String country;
    private final String flag;

    public Player(int id, String name, String surname, String birthdate, String team, String country, String flag) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.team = team;
        this.country = country;
        this.flag = flag;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getTeam() {
        return team;
    }

    public String getCountry() {
        return country;
    }

    public String getFlag() {
        return flag;
    }


    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", team='" + team + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
