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
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.hjow.game.reflexioner.mainClasses.RunManager;
import com.hjow.game.reflexioner.setting.Setting;

public class ReflexDecorate extends OvalObject
{
    private static final long serialVersionUID = 7309715590955478285L;
    private transient BufferedImage image = null;
    private int dy = 1;
    private String name = "";
    
    public ReflexDecorate()
    {
        super();
        setHp(1);
    }
    public ReflexDecorate(int x, int y, int dy, int r)
    {
        super();
        setX(x);
        setY(y);
        setDy(dy);
        setR(r);
        setHp(1);
    }
    public ReflexDecorate(String name, int x, int y, int dy, int r)
    {
        super();
        setName(name);
        setX(x);
        setY(y);
        setDy(dy);
        setR(r);
        setHp(1);
    }
    public ReflexDecorate(String name, int x, int y, int dy, int r, String path)
    {
        super();
        setName(name);
        setX(x);
        setY(y);
        setDy(dy);
        setR(r);
        setHp(1);
        loadImage(path);
    }
    @Override
    public void update()
    {
        setY(getY() + getDy());
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        
    }
    public int getDy()
    {
        return dy;
    }
    public void setDy(int dy)
    {
        this.dy = dy;
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
    public void loadImage(String path)
    {
        if(ImageCache.img_decorate != null)
        {
            image = ImageCache.img_decorate;
            return;
        }
        try
        {
            File target = new File(RunManager.r65279(path + "deco_" + name + ".png"));
            if(! target.exists()) target = new File(RunManager.r65279(path + "deco_" + name + ".jpg"));
            if(! target.exists()) target = new File(RunManager.r65279(path + "deco_" + "default" + ".png"));
            if(! target.exists()) target = new File(RunManager.r65279(path + "deco_" + "default" + ".jpg"));
            if(! target.exists()) return;
            image = ImageIO.read(target);
        } 
        catch (Exception e)
        {
            image = null;
        }
    }
    @Override
    public void draw(Graphics g, JPanel a)
    {
        if(image == null || (! Reflexioner.image_allow))
            super.draw(g, a);
        else
        {
            g.drawImage(image, Arena.convertX(getX() - (int)(getR() / 2.0), a), Arena.convertY(getY() - (int)(getR() / 2.0), a), Arena.convertWidth(getR(), a), Arena.convertHeight(getR(), a), null);            
        }
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public ReflexDecorate clone()
    {
        return clone(false);
    }
    public ReflexDecorate clone(boolean imgnull)
    {
        ReflexDecorate newDeco = new ReflexDecorate(new String(getName()), getX(), getY(), getDy(), getR());
        return newDeco;
    }
}
