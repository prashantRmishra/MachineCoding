package CrickBuzz;

import CrickBuzz.factory.TeamFactory;
import CrickBuzz.match.Match;
import CrickBuzz.match.T20Format;
import CrickBuzz.teams.Team;

class Driver{
    public static void main(String args[]){
        //flow of the game
        Team team1 = TeamFactory.createTeam("INDIA");
        Team team2 = TeamFactory.createTeam("SRI_LANKA");
        Match match = new Match("INDIA vs SRI LANKA", new T20Format(20, 3) , team1, team2, "Wankhede, Mumbai India");
        match.startMatch();

    }
}