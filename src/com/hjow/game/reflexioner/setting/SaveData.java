package com.hjow.game.reflexioner.setting;

import com.hjow.game.reflexioner.mainClasses.XMLSerializableObject;

public abstract class SaveData extends XMLSerializableObject implements Cloneable
{
    private static final long serialVersionUID = 6098507614396410772L;
    public long value = 0;
    public abstract SaveData clone();
    public int getValue()
    {
        return (int)value;
    }
    public void setValue(int value)
    {
        this.value = value;
    }
}
