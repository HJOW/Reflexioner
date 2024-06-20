package dom;


import java.awt.Component;

public class DOM_Location
{
	public String hash = "";
	public String host = "", hostname = "", pathname = "", protocol = "";
	public int port = 0;
	public Object search = null;
	
	public String href = "";
	public DOM_Location(Component dialog)
	{
		
	}
	public String toString()
	{
		return href;
	}
	public void assign(String str)
	{
		href = str;
	}
	public void reload()
	{
		
	}
	public void replace(String url)
	{
		
	}
}
