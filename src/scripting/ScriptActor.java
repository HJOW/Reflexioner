package scripting;

import javax.swing.JDialog;

import mainClasses.MessageShowable;
import mainClasses.Openable;

public interface ScriptActor extends MessageShowable
{
	public void putObject(String name, Object object);
	public Object actAndGet(String orders) throws Exception;
	public void actOnly(String orders) throws Exception;
	public void act(String orders) throws Exception;
	public void load_js_files(boolean func);
	public void load_js_url();
	public Helpable[] helpList();
	public Openable getHelpWindow();
	public Openable getHelpWindow(JDialog dialog);
}
