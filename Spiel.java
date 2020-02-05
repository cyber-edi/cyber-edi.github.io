package spiel;

import java.util.Scanner;
import java.util.Random;

public class Spiel 
{
	public static void main (String [] args)
	{
		Random zufall = new Random ();
		Scanner eingabe = new Scanner (System.in);
		int x, y;
		int versuche1 = 0;
		int versuche2 = 0;
		Flotte flotteSpieler1 = new Flotte ();
		Flotte flotteSpieler2 = new Flotte ();
		Spielfeld spieler1 = new Spielfeld();
		Spielfeld spieler2 = new Spielfeld();
		spieler1.setWasser();
		spieler1.setRandomSpielfeld(flotteSpieler1);
		spieler2.setWasser();
		spieler2.setRandomSpielfeld(flotteSpieler2);
		
		while (flotteSpieler1.getZerstoert() == false || 
				flotteSpieler2.getZerstoert() == false)
		{
			spieler2.displayGegner();
			System.out.println("Spieler 1. schießt...");
			System.out.print("Reihe: ");
			x = eingabe.nextInt();
			System.out.print("Spalte: ");
			y = eingabe.nextInt();
			spieler2.checkPosition(x, y);
			versuche1++;
						
			System.out.print("Spieler 2. schießt...");
			x = zufall.nextInt(10);
			y = zufall.nextInt(10);
			spieler1.checkPosition(x, y);
			spieler1.displaySpielfeld();
			versuche2++;
		}
	}
}
