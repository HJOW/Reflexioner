package com.hjow.game.reflexioner.pack;

import java.io.Serializable;
import java.util.List;

import com.hjow.game.reflexioner.reflexioner.ReflexScenario;
import com.hjow.game.reflexioner.reflexioner.SpaceShip;

public interface Pack extends Serializable
{
	public long                 getSerial();
	public String               getName();
	public String               getDescription(String locale);
    public List<SpaceShip>      getSpaceShip();
    public List<ReflexScenario> getScenarios();
}
