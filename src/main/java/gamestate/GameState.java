package gamestate;

import game.Camel;
import game.Die;
import game.Pyramid;
import immutable.DesertCard;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

import java.awt.*;
import java.util.*;
import java.util.Queue;

public class GameState {
    private final static Color[] camelColors = {Color.WHITE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
    private ArrayList<Camel> camels;
    private ArrayList<Player> players;
    private Queue<RaceBettingCard> winBets;
    private Queue<RaceBettingCard> loseBets;
    private Map<Color, TreeSet<RoundBettingCard>> roundBets;
    private Track track;
    private Player curPlayer;
    private int curPlayerIndex;
    private Pyramid pyramid;

    public GameState() 
    {
        camels = new ArrayList<Camel>();
        for (int i = 0; i < camelColors.length; i++)
            camels.add(new Camel(camelColors[i]));
        players = new ArrayList<Player>();
        //initialize players
        winBets = new ArrayDeque<>();
        loseBets = new ArrayDeque<>();
        roundBets = new TreeMap<>();
        track = new Track(16, camels);
        curPlayer = players.get(0);
        curPlayerIndex = 0;
        pyramid = new Pyramid();
    }
    public void moveCamel() 
    {
    	Die d = pyramid.getDie((int)(Math.random()*pyramid.getNumNotRolledDice()));
    	
    }
    public void placeWinBet(RaceBettingCard c) 
    {
        winBets.add(c);
    }
    public void placeLoseBet(RaceBettingCard c) 
    {
        loseBets.add(c);
    }
    public void placeRoundBet(Color c) //**CHANGE**PREV: public void placeRoundBet(Player p)
    {
    	Player p = this.curPlayer;
        int index = -1;
        for (int i = 0; i < players.size(); i++) //change if no name forplayer
        {
            if (p.getName().equals(players.get(i).getName())) {
                index = i;
                break;
            }
        }
        players.get(index).addRoundBet(roundBets.get(c).last());
    }
    public boolean isCurPlayer(Player p) 
    {
        return p == curPlayer;
    }
    public void placeDesertCard(DesertCard c) 
    {

    }
    public void commitTurn() 
    {
    	if(curPlayerIndex < players.size()-1)
    		curPlayer = players.get(curPlayerIndex+1);
    	curPlayer = players.get(0);
    	
    	if(pyramid.areAllDiceRolled())
    		pyramid.resetDice();
    }
}