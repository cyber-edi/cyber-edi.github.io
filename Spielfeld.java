package spiel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class Spielfeld 
{
	//Felder Matrix zum speichern der einzelnen Felder (Wasser o. Schiff)
	Feld [][] spielfeld = new Feld [10][10];
	//Feld Wasser
	Wasser wasser;
	//Koordinaten Matrix für die Auswertung der Geschosse
	Koordinaten[][] koordinaten = new Koordinaten[10][10];
	//Boolean Kompass: False = Horizontal, True = Vertikal. 
	//Boolean besetzt ob ein Feld mit Schiff besetzt ist. 
	boolean kompass, besetzt;
	//X, Y Koordinaten
	int x, y;
	
	Random zufall = new Random ();
	BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
	Scanner eingabe = new Scanner (System.in);
	
	public void displayGegner ()
	{
		System.out.println("#. [0][1][2][3][4][5][6][7][8][9]");
		for (int i = 0; i < spielfeld.length; i++)
		{
			System.out.print(i + ". ");
			for (int j = 0; j < spielfeld.length; j++)
			{
				System.out.print("[" + koordinaten[i][j] + "]");
			}
			System.out.println();
		}
		System.out.println();
	}
	public void checkSchiff(Schiff [] schiff)
	{
		int schaden = 0;
		for (int i = 0; i < schiff.length; i++)
		{			
			if (schiff[i].getSchaden() == true)
				schaden++;
		}
		if (schaden == schiff.length)
		{
			for (int i = 0; i < schiff.length; i++)
				schiff[i].setZerstoert(true);
		}
	}
	public void setWasser ()
	{
		for (int i = 0; i < spielfeld.length; i++)
		{
			for (int j = 0; j < spielfeld.length; j++)
			{
				spielfeld[i][j] = new Wasser();
				koordinaten[i][j] = new Koordinaten(i, j);
			}
		}
	}
	public void displaySpielfeld ()
	{
		for (int i = 0; i < spielfeld.length; i++)
		{
			System.out.print(i + ". ");
			for (int j = 0; j < spielfeld.length; j++)
			{
				System.out.print("[" + spielfeld[i][j] + "]");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void checkPosition (int x, int y)
	{
		if (spielfeld[x][y].getClass().equals(Wasser.class))
		{
			spielfeld[x][y].setAnvisiert(true);
			System.out.println("Leider nur Wasser getroffen");
			koordinaten[x][y].setAnvisiert(true);
			//koordinaten[x][y].setGetroffen(false);
		}
		else
		{
			spielfeld[x][y].setSchaden(true);
			System.out.println("Volltreffer!");
			koordinaten[x][y].setGetroffen(true);
		}
	}
	public boolean checkBesetzt (int x, int y, int l, boolean kompass)
	{
		if (kompass == false)
		{
			for (int i = 0; i < l; i++)
			{
				if (spielfeld[x][y + i].getClass().equals(Schiff.class))
					besetzt = true;
				
				else
					besetzt = false;
			}
			
		}
		else
		{
			for (int i = 0; i < l; i++)
			{
				if (spielfeld[x + i][y].getClass().equals(Schiff.class))
					besetzt = true;
				else 
					besetzt = false;
			}
		}
		return besetzt;
	}
	public void setSchiff (Schiff [] schiff, String typ, int l)
	{
		do
		{
			System.out.println("\nEingabe Position " + typ + "\n");
			checkKompass ();
			System.out.print("Reihe: ");
			x = eingabe.nextInt();
			System.out.print("Spalte: ");
			y = eingabe.nextInt();
			checkBesetzt(x, y, schiff.length, kompass);
			if (besetzt == false)
			{
				if (kompass == false)
				{
					for (int i = 0; i < schiff.length; i++)
					{
						schiff[i] = new Schiff (typ, schiff.length);
						spielfeld[x][y + i] = schiff[i];
					}
				}
				else
				{
					for (int i = 0; i < schiff.length; i++)
					{
						schiff[i] = new Schiff (typ, schiff.length);
						spielfeld[x + i][y] = schiff[i];
					}
				}
			}
			else
			{
				besetzt = true;
				System.out.println("Platz schon besetzt!");
			}
		}
		while (besetzt == true);
		
		System.out.println("Schiff " + typ + " wurde platziert!");
	}
	public boolean checkKompass ()
	{
		String horizontal = "h";
		String input;
		System.out.print("Kompass\nHorizontal = h\nVertikal = v\nEingabe: ");
		try
		{
			input = br.readLine();
			if (input.equals(horizontal))
				kompass = false;
			else
				kompass = true;
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return kompass;
	}
	public void setRandomSchiff (Schiff [] schiff, String typ, int l)
	{
		do
		{
			kompass = zufall.nextBoolean();
			if (kompass == false)
			{
				x = zufall.nextInt(10);
				y = zufall.nextInt(10 - l);
				checkBesetzt (x, y, l, kompass);
				if (besetzt == false)
				{
					for (int i = 0; i < l; i++)
					{
						schiff[i] = new Schiff (typ, l);
						spielfeld[x][y + i] = schiff[i];
					}
				}
				else
					besetzt = true;
			}
			else
			{
				x = zufall.nextInt(10 - l);
				y = zufall.nextInt(10);
				checkBesetzt(x, y, l, kompass);
				if (besetzt == false)
				{
					for (int i = 0; i < l; i++)
					{
						schiff[i] = new Schiff (typ, l);
						spielfeld[x + i][y] = schiff[i];
					}
				}
				else
					besetzt = true;
			}
		}
		while (besetzt == true);
	}
	public void setRandomSpielfeld (Flotte flotte)
	{
		setRandomSchiff(flotte.schlachtSchiff, "Schlachtschiff", flotte.schlachtSchiff.length);
		setRandomSchiff(flotte.kreuzer1, "Kreuzer I", flotte.kreuzer1.length);
		setRandomSchiff(flotte.kreuzer2, "Kreuzer II", flotte.kreuzer2.length);
		setRandomSchiff(flotte.zerstoerer1, "Zerstörer I", flotte.zerstoerer1.length);
		setRandomSchiff(flotte.zerstoerer2, "Zerstörer II", flotte.zerstoerer2.length);
		setRandomSchiff(flotte.zerstoerer3, "Zerstörer III", flotte.zerstoerer3.length);
		setRandomSchiff(flotte.uBoot1, "U-Boot I", flotte.uBoot1.length);
		setRandomSchiff(flotte.uBoot2, "U-Boot II", flotte.uBoot2.length);
		setRandomSchiff(flotte.uBoot3, "U-Boot III", flotte.uBoot3.length);
		setRandomSchiff(flotte.uBoot4, "U-Boot IV", flotte.uBoot4.length);
	}
	public void setSpielfeld (Flotte flotte)
	{
		setSchiff(flotte.schlachtSchiff, "Schlachtschiff", flotte.schlachtSchiff.length);
		displaySpielfeld();
		setSchiff(flotte.kreuzer1, "Kreuzer I", flotte.kreuzer1.length);
		displaySpielfeld();
		setSchiff(flotte.kreuzer2, "Kreuzer II", flotte.kreuzer2.length);
		displaySpielfeld();
		setSchiff(flotte.zerstoerer1, "Zerstörer I", flotte.zerstoerer1.length);
		displaySpielfeld();
		setSchiff(flotte.zerstoerer2, "Zerstörer II", flotte.zerstoerer2.length);
		displaySpielfeld();
		setSchiff(flotte.zerstoerer3, "Zerstörer III", flotte.zerstoerer3.length);
		displaySpielfeld();
		setSchiff(flotte.uBoot1, "U-Boot I", flotte.uBoot1.length);
		displaySpielfeld();
		setSchiff(flotte.uBoot2, "U-Boot II", flotte.uBoot2.length);
		displaySpielfeld();
		setSchiff(flotte.uBoot3, "U-Boot III", flotte.uBoot3.length);
		displaySpielfeld();
		setSchiff(flotte.uBoot4, "U-Boot IV", flotte.uBoot4.length);
		System.out.println("Flotte platziert!");
	}
} 
