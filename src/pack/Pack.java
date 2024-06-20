package pack;

import java.io.Serializable;
import java.util.List;

import reflexioner.ReflexScenario;
import reflexioner.SpaceShip;

public interface Pack extends Serializable
{
	public long                 getSerial();
	public String               getName();
	public String               getDescription(String locale);
    public List<SpaceShip>      getSpaceShip();
    public List<ReflexScenario> getScenarios();
}
