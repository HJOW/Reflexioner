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

public class SuperMissile extends Missile
{
    private static final long serialVersionUID = -3924286124331256018L;

    public SuperMissile()
    {
        super();
        setW(getW() * 2);
        setH(getH() * 2);
    }
    public SuperMissile(String path)
    {
        this();
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    public SuperMissile(String path, int owner)
    {
        super(path, owner);
    }
    public String getMissileName()
    {
        return "super";
    }
    @Override
    protected boolean loadCache()
    {
        if(getOwner() <= -1)
        {            
            if(ImageCache.img_super_missile != null)
            {
                setImage(ImageCache.img_super_missile);
                return true;
            }
            else return false;
        }
        else
        {
            if(ImageCache.img_enemy_super_missile != null)
            {
                setImage(ImageCache.img_enemy_super_missile);
                return true;
            }
            else return false;
        }
    }
    public Missile clone()
    {
        return clone(false);
    }
    public Missile clone(boolean imgnull)
    {        
        SuperMissile newMissile = new SuperMissile();
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