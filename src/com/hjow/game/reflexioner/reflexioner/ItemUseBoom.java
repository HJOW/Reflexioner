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