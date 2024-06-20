package reflex;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class ImageObject extends RectObject
{
	private static final long serialVersionUID = 4633772727235919989L;
	private transient BufferedImage img = null;
	private String name = "";
	public ImageObject()
	{
		
	}
	public ImageObject(String name)
	{
		this.name = name;
	}
	public void load() throws IOException
	{
		img = ImageIO.read(new File(name));
	}
	@Override
	public int getW()
	{
		return img.getWidth();
	}
	@Override
	public int getH()
	{
		return img.getHeight();
	}
	@Override
	public void draw(Graphics g, JPanel a)
	{
		g.drawImage(img, getX() - (int)(img.getWidth()/2.0), getY() - (int)(img.getHeight()/2.0), null);
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}