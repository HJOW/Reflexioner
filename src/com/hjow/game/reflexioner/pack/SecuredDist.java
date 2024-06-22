package com.hjow.game.reflexioner.pack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecuredDist implements Serializable {
    private static final long serialVersionUID = -7659331755330322341L;
    public String getRightPad(int versionMain, int versionSub1, int versionSub2)
    {
        // This value will be changed on release version
        return "" + (blockOne(versionMain, versionSub1, versionSub2) + "X" + (blockThree(versionMain, versionSub1, versionSub2) + blockFive(versionMain, versionSub1, versionSub2)));
    }
    public String getLeftPad(int versionMain, int versionSub1, int versionSub2)
    {
        // This value will be changed on release version
        return "" + (blockFour(versionMain, versionSub1, versionSub2) + "h" + (blockOne(versionMain, versionSub1, versionSub2) + blockTwo(versionMain, versionSub1, versionSub2)));
    }
    public List<Pack> getPacks()
    {
        // This value will be changed on release version
        return new ArrayList<Pack>();
    }
    
    // This function will be changed on release version
    private long blockOne(int versionMain, int versionSub1, int versionSub2)
    {
        long a, b, c, d ,e;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        return (a + b + e + versionMain) * (d + c + versionSub1 + versionSub2);
    }
    
    // This function will be changed on release version
    private long blockTwo(int versionMain, int versionSub1, int versionSub2)
    {
        long a, b, c, d ,e;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        return (a + b + e + versionSub2) * (d + c + versionMain);
    }
    
    // This function will be changed on release version
    private long blockThree(int versionMain, int versionSub1, int versionSub2)
    {
        long a, b, c, d ,e;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        return (a + b + e) * (d + c + versionSub2);
    }
    
    // This function will be changed on release version
    private long blockFour(int versionMain, int versionSub1, int versionSub2)
    {
        long a, b, c, d ,e;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        return (a + b + e) * (d + c + versionMain);
    }
    
    // This function will be changed on release version
    private long blockFive(int versionMain, int versionSub1, int versionSub2)
    {
        long a, b, c, d ,e;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        return (a + b + e + versionSub1) * (d + c + versionMain);
    }
}
