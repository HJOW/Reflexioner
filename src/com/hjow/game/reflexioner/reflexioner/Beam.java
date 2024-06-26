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

import java.awt.event.KeyEvent;

public class Beam extends Missile implements Boom
{
    private static final long serialVersionUID = -5706613365453325757L;
    public Beam()
    {
        super();
        setHp(10);
        setDy(0);
        this.setY(0);
        this.setW((int) (getHp() * 2));
        this.setH((int) Math.round(Arena.maxHeight() * 1.5));
    }
    public Beam(long hp)
    {
        super();
        setHp(hp);
        setDy(0);
        this.setY(0);
        this.setW((int) (getHp() * 2));
        this.setH((int) Math.round(Arena.maxHeight() * 1.5));
    }
    public Beam(long hp, String path)
    {
        this(hp);
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    public Beam(long hp, int owner, String path)
    {
        this(hp);
        setOwner(owner);
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    public Beam(int owner, String path)
    {
        this();
        setOwner(owner);
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    public String getMissileName()
    {
        return "beam";
    }
    public String getBoomName()
    {
        return "beam";
    }
    public String getBoomName(boolean makerInclude)
    {
        String results = "beam";
        return results;
    }
    @Override
    public void update()
    {
        setHp(getHp() - 1);
        this.setW((int) (getHp() * 2));
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        
    }    
    @Override
    protected boolean loadCache()
    {
        if(getOwner() <= -1)
        {            
            if(ImageCache.img_beam != null)
            {
                setImage(ImageCache.img_beam);
                return true;
            }
            else return false;
        }
        else
        {
            if(ImageCache.img_enemy_beam != null)
            {
                setImage(ImageCache.img_enemy_beam);
                return true;
            }
            else return false;
        }
    }
    public Beam clone()
    {
        return clone(false);
    }
    public Beam clone(boolean imgnull)
    {        
        Beam newMissile = new Beam();
        newMissile.setX(getX());
        newMissile.setY(getY());
        newMissile.setDy(getDy());
        newMissile.setColor(getColor());
        newMissile.setW(getW());
        newMissile.setH(getH());
        newMissile.setLaunched(isLaunched());
        newMissile.setOwner(getOwner());
        newMissile.setDamage(getDamage());
        if(imgnull) newMissile.setImage(null);
        else newMissile.setImage(getNowImage());
        newMissile.setColor(getColor());
        return newMissile;
    }
}