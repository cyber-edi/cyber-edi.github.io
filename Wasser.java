package spiel;

public class Wasser extends Feld
{
	private boolean anvisiert;
	
	Wasser ()
	{
		super ();
		this.anvisiert = false;
	}
	public boolean getAnvisiert ()
	{
		return this.anvisiert;
	}
	public void setAnvisiert (boolean anvisiert)
	{
		this.anvisiert = anvisiert;
	}
	public String toString ()
	{
		if (anvisiert == false)
			return "W";
		else
			return "o";
	}
}
