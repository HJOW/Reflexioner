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
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.hjow.game.reflexioner.mainClasses.RunManager;
import com.hjow.game.reflexioner.setting.Setting;

public class OvalBoom extends OvalObject implements Boom
{
    private static final long serialVersionUID = -1719136597766950927L;
    private int maker = 0;
    private transient BufferedImage image = null;
    
    
    public OvalBoom()
    {
        super();
        setMax_hp(5);
        setHp(5);
        setR((int) (getHp() * 10));
        setColor(new Color(255, 100, 100));
    }
    public OvalBoom(int r)
    {
        super();
        setMax_hp(r);
        setHp(r);
        setR((int) (getHp() * 10));
        setColor(new Color(255, 100, 100));
    }
    public OvalBoom(int r, int maker)
    {
        super();
        setMaker(maker);
        setMax_hp(r);
        setHp(r);
        setR((int) (getHp() * 10));
        setColor(new Color(255, 100, 100));
    }
    public void setRx(int r)
    {
        setHp(r);
        setR((int) (getHp() * 10));
    }
    @Override
    public void update()
    {
        setHp(getHp() - 1);
        setR((int) (getHp() * 10));
        t_r = 255;
        t_g = (int) (getColor().getGreen() + ((getHp() / getMax_hp()) * 255));
        t_b = (int) (getColor().getBlue() + ((getHp() / getMax_hp()) * 255));
        if(t_r < 0) t_r = 0;
        if(t_g < 0) t_g = 0;
        if(t_b < 0) t_b = 0;
        if(t_r > 255) t_r = 255;
        if(t_g > 255) t_g = 255;
        if(t_b > 255) t_b = 255;
        setColor(new Color(t_r, t_g, t_b));
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        
    }
    public int getMaker()
    {
        return maker;
    }
    public void setMaker(int maker)
    {
        this.maker = maker;
    }    
    public void setImage(BufferedImage image)
    {
        this.image = image;
    }
    public void removeImage()
    {
        this.image = null;
    }
    public void loadImage()
    {
        loadImage(Setting.load().getDefaultPath());
    }
    public String getBoomName()
    {
        return getBoomName(true);
    }
    public String getBoomName(boolean makerInclude)
    {
        String results = "ovalboom";
        if(makerInclude && maker >= 0) results = "enemy_" + results;
        return results;
    }
    protected boolean loadCache()
    {
        if(getMaker() <= -1)
        {            
            if(ImageCache.img_ovalboom != null)
            {
                setImage(ImageCache.img_ovalboom);
                return true;
            }
            else return false;
        }
        else
        {
            if(ImageCache.img_enemy_ovalboom != null)
            {
                setImage(ImageCache.img_enemy_ovalboom);
                return true;
            }
            else return false;
        }
    }
    public void loadImage(String path)
    {
        if(loadCache()) return;
        try
        {
            File target = new File(RunManager.r65279(path + getBoomName(true) + ".png"));
            if(! target.exists()) target = new File(RunManager.r65279(path + getBoomName(true) + ".jpg"));
            if(! target.exists()) target = new File(RunManager.r65279(path + getBoomName(false) + ".png"));
            if(! target.exists()) target = new File(RunManager.r65279(path + getBoomName(false) + ".jpg"));
            if(! target.exists()) target = new File(RunManager.r65279(path + "boom_" + "default" + ".png"));
            if(! target.exists()) target = new File(RunManager.r65279(path + "boom_" + "default" + ".jpg"));
            if(! target.exists()) target = new File(RunManager.r65279(path + "boom" + ".png"));
            if(! target.exists()) target = new File(RunManager.r65279(path + "boom" + ".jpg"));
            if(! target.exists()) return;
            image = ImageIO.read(target);
        } 
        catch (Exception e)
        {
            
        }
    }
    @Override
    public void draw(Graphics g, JPanel a)
    {
        if(Reflexioner.image_allow && image != null)
        {
            g.drawImage(image, Arena.convertX(getX() - (int)(getR() / 2.0), a), Arena.convertY(getY() - (int)(getR() / 2.0), a), Arena.convertWidth(getR(), a), Arena.convertHeight(getR(), a), null);
        }
        else
        {
            super.draw(g, a);
        }
    }
    public OvalBoom clone()
    {
        return clone(false);
    }
    public OvalBoom clone(boolean imgnull)
    {
        OvalBoom newBoom = new OvalBoom();
        
        newBoom.setX(getX());
        newBoom.setY(getY());
        newBoom.setColor(getColor());
        newBoom.setR(getR());
        newBoom.setHp(getHp());
        newBoom.setMaker(getMaker());
        newBoom.setImage(null);
        newBoom.setColor(getColor());
        if(imgnull) newBoom.setImage(null);
        else newBoom.setImage(getLoadedImage());
        return newBoom;
    }
    protected BufferedImage getLoadedImage()
    {
        return image;
    }
}