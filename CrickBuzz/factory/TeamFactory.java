package CrickBuzz.factory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import CrickBuzz.Player;
import CrickBuzz.PlayerType;
import CrickBuzz.Team;

public class TeamFactory {
    public static Team createTeam(String name){
        //create players in the team 
        Team team;
        Queue<Player> players =  new LinkedList<>();
        List<Player> bowlers = new ArrayList<>();
        Player p1 = new Player(name+1,PlayerType.all_rounder);
        Player p2 = new Player(name+2,PlayerType.all_rounder);
        Player p3 = new Player(name+3,PlayerType.all_rounder);
        Player p4 = new Player(name+4,PlayerType.all_rounder);
        Player p5 = new Player(name+5,PlayerType.all_rounder);
        Player p6 = new Player(name+6,PlayerType.all_rounder);
        Player p7 = new Player(name+7,PlayerType.all_rounder);
        Player p8 = new Player(name+8,PlayerType.all_rounder);
        Player p9 = new Player(name+9,PlayerType.all_rounder);
        Player p10 = new Player(name+10,PlayerType.all_rounder);
        Player p11 = new Player(name+11,PlayerType.all_rounder);
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);
        players.add(p6);
        players.add(p7);
        players.add(p8);
        players.add(p9);
        players.add(p10);
        players.add(p11);
        //4 are the bowlers in the team
        bowlers.add(p8);
        bowlers.add(p9);
        bowlers.add(p10);
        bowlers.add(p11);
        team = new Team(players, new ArrayList<>(), bowlers);
        return team;
    }
}
