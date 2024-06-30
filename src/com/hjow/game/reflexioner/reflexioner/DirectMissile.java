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
import java.util.List;
import java.util.Vector;

public class DirectMissile extends GuidedMissile
{
    private static final long serialVersionUID = -3595086904413941868L;

    public DirectMissile()
    {
        setHp(1);
    }
    public DirectMissile(String path)
    {
        this();
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    public DirectMissile(String path, int owner)
    {
        this();
        setOwner(owner);
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    @Override
    protected boolean loadCache()
    {
        if(getOwner() <= -1)
        {            
            if(ImageCache.img_direct_missile != null)
            {
                setImage(ImageCache.img_direct_missile);
                return true;
            }
            else return false;
        }
        else
        {
            if(ImageCache.img_enemy_direct_missile != null)
            {
                setImage(ImageCache.img_enemy_direct_missile);
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
    }
    public static List<Missile> spread(int x, int y, int r, int owner, int howMany, SpaceShip target, double ratio, List<Enemy> enemyList, long difficulty)
    {
        List<Missile> missiles = new Vector<Missile>();
        double calcs_x, calcs_y, dist;
        for(int i=0; i<howMany; i++)
        {
            
            GuidedMissile newMissile;
            if(Math.random() > ratio && owner != Missile.SPACESHIP)
            {
                newMissile = new GuidedMissile(enemyList, target);
                newMissile.setSpeed(newMissile.getSpeed() / 2);
                newMissile.setColor(Reflexioner.color_enemy_missile);
                newMissile.setColor(new Color(newMissile.getColor().getRed() / 2, newMissile.getColor().getGreen() / 2, newMissile.getColor().getBlue() / 2));
            }
            else
            {
                newMissile = new DirectMissile();
                newMissile.setColor(Reflexioner.color_enemy_missile);
            }
            
            newMissile.setOwner(owner);            
            newMissile.setHp(1000);
            newMissile.setX(x + ((i - (howMany / 2)) * (480 / howMany)));
            newMissile.setY(y);
            calcs_y = target.getY() - newMissile.getY();
            calcs_x = target.getX() - newMissile.getX();
            newMissile.setDx((int)(calcs_x));
            newMissile.setDy((int)(calcs_y));            
            dist = Math.sqrt(Math.pow(newMissile.getDx(), 2) + Math.pow(newMissile.getDy(), 2));
            newMissile.setDx((int)(newMissile.getDx() * 4.0 / (dist)));
            newMissile.setDy((int)(newMissile.getDy() * 4.0 / (dist)));
            if(owner == Missile.SPACESHIP)
            {
                newMissile.setDy((newMissile.getDy() * -1) - newMissile.getSpeed());
                if(newMissile.getDy() < newMissile.getSpeed() * 2) newMissile.setDy(newMissile.getSpeed() * -2);
                newMissile.setColor(Reflexioner.color_spaceShip_missile);
                if(! (newMissile instanceof DirectMissile))
                {
                    newMissile.setColor(new Color(newMissile.getColor().getRed() / 2, newMissile.getColor().getGreen() / 2, newMissile.getColor().getBlue() / 2));
                }
            }
            newMissile.setLaunched(true);
            newMissile.setDamage(75 + (int) (difficulty / 1500));
            newMissile.setW(newMissile.getW() * 2);
            newMissile.setH(newMissile.getH() * 2);
            
            
            missiles.add(newMissile);
        }
        return missiles;
    }    
    public String getMissileName()
    {
        return "direct";
    }
    public Missile clone()
    {
        return clone(false);
    }
    public Missile clone(boolean imgnull)
    {        
        DirectMissile newMissile = new DirectMissile();
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
        newMissile.setGuideRound(getGuideRound());
        newMissile.setDx(getDx());
        newMissile.setColor(getColor());
        return newMissile;
    }
}