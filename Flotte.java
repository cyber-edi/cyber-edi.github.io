package spiel;

public class Flotte 
{
	private int zerstoerteSchiffe;
	private boolean zerstoert;
	Schiff [] schlachtSchiff = new Schiff [5];
	Schiff [] kreuzer1 = new Schiff [4];
	Schiff [] kreuzer2 = new Schiff [4];
	Schiff [] zerstoerer1 = new Schiff [3];
	Schiff [] zerstoerer2 = new Schiff [3];
	Schiff [] zerstoerer3 = new Schiff [3];
	Schiff [] uBoot1 = new Schiff [2];
	Schiff [] uBoot2 = new Schiff [2];
	Schiff [] uBoot3 = new Schiff [2];
	Schiff [] uBoot4 = new Schiff [2];
	
	public void setZerstoerteSchiffe(int zerstoerteSchiffe)
	{
		this.zerstoerteSchiffe = zerstoerteSchiffe;
	}
	
	public boolean getZerstoert()
	{
		return zerstoert;
	}
	public void setZerstoert (boolean zerstoert)
	{
		this.zerstoert = zerstoert;
	}
}
