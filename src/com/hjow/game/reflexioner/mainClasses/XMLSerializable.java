package com.hjow.game.reflexioner.mainClasses;

import java.io.Serializable;

import org.w3c.dom.Document;

public interface XMLSerializable extends Serializable
{
    public Document toXMLDocument();
}
