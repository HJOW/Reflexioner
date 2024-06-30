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

import javax.swing.JPanel;

public class StringDecorate extends ReflexDecorate
{
    private static final long serialVersionUID = -5836400727609586307L;
    private String contents = "";
    public StringDecorate()
    {
        super();
        setHp(50);
    }
    public StringDecorate(int x, int y, int dy, int r)
    {
        super(x, y, dy, r);
        setHp(50);
    }
    public StringDecorate(String name, int x, int y, int dy, int r)
    {
        super(name, x, y, dy, r);
        setHp(50);
    }
    public String getContents()
    {
        return contents;
    }
    public void setContents(String contents)
    {
        this.contents = contents;
    }
    public ReflexDecorate clone()
    {
        return clone(false);
    }
    public ReflexDecorate clone(boolean imgnull)
    {
        StringDecorate newDeco = new StringDecorate(new String(getName()), getX(), getY(), getDy(), getR());
        newDeco.setContents(getContents());
        return newDeco;
    }
    @Override
    public void draw(Graphics g, JPanel a)
    {
        g.drawString(contents, Arena.convertX(getX(), a), Arena.convertY(getY(), a));
    }
    @Override
    public void update()
    {
        setHp(getHp() - 1);
        setY(getY() + getDy());
    }
}
