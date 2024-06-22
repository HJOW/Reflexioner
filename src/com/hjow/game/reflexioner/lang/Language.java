package com.hjow.game.reflexioner.lang;

import java.io.Serializable;
import java.util.Properties;

public interface Language extends Serializable {
    public String     language();
    public Properties properties();
}
