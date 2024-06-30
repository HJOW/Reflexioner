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

public class ItemUseBoom extends OvalBoom
{
    private static final long serialVersionUID = -5442134646112323388L;
    public ItemUseBoom()
    {
        super();
        setColor(Reflexioner.color_useItem);
    }
    public ItemUseBoom(int r)
    {
        super(r);
        setColor(Reflexioner.color_useItem);
    }
    public String getBoomName(boolean makerInclude)
    {
        String results = "itemboom";
        return results;
    }
    protected boolean loadCache()
    {
        if(ImageCache.img_itemuseboom != null)
        {
            setImage(ImageCache.img_itemuseboom);
            return true;
        }
        else return false;
    }
    public OvalBoom clone()
    {
        return clone(false);
    }
    public OvalBoom clone(boolean imgnull)
    {
        ItemUseBoom newBoom = new ItemUseBoom();
        
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
}