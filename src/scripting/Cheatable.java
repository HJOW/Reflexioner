package scripting;

import mainClasses.MessageShowable;

public interface Cheatable extends MessageShowable
{
	public void showAll();
	public void cheatApply();
	public void cheatApply(String cheat) throws Exception;
}
