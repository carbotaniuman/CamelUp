
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
		  	out.println(game.getCurPlayer().getName() + "'s Turn:");
		  	do {
		  		out.print("\nA - place round bet; B - place race bet; C - roll die; D - place Desert Card : ");
		  		String choice = scanner.nextLine();
		  		if(choice.equalsIgnoreCase("a"))
		  		{
		  			out.print("What color camel are you betting on? ");
		  			Color c = GameState.COLORBIMAP.get(scanner.nextLine().toLowerCase());
		  			game.placeRoundBet(c);
		  			//figure out how to call player's cards, pull/remove one of color,make sure it hasn't been used, 
		  			//remove that number from card set, throw false if it can't get removed cause none left (make boolean method)
		  		}
		 		else if(choice.equalsIgnoreCase("b"))
		 		{
		 			out.print("Win or lose bet? ");
		 			String bet = scanner.nextLine();
		 			out.print("Enter color of bet: ");
		 			Color c = GameState.COLORBIMAP.get(scanner.nextLine().toLowerCase());
		 			if(bet.equalsIgnoreCase("win"))
		 			{
		 				game.placeWinBet(c);
		 			}
		 			else if(bet.equalsIgnoreCase("lose"))
		 			{
		 				game.placeLoseBet(c);
		 			}
		 		}
		 		else if(choice.equalsIgnoreCase("c"))
		 		{
		 			game.moveCamel();
		 			Die d = game.getPyramid().getLastDie();
		 			String color = toUpperCase(GameState.COLORBIMAP.inverse().get(d.getColor()));
		 			System.out.println(color + " rolled a " + d.getLastRoll());
		 		}
		 		else if(choice.equalsIgnoreCase("d"))
		 		{
		 			out.print("Plus one or minus one? ");
		 			boolean isOasis = scanner.nextBoolean();
		 			out.print("Which tile? ");
		 			int tileNum = scanner.nextInt();
		 			game.placeDesertCard(isOasis, tileNum);
		 		}
		  	} while(turnIndex == game.getTurnIndex());
		  	out.println();
		  	if(game.isGameEnded())
		  	{
		  		break;
		  	}
		}
	}
	
	public static String toUpperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}


