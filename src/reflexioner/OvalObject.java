package reflexioner;

import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public abstract class OvalObject extends GraphicObject
{
	private static final long serialVersionUID = 1108509077903825575L;
	private int r;
	public OvalObject()
	{
		super();
		r = 0;
	}
	public Area area()
	{
		return new Area(new Ellipse2D.Double((double) getX(), (double)getY(), (double)getR(), (double)getR()));
	}	
	@Override
	public void draw(Graphics g, JPanel a)
	{
		g.fillOval(Arena.convertX(getX() - (int)(r/2.0), a), Arena.convertY(getY() - (int)(r/2.0), a), Arena.convertWidth(r, a), Arena.convertHeight(r, a));		
	}
	public int getR()
	{
		return r;
	}
	public void setR(int r)
	{
		this.r = r;
	}	
}
