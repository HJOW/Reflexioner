package reflexioner;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;

import javax.swing.JPanel;

public abstract class RectObject extends GraphicObject
{
	private static final long serialVersionUID = 4039505623161233294L;
	private int w, h;
	public RectObject()
	{
		super();
		w = 0;
		h = 0;
	}
	public Area area()
	{
		return new Area(new Rectangle(getX(), getY(), getW(), getH()));
	}	
	@Override
	public void draw(Graphics g, JPanel a)
	{
		g.fillRect(Arena.convertX(getX() - (int)(w/2.0), a), Arena.convertY(getY() - (int)(h/2.0), a), Arena.convertWidth(w, a), Arena.convertHeight(h, a));	
	}
	public int getW()
	{
		return w;
	}
	public void setW(int w)
	{
		this.w = w;
	}
	public int getH()
	{
		return h;
	}
	public void setH(int h)
	{
		this.h = h;
	}	
}
