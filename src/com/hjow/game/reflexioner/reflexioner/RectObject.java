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

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;

import javax.swing.JPanel;

public abstract class RectObject extends GraphicObject
{
    private static final long serialVersionUID = 4039505623161233294L;
    private int w, h;
    public RectObject()
    {
        super();
        w = 0;
        h = 0;
    }
    public Area area()
    {
        return new Area(new Rectangle(getX(), getY(), getW(), getH()));
    }    
    @Override
    public void draw(Graphics g, JPanel a)
    {
        g.fillRect(Arena.convertX(getX() - (int)(w/2.0), a), Arena.convertY(getY() - (int)(h/2.0), a), Arena.convertWidth(w, a), Arena.convertHeight(h, a));    
    }
    public int getW()
    {
        return w;
    }
    public void setW(int w)
    {
        this.w = w;
    }
    public int getH()
    {
        return h;
    }
    public void setH(int h)
    {
        this.h = h;
    }    
}
