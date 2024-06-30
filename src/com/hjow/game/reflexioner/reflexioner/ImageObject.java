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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class ImageObject extends RectObject
{
    private static final long serialVersionUID = 4633772727235919989L;
    private transient BufferedImage img = null;
    private String name = "";
    public ImageObject()
    {
        
    }
    public ImageObject(String name)
    {
        this.name = name;
    }
    public void load() throws IOException
    {
        img = ImageIO.read(new File(name));
    }
    @Override
    public int getW()
    {
        return img.getWidth();
    }
    @Override
    public int getH()
    {
        return img.getHeight();
    }
    @Override
    public void draw(Graphics g, JPanel a)
    {
        g.drawImage(img, Arena.convertX(getX() - (int)(img.getWidth()/2.0), a), Arena.convertY(getY() - (int)(img.getHeight()/2.0), a), null);
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
}