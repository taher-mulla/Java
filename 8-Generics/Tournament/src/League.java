import java.util.ArrayList;

//the league creates teams and sports

//Java generics used here for teams
public class League<T extends Team>{
    private ArrayList<T> teams;
    String leagueName;

    public League(String leagueName) {
        this.leagueName = leagueName;
        teams = new ArrayList<>();
    }

    public void addTeam(T team){
        teams.add(team) ;
    }

    public void printTeams(){
        for (T team : teams) {
            System.out.println("Name: " + team.getTeamName() + "\tScore: " + team.getScore());
        }
    }
}
