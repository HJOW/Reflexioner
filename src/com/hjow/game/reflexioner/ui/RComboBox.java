package com.hjow.game.reflexioner.ui;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings("rawtypes")
public class RComboBox extends JComboBox {
	private static final long serialVersionUID = -8615392625817862067L;
    public RComboBox()
    {
    	super();
    }
    @SuppressWarnings("unchecked")
	public RComboBox(ComboBoxModel model)
    {
    	super(model);
    }
    @SuppressWarnings("unchecked")
	public RComboBox(Object[] items)
    {
    	super(items);
    }
    @SuppressWarnings("unchecked")
	public RComboBox(Vector items)
    {
    	super(items);
    }
}
