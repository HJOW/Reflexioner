
import javax.swing.JOptionPane;

import mainClasses.MessageShowable;
import mainClasses.RunManager;
public class ReflexionerIndex 
{		
	public static final boolean TRY_CHECKVER = false;
	public static final boolean UPDATE_ONLY = false;
	
	public static void main(String[] args)
	{		
		try {
			RunManager.run(args, null, ReflexionerIndex.class);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}	
}
class ConsoleShowable implements MessageShowable
{
	@Override
	public void message()
	{
		System.out.println();
		
	}

	@Override
	public void message_bar()
	{
		System.out.println("----------------------------------------------");
		
	}

	@Override
	public void message(String str)
	{
		System.out.println(str);
		
	}

	@Override
	public void alert(String str)
	{
		JOptionPane.showMessageDialog(null, str);
		
	}

	@Override
	public void openConsole()
	{
		// TODO Auto-generated method stub
		
	}	
}
