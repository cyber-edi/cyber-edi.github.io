package spiel;

public class Koordinaten 
{
	private int x, y;
	private boolean anvisiert;
	private boolean getroffen;
	
	Koordinaten (int x, int y)
	{
		this.x = x;
		this.y = y;
		this.anvisiert = false;
		this.getroffen = false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isAnvisiert() {
		return anvisiert;
	}

	public void setAnvisiert(boolean anvisiert) {
		this.anvisiert = anvisiert;
	}
	public boolean isGetroffen ()
	{
		return getroffen;
	}
	public void setGetroffen (boolean getroffen)
	{
		this.getroffen = getroffen;
	}
	public String toString ()
	{
		if (getroffen == true)
			return "X";
		if (anvisiert == true)
			return "+";
		else
			return ".";
	}
}
