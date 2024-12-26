package com.jonander2233.test01examen01.utils.models;

import java.io.Serializable;

/**
 * Card
 * License: 🅮 Public Domain
 * APIRoyal
 * @author Germán Gascón <ggascon@gmail.com>
 * @version 0.1, 2024-02-21
 * @since 0.1, 2024-02-21
 **/
public class Card implements Serializable {
    private final long id;
    private final String name;
    private final int elixir;
    private final String type;
    private final String rarity;
    private final int arena;
    private final String image;
    private final String description;
    private int speed;
    private int hitSpeed;
    private int range;

    public Card(long id, String name, int elixir, String type, String rarity, int arena, String image, String description, int speed, int hitSpeed, int range) {
        this.id = id;
        this.name = name;
        this.elixir = elixir;
        this.type = type;
        this.rarity = rarity;
        this.arena = arena;
        this.image = image;
        this.description = description;
        this.speed = speed;
        this.hitSpeed = hitSpeed;
        this.range = range;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getElixir() {
        return elixir;
    }

    public String getType() {
        return type;
    }

    public String getRarity() {
        return rarity;
    }

    public int getArena() {
        return arena;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }


    public int getSpeed() {
        return speed;
    }

    public int getHitSpeed() {
        return hitSpeed;
    }

    public int getRange() {
        return range;
    }


    public void setHitSpeed(int hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", elixir=" + elixir +
                ", type='" + type + '\'' +
                ", rarity='" + rarity + '\'' +
                ", arena=" + arena +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", speed=" + speed +
                ", hitSpeed=" + hitSpeed +
                ", range=" + range +
                '}';
    }
}
