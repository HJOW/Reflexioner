package com.hjow.game.reflexioner.ui;

import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

@SuppressWarnings("rawtypes")
public class RList extends JList
{
	private static final long serialVersionUID = 7632718288254208239L;
    public RList()
    {
    	super();
    }
    @SuppressWarnings("unchecked")
	public RList(ListModel model)
    {
    	super(model);
    }
    @SuppressWarnings("unchecked")
	public RList(Object[] listData)
    {
    	super(listData);
    }
    @SuppressWarnings("unchecked")
	public RList(Vector listData)
    {
    	super(listData);
    }
}
