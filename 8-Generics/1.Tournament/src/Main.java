public class Main {
    public static void main(String[] args) {

        League<FootballTeam> footballLeague = new League<>("Football League");


        FootballTeam team = new FootballTeam("F - AAAA", 2);
        footballLeague.addTeam(team);
        //footballLeague.printTeams();

        team = new FootballTeam("F - BBBB", 5);
        footballLeague.addTeam(team);
        //footballLeague.printTeams();

        team = new FootballTeam("F - CCCC", 4);
        footballLeague.addTeam(team);
        ///footballLeague.printTeams();

        team = new FootballTeam("F - DDDD", 7);
        footballLeague.addTeam(team);
        
	//printing the football teams        
        footballLeague.printTeams();



        HockeyTeam hockeyTeam = new HockeyTeam("H - AAA", 9);
       // footballLeague.addTeam(Hteam);            ERROR
        League<HockeyTeam> hTeam = new League<>("Hockey League");
        hTeam.addTeam(hockeyTeam);

	//printing the hockey teams
        hTeam.printTeams();


    }
}
