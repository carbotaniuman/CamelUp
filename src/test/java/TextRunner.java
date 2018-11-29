
import static java.lang.System.out;
import java.awt.Color;
import java.util.Scanner;

import gamestate.*;
import immutable.*;
import game.*;

public class TextRunner 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		out.print("Enter player name = ");
		Player p = new Player(scan.nextLine());
		out.print("Enter color 1 = ");
		String color1 = scan.nextLine();
		out.print("Enter color 2 = ");
		String color2 = scan.nextLine();
		Color c1 = getColor(color1);
		Color c2 = getColor(color2);
		out.print("Enter isOasis = ");
		boolean isOasis = scan.nextBoolean();
		out.print("Enter num = ");
		int tileNum = scan.nextInt();
		RaceBettingCard raceb1 = new RaceBettingCard(c1, p);
		RaceBettingCard raceb2 = new RaceBettingCard(c2, p);
		DesertCard dc = new DesertCard(p, isOasis);
		GameState g = new GameState();
		g.moveCamel();
		g.placeWinBet(raceb1);
		g.placeLoseBet(raceb2);
		g.placeRoundBet(c1);
		out.println(g.isCurPlayer(p));
		g.placeDesertCard(dc, tileNum);
		g.commitTurn();
	}
	
	public static Color getColor(String color)
	{
		Color c = null;
		if(color.equalsIgnoreCase("blue"))
		{
			c = Color.blue;
		}
		else if(color.equalsIgnoreCase("green"))
		{
			c = Color.green;
		}
		else if(color.equalsIgnoreCase("yellow"))
		{
			c = Color.yellow;
		}
		else if(color.equalsIgnoreCase("white"))
		{
			c = Color.white;
		}
		else if(color.equalsIgnoreCase("orange"))
		{
			c = Color.orange;
		}
		return c;
	}
}
