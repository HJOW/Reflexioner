package scripting;

import mainClasses.MessageShowable;

public interface Helpable
{
	public void help();
	public void help(int lang);
	public Helpable newHelp(MessageShowable showable);
	public String title();
}
