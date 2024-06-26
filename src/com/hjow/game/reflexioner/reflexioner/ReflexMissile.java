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

public class ReflexMissile extends DirectMissile
{
    private static final long serialVersionUID = 5077561900373055591L;
    private boolean side_reflex = false;

    public ReflexMissile()
    {
        super();
        setHp(1000);
        setDy(- (Arena.getGspeed()));
        
        if(Math.random() >= 0.5)
            setDx(-1 * (int)Math.round(Arena.getGspeed() / 2));
        else
            setDx((int) Math.round(Arena.getGspeed() / 2));
    }
    public ReflexMissile(String path)
    {
        this();
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    public ReflexMissile(String path, int owner)
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
            if(ImageCache.img_flex_missile != null)
            {
                setImage(ImageCache.img_flex_missile);
                return true;
            }
            else return false;
        }
        else
        {
            if(ImageCache.img_enemy_flex_missile != null)
            {
                setImage(ImageCache.img_enemy_flex_missile);
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
            setX(getX() + getDx());
            setY(getY() + getDy());
            //System.out.println(this + " b " + getX() + ", " + getY() + " : " + getDx() + ", " + getDy());        
            if(side_reflex)
            {
                if(getY() < 0)
                {
                    setY(0);
                    setDy(1 * Arena.getGspeed());
                    if(getDx() == 0)
                    {
                        if(Math.random() >= 0.5)
                            setDx(Arena.maxWidth());
                        else
                            setDx(-1 * Arena.maxWidth());
                    }
                    //System.out.println(this + " y is under 0 - " + getDy());
                }
                else if(getY() > Arena.maxHeight())
                {
                    setY(Arena.maxHeight());
                    setDy(-1 * Arena.getGspeed());
                    if(getDx() == 0)
                    {
                        if(Math.random() >= 0.5)
                            setDx(Arena.maxWidth());
                        else
                            setDx(-1 * Arena.maxWidth());
                    }
                    //System.out.println(this + " y is up - " + getDy());
                }
                if(getX() < 0)
                {                
                    setX(0);
                    setDx(1 * Arena.getGspeed());                
                    //System.out.println(this + " x is under 0 - " + getDx());
                }
                else if(getX() > Arena.maxWidth())
                {
                    setX(Arena.maxWidth());
                    setDx(-1 * Arena.getGspeed());                
                    //System.out.println(this + " x is up - " + getDx());
                }
            }
            //System.out.println(this + " a " + getX() + ", " + getY() + " : " + getDx() + ", " + getDy());
        }
        setHp(getHp() - 1);
        if(getHp() <= 0) setLaunched(false);
    }
    public boolean isSide_reflex()
    {
        return side_reflex;
    }
    public void setSide_reflex(boolean side_reflex)
    {
        this.side_reflex = side_reflex;
    }
    public String getMissileName()
    {
        if(side_reflex) return "half_reflex";
        else return "reflex";
    }
    public Missile clone()
    {
        return clone(false);
    }
    public Missile clone(boolean imgnull)
    {        
        ReflexMissile newMissile = new ReflexMissile();
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
        newMissile.setSide_reflex(isSide_reflex());
        newMissile.setColor(getColor());
        return newMissile;
    }
}