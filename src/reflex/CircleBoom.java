package reflex;

import java.awt.Graphics;

import javax.swing.JPanel;

public class CircleBoom extends OvalBoom
{
	private static final long serialVersionUID = -5618847410209036191L;
	public CircleBoom()
	{
		super();
	}
	public CircleBoom(int r)
	{
		super(r);
	}
	@Override
	public void draw(Graphics g, JPanel a)
	{
		g.drawOval(getX() - (int)(getR()/2.0), getY() - (int)(getR()/2.0), getR(), getR());		
	}
	public String getBoomName(boolean makerInclude)
	{
		String results = "accumulateboom";
		if(makerInclude && getMaker() >= 0) results = "enemy_" + results;
		return results;
	}
	public OvalBoom clone()
	{
		return clone(false);
	}
	public OvalBoom clone(boolean imgnull)
	{
		CircleBoom newBoom = new CircleBoom();
		
		newBoom.setX(getX());
		newBoom.setY(getY());
		newBoom.setColor(getColor());
		newBoom.setR(getR());
		newBoom.setHp(getHp());
		newBoom.setMaker(getMaker());		
		newBoom.setColor(getColor());
		if(imgnull) newBoom.setImage(null);
		else newBoom.setImage(getLoadedImage());
		return newBoom;
	}
}