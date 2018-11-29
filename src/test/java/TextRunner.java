
import static java.lang.System.out;

import java.awt.Color;

import gamestate.*;
import immutable.*;

public class TextRunner 
{
	Player p = new Player("Non");
	Color c1 = Color.BLUE;
	Color c2 = Color.GREEN;
	boolean isOasis = false;
	int tileNum = 5;
	RaceBettingCard raceb1 = new RaceBettingCard(c1, p);
	RaceBettingCard raceb2 = new RaceBettingCard(c2, p);
	DesertCard dc = new DesertCard(p, isOasis);
	GameState g = new GameState();
	g.moveCamel();
	g.placeWinBet(raceb1);
	g.placeLoseBet(raceb2);
	g.placeRoundBet(c1);
	g.isCurPlayer(p);
	g.placeDesertCard(dc, tileNum);
	g.commitTurn();
}
