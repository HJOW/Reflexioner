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

public class CircleBoom extends OvalBoom
{
    private static final long serialVersionUID = -5618847410209036191L;
    public CircleBoom()
    {
        super();
    }
    public CircleBoom(int r)
    {
        super(r);
    }
    @Override
    public void draw(Graphics g, JPanel a)
    {
        g.drawOval(Arena.convertX(getX() - (int)(getR()/2.0), a), Arena.convertY(getY() - (int)(getR()/2.0), a), Arena.convertWidth(getR(), a), Arena.convertHeight(getR(), a));        
    }
    public String getBoomName(boolean makerInclude)
    {
        String results = "accumulateboom";
        if(makerInclude && getMaker() >= 0) results = "enemy_" + results;
        return results;
    }
    public OvalBoom clone()
    {
        return clone(false);
    }
    public OvalBoom clone(boolean imgnull)
    {
        CircleBoom newBoom = new CircleBoom();
        
        newBoom.setX(getX());
        newBoom.setY(getY());
        newBoom.setColor(getColor());
        newBoom.setR(getR());
        newBoom.setHp(getHp());
        newBoom.setMaker(getMaker());        
        newBoom.setColor(getColor());
        if(imgnull) newBoom.setImage(null);
        else newBoom.setImage(getLoadedImage());
        return newBoom;
    }
}