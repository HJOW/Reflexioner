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
import java.awt.geom.Area;

import javax.swing.JPanel;

public interface Boom
{
    public void update();
    public Area area();
    public boolean collapse(GraphicObject other);
    public long getHp();
    public void draw(Graphics g, JPanel a);
    public Color getColor();
    public void setX(int x);
    public void setY(int y);
    public int getX();
    public int getY();
    public String getBoomName(boolean makerInclude);
    public String getBoomName();
    public void loadImage();
    public void loadImage(String path);
    public Boom clone();
    public Boom clone(boolean imgnull);
}
