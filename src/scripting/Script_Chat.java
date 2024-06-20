package scripting;

import main_classes.MessageShowable;

@Deprecated
public class Script_Chat implements Helpable
{
	private MessageShowable wind = null;
	public Script_Chat()
	{
		
	}
	public Script_Chat(MessageShowable w)
	{
		wind = w;
	}
	public void port(int port)
	{
		
	}
	public void buffer_size(int size)
	{
		
	}
	public void add(String ip)
	{	
		list_refresh();
	}
	public void send(String message)
	{
		
	}
	public void send(int ip, String message)
	{
		
	}
	public void send(String ip, String message)
	{
		
	}
	public void ban(String ip)
	{
		list_refresh();
	}
	public void remove(String ip)
	{
		list_refresh();
	}
	private void list_refresh()
	{
		try
		{
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private void message()
	{
		if(wind != null)
		{
			wind.message();
		}
	}
	private void message_bar()
	{
		if(wind != null)
		{
			wind.message_bar();
		}
	}
	private void message(String str)
	{
		if(wind != null)
		{
			wind.message(str);
		}
	}
	public void help()
	{
		String lang = System.getProperty("user.language");
		if(lang.equalsIgnoreCase("english") || lang.equalsIgnoreCase("en") || lang.equalsIgnoreCase("eng")) help(0);
		else if(lang.equalsIgnoreCase("korean") || lang.equalsIgnoreCase("ko") || lang.equalsIgnoreCase("kor") || lang.equalsIgnoreCase("kr")) help(1);
		else help(0);
	}
	public void help(int lang)
	{		
		switch(lang)
		{
			case 1:
				message("===== chat commands =====");
				message();
				message("add(_i) : ip 주소 _i를 대화 대상으로 추가합니다.");
				message("remove(_i) : ip 주소 _i를 대화 대상에서 제거합니다.");
				message("send(_m) : 대화 대상에 포함된 모든 대상에게 문자열 _m 메시지를 보냅니다.");
				message("send(_i, _m) : 대화 대상에 포함된 _i 번째 대상(0부터 시작)에게 문자열 _m 메시지를 보냅니다.");
				message("port(_i) : 보낼 때 포트 번호를 _i로 설정합니다.");
				message("buffer_size(_i) : 버퍼 크기를 _i로 변경합니다. 버퍼가 비워집니다.");
				message();
				message_bar();
				break;
			default:
				message("===== chat commands =====");
				message("I\'m sorry that there is only korean helps for chat commands.");
				help(1);
				break;
		}
	}
	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		Helpable helpables = new Script_Chat(showable);
		return helpables;
	}
	@Override
	public String title()
	{
		return "chat";
	}
}
