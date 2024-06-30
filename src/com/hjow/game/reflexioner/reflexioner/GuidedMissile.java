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
import java.util.List;

import javax.swing.JPanel;

public class GuidedMissile extends Missile
{
    private static final long serialVersionUID = 8354762264500598428L;
    protected transient List<Enemy> enemyList;
    protected transient SpaceShip spaceShip;
    protected transient int maxValue = 0, distance = 0;    
    protected transient GraphicObject target = null;
    protected transient boolean exist = false;
    private int speed = 2;
    private int guideRound = 1000;
    private int dx = 0;
    
    
    
    public GuidedMissile()
    {
        
    }
    public GuidedMissile(List<Enemy> enemyList, SpaceShip spaceShip)
    {
        super();
        this.enemyList = enemyList;
        this.spaceShip = spaceShip;
        setY(-200);
        setW(10);
        setH(10);
        setHp(10000);
        setSpeed(32);
        setDamage(getDamage() / 2);
        setDy(- (Arena.getGspeed()));
        setColor(Reflexioner.color_spaceShip_missile);
    }
    public GuidedMissile(List<Enemy> enemyList, SpaceShip spaceShip, String path)
    {
        this(enemyList, spaceShip);
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    public GuidedMissile(List<Enemy> enemyList, SpaceShip spaceShip, int owner, String path)
    {
        this(enemyList, spaceShip);
        setOwner(owner);
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    public void setSpaceShipData(SpaceShip data)
    {
        this.spaceShip = data;
    }
    @Override
    protected boolean loadCache()
    {
        if(getOwner() <= -1)
        {            
            if(ImageCache.img_guide_missile != null)
            {
                setImage(ImageCache.img_guide_missile);
                return true;
            }
            else return false;
        }
        else
        {
            if(ImageCache.img_enemy_guide_missile != null)
            {
                setImage(ImageCache.img_enemy_guide_missile);
                return true;
            }
            else return false;
        }
    }
    @Override
    public void update()
    {
        if(isLaunched())
        {
            setY(getY() + getDy());
            setX(getX() + getDx());
        }
        setHp(getHp() - 1);
        maxValue = 0;
        if(enemyList.size() >= 1)
        {
            if(target != null)
            {
                exist = false;
                for(Enemy e : enemyList)
                {
                    if(e.equals(target))
                    {
                        exist = true;
                        break;
                    }
                }
                if(! exist)
                    target = null;
            }
            if(target == null)
            {
                if(getOwner() == SPACESHIP)
                {
                    for(Enemy e : enemyList)
                    {            
                        distance = (int) Math.round(Math.sqrt(Math.pow(this.getX() - e.getX(), 2) + Math.pow(this.getY() - e.getY(), 2)));
                        if(maxValue < distance)
                        {
                            maxValue = distance;
                            target = e;
                        }
                    }
                }
                else
                {
                    target = spaceShip;
                }
            }
            if(target != null)
            {
                distance = (int) Math.round(Math.sqrt(Math.pow(this.getX() - target.getX(), 2) + Math.pow(this.getY() - target.getY(), 2)));
                
                maxValue = target.getX() - this.getX();
                maxValue = maxValue * speed;
                maxValue = (int) Math.ceil(maxValue / (distance + 0.01));
                maxValue = (int) Math.ceil(maxValue / 4.0);
                setDx(maxValue);
                
                maxValue = target.getY() - this.getY();
                maxValue = maxValue * speed;
                maxValue = (int) Math.ceil(maxValue / (distance + 0.01));
                maxValue = (int) Math.ceil(maxValue / 4.0);                
                
                if(Math.abs(maxValue) <= 0) maxValue = (int) Math.ceil(speed / 16.0);
                setDy(maxValue);                
            }
            else
            {
                setDx(0);
                setDy((int) Math.ceil(Arena.getGspeed() / 16.0));
            }
        }
        else target = null;
        //if(getY() <= -10 || getY() >= Reflexioner.size_y + 10) setLaunched(false);
    }
    @Override
    public void draw(Graphics g, JPanel a)
    {
        if(image == null)
            g.fillOval(Arena.convertX(getX() - (int)(getW()/2.0), a), Arena.convertY(getY() - (int)(getW()/2.0), a), Arena.convertWidth(getW(), a), Arena.convertHeight(getH(), a));
        else
            super.draw(g, a);
    }
    public List<Enemy> getEnemyList()
    {
        return enemyList;
    }
    
    public void setEnemyList(List<Enemy> enemyList)
    {
        this.enemyList = enemyList;
    }
    public int getGuideRound()
    {
        return guideRound;
    }
    public void setGuideRound(int guideRound)
    {
        this.guideRound = guideRound;
    }
    public int getDx()
    {
        return dx;
    }
    public void setDx(int dx)
    {
        this.dx = dx;
    }
    public int getSpeed()
    {
        return speed;
    }
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    public String getMissileName()
    {
        return "guide";
    }
    public Missile clone()
    {
        return clone(false);
    }
    public Missile clone(boolean imgnull)
    {        
        GuidedMissile newMissile = new GuidedMissile();
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
        newMissile.setSpeed(getSpeed());
        newMissile.setGuideRound(guideRound);
        newMissile.setDx(getDx());
        newMissile.setColor(getColor());
        return newMissile;
    }
}