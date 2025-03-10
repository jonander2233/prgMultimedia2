package com.jonander2233.test01examen01.utils;

import com.jonander2233.test01examen01.utils.models.Card;
import com.jonander2233.test01examen01.utils.models.Competition;
import com.jonander2233.test01examen01.utils.models.Match;
import com.jonander2233.test01examen01.utils.models.Player;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SortObjects {
    private static Map<String, Integer> ordenRarezas;
    private static Map<Player, Integer> victories;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static LocalDate fechaActual;
    private static LocalDate fechaNac1;
    private static LocalDate fechaNac2;

    static {
        victories = new HashMap<>();
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
    public static Integer getWins(Player player){
        return victories.get(player);
    }
    public static void orderPlayers(Competition competition){
        List<Match> matches = competition.getMatches();
        List<Player> players = competition.getPlayers();
        //recorrer los matches y sumar las victorias
        for (int i = 0; i < matches.size(); i++) {
            //por cada iteración, se coge el match y se intenta recoger los players
            Player p1 = findByid(matches.get(i).getIdPlayer1(), competition); //id jugador 1
            Player p2 = findByid(matches.get(i).getIdPlayer2(), competition); //id jugador 2
            //si ambos players existen se comparan sus coronas ganadas en el match
            if(p1 != null && p2 != null){
                int p1Crowns = matches.get(i).getCrownsPlayer1();//coronas jugador 1
                int p2Crowns = matches.get(i).getCrownsPlayer2(); //coronas jugador 3
                //si no existen los jugadores en el hashMap se crean con valor de victorias por defecto a 0
                if(!victories.containsKey(p1)){
                    victories.put(p1,0);
                }
                if(!victories.containsKey(p2)){
                    victories.put(p2,0);
                }
                //si el jugador uno tiene más copas, entonces se obtiene su int del hashmap (que en este caso es el numero de victorias)
                //y se aumenta en 1, luego se remplaza por el numero de victorias anteriores, es decir, si antes era 1, ahora es 2
                if(p1Crowns > p2Crowns){
                    int nWins = victories.get(p1);
                    nWins ++;
                    victories.replace(p1,nWins);
                } else if(p2Crowns > p1Crowns) {
                    //si es al contrario, se hace lo mismo pero a la inversa, se aumentan las victorias al player 2
                    int nWins = victories.get(p2);
                    nWins ++;
                    victories.replace(p2,nWins);
                }
            }
        }
        //aquí se termina la lectura de todos los matches
        //se ordenan según sus victorias
        players.sort((p1,p2)->{
            int winsp1 = victories.get(p1);
            int winsp2 = victories.get(p2);
            int compareWins = Integer.compare(winsp2,winsp1);
            if(compareWins !=0)
                return compareWins;

            String P1fechaNacStr = p1.getBirthdate();
            String P2fechaNacStr = p2.getBirthdate();
            fechaNac1 = LocalDate.parse(P1fechaNacStr,formatter);
            fechaNac2 = LocalDate.parse(P2fechaNacStr,formatter);
            int compareAge = fechaNac2.compareTo(fechaNac1);
            if (compareAge != 0) {
                return compareAge;
            }
            return p2.getName().compareTo(p1.getName());
        });

        System.out.println("ordenados por victorias");
        for (Player player : players){
            System.out.println(player.getName() + " id: " + player.getId() + " wins: " + victories.get(player));
        }
    }

    private static Player findByid(int id,Competition competition){
        List<Player> players = competition.getPlayers();
        Optional<Player> foundPlayer = players.stream()
                .filter(player -> player.getId() == id)
                .findFirst();
        return foundPlayer.orElse(null); // Si no se encuentra, devuelve null
    }
}
