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

import java.util.List;
import java.util.Vector;


public class BigEnemy extends Enemy
{
    private static final long serialVersionUID = 8847865107124168926L;
    private int missiles = 1;
    private boolean guide = false;
    public transient int phase = 0;
    public BigEnemy()
    {
        super();
        setColor(Reflexioner.color_bigenemy);
        setHp(getHp() * 2);
    }
    public BigEnemy(String path)
    {
        this();
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    public BigEnemy(String path, boolean player_own)
    {
        this();
        setPlayer_own(player_own);
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    @Override
    protected boolean loadCache()
    {
        if(isPlayer_own())
        {
            if(ImageCache.img_r_bigEnemy != null)
            {
                setImage(ImageCache.img_r_bigEnemy);
                return true;
            }
            else if(ImageCache.img_r_enemy != null)
            {
                setImage(ImageCache.img_r_enemy);
                return true;
            }
            else return false;
        }
        else
        {
            if(ImageCache.img_bigEnemy != null)
            {
                setImage(ImageCache.img_bigEnemy);
                return true;
            }
            else if(ImageCache.img_enemy != null)
            {
                setImage(ImageCache.img_enemy);
                return true;
            }
            else return false;
        }        
    }
    public int getMissiles()
    {
        return missiles;
    }
    public void setMissiles(int missiles)
    {
        this.missiles = missiles;
    }
    public boolean isGuide()
    {
        return guide;
    }
    public void setGuide(boolean guide)
    {
        this.guide = guide;
    }
    public String getEnemyName()
    {
        return "big";
    }
    @Override
    public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
    {
        List<Missile> results = new Vector<Missile>();
        Missile newMissile;
        
        
        int centerLocate = this.getMissiles() / 2;
        for(int j=0; j<this.getMissiles(); j++)
        {
            if(this.isGuide())
            {
                newMissile = new GuidedMissile(enemies, spaceShip, file_path);
                ((GuidedMissile)newMissile).setSpeed(16);
                ((GuidedMissile)newMissile).setHp(100);
            }
            else
            {
                newMissile = new ReflexMissile(file_path);
                ((ReflexMissile)newMissile).setDx(0);                                                
                ((ReflexMissile)newMissile).setHp(300);
                ((ReflexMissile)newMissile).setSide_reflex(true);
                newMissile.setW(newMissile.getW() * 2);
            }
            newMissile.setOwner(owner);
            newMissile.setLaunched(true);                                    
            newMissile.setX(this.getX() + ((centerLocate - j) * 30));
            newMissile.setY(this.getY() + this.getR());
            newMissile.setDy(newMissile.getDy() * -1);            
            newMissile.setDamage(this.getDamage());
            if(isPlayer_own())
            {
                newMissile.setDy(newMissile.getDy() * -1);
                newMissile.setOwner(Missile.SPACESHIP);
                newMissile.setColor(Reflexioner.color_spaceShip_missile);
            }
            else newMissile.setColor(Reflexioner.color_enemy_missile);
            results.add(newMissile);
        }
        this.setEnergy(0);
        
        return results;
    }
    @Override
    public Enemy clone(boolean imgnull)
    {
        BigEnemy newOne = new BigEnemy();
        newOne.setDx(getDx());
        newOne.setDy(getDy());
        newOne.setEnergy(getEnergy());
        newOne.setDamage(getDamage());
        newOne.setMax_energy(getMax_energy());
        newOne.setMax_hp(getMax_hp());
        newOne.setHp(getHp());
        newOne.setColor(getColor());
        newOne.setR(getR());
        newOne.setX(getX());
        newOne.setY(getY());
        newOne.setGuide(isGuide());
        newOne.setMissiles(getMissiles());
        newOne.setColor(getColor());
        newOne.setOwn_unique(isOwn_unique());
        if(imgnull) newOne.setImage(null);
        else newOne.setImage(loadedImage());
        return newOne;
    }
    public int makeItemCount(long difficulty, long difficulty_delay, double randomNumber)
    {
        if(isOwn_unique()) return 0;
        if(difficulty < difficulty_delay)
        {
            if(randomNumber >= 0.8) return 1;
        }
        else if(difficulty < difficulty_delay * 2)
        {
            if(randomNumber >= 0.95) return 1;
        }
        else
        {
            if(randomNumber >= 0.98) return 1;
        }
        return 0;
    }
}
