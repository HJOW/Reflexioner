/*
Copyright 2024 HJOW (hujinone22@naver.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.hjow.game.reflexioner.reflexioner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;

import javax.swing.JPanel;

import com.hjow.game.reflexioner.mainClasses.XMLSerializableObject;

public abstract class GraphicObject extends XMLSerializableObject
{
    private static final long serialVersionUID = -8975895635519622658L;
    private int x = 0, y = 0;
    private Color color;
    private long hp = 1000;
    private long max_hp = 1000;
    private long hp_heal = 1;
    
    public transient int t_r, t_g, t_b;
    
    public GraphicObject()
    {
        x = 0;
        y = 0;
    }
    public abstract void update();
    public abstract void draw(Graphics g, JPanel a);
    public abstract void keyPressed(KeyEvent e);
    public abstract Area area();
    public abstract GraphicObject clone();
    public abstract GraphicObject clone(boolean imgnull);
    public void decreaseHP()
    {
        setHp(getHp() - 1);
    }
    
    public Point getLocation()
    {
        return new Point(x, y);
    }
    public void getLocation(Point v)
    {
        x = (int) Math.round(v.getX());
        y = (int) Math.round(v.getY());
    }
    public void init()
    {
        setHp(getMax_hp());
        setHp_heal(1);
    }
    public boolean collapse(GraphicObject other) 
    {
        boolean results = false;
        Area newArea;
        Area thisArea = area();
        if(other instanceof OvalObject)
        {
            newArea = other.area();            
            newArea.intersect(thisArea);
            results = ! newArea.isEmpty();
        }
        else if(other instanceof RectObject)
        {
            newArea = other.area();
            newArea.intersect(thisArea);
            results = ! newArea.isEmpty();
        }        
        //System.out.println(this.getClass().getName() + " vs " + other.getClass().getName() + " : " + results + ", " + this.getHp());
        return results;
    }
    public boolean collapse(Area area)
    {
        Area thisArea = area();
        thisArea.intersect(area);
        return ! thisArea.isEmpty();
    }
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public Color getColor()
    {
        return color;
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    public long getHp()
    {
        return hp;
    }
    public void setHp(long hp)
    {
        this.hp = hp;
    }
    public long getMax_hp()
    {
        return max_hp;
    }
    public void setMax_hp(long max_hp)
    {
        this.max_hp = max_hp;
    }
    public long getHp_heal()
    {
        return hp_heal;
    }
    public void setHp_heal(long hp_heal)
    {
        this.hp_heal = hp_heal;
    }    
}
