package spiel;

public class Schiff extends Feld
{
	private String typ;
	private int laenge;
	private boolean schaden;
	private boolean zerstoert;
	
	public Schiff(String typ, int laenge) 
	{
		super();
		this.typ = typ;
		this.laenge = laenge;
		this.schaden = false;
		this.zerstoert = false;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public int getLaenge() {
		return laenge;
	}

	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	public boolean getSchaden() {
		return schaden;
	}

	public void setSchaden(boolean schaden) {
		this.schaden = schaden;
	}
	public boolean getZerstoert()
	{
		return zerstoert;
	}
	public void setZerstoert(boolean zerstoert)
	{
		this.zerstoert = zerstoert;
	}
	public String toString ()
	{
		if (schaden == false)
			return "S";
		else
			return "x";
	}
}
