
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
		Scanner scanner = new Scanner(System.in);
		GameState game = new GameState();
		while(true)
		{
		  	long turnIndex = game.getTurnIndex();
		  	do {
		  		out.print("A - place round bet; B - place race bet; C - roll die; D - place Desert Card : ");
		  		String choice = scanner.nextLine();
		  		if(choice.equalsIgnoreCase("a"))
		  		{
		  			//figure out how to call player's cards, pull/remove one of color,make sure it hasn't been used, 
		  			//remove that number from card set, throw false if it can't get removed cause none left (make boolean method)
		  		}
		 		else if(choice.equalsIgnoreCase("b"))
		 		{
		 			//same thing with place round bet
		 		}
		 		else if(choice.equalsIgnoreCase("c"))
		 		{
		 			game.moveCamel();
		 		}
		 		else if(choice.equalsIgnoreCase("d"))
		 		{
		 			out.print("Plus one or minus one? ");
		 			boolean isOasis = scanner.nextBoolean();
		 			out.print("Which tile? ");
		 			int tileNum = scanner.nextInt();
		 			Player p = game.getCurPlayer();
		 			game.placeDesertCard(isOasis, p, tileNum);
		 		}
		  	} while(turnIndex != game.getTurnIndex());
		}
	}
	
	public static Color getColor(String color)
	{
		Color c = null;
		if(color.equalsIgnoreCase("blue"))
		{
			c = new Color(51, 153,255);
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


