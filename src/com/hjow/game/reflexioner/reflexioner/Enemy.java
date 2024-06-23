package com.hjow.game.reflexioner.reflexioner;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.hjow.game.reflexioner.mainClasses.RXUtils;
import com.hjow.game.reflexioner.mainClasses.RunManager;
import com.hjow.game.reflexioner.setting.Setting;

public class Enemy extends OvalObject implements HaveEnergy
{
    private static final long serialVersionUID = 6365464792268179056L;
    private int dx = -5;
    private int dy = -5;
    private long energy = 0; 
    private long damage = 0;
    private long max_energy = 200;
    private boolean player_own = false;
    private transient BufferedImage image = null;
    private boolean own_unique = false;
    
    public Enemy()
    {
        super();
        setX(Arena.maxWidth());
        setY(10);
        setR(Arena.getGenemyR());
        setColor(Reflexioner.color_enemy);
        setHp(500);
        setMax_hp(500);
        setDamage((getHp() / 10));
        loadCache();
    }
    public Enemy(String path)
    {
        this();
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
    }
    public Enemy(String path, boolean player_own)
    {
        this();
        setPlayer_own(player_own);
        if(path != null && Reflexioner.image_allow)
            loadImage(path);
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
    protected boolean loadCache()
    {
        if(isPlayer_own())
        {
            if(ImageCache.img_r_enemy != null)
            {
                image = ImageCache.img_r_enemy;
                return true;
            }
            else return false;
        }
        else
        {
            if(ImageCache.img_enemy != null)
            {
                image = ImageCache.img_enemy;
                return true;
            }
            else return false;
        }
    }
    public void loadImage(String path)
    {
        if(loadCache()) return;
        if(isPlayer_own())
        {
            try
            {
                File target = new File(RunManager.r65279(path + "r_" + getEnemyName() + ".png"));
                if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + getEnemyName() + ".jpg"));
                if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".png"));
                if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".jpg"));
                if(! target.exists()) 
                {
                    InputStream inp = System.class.getClassLoader().getResourceAsStream("resources.image." + getEnemyName() + ".png");
                    if(inp == null) inp = System.class.getClassLoader().getResourceAsStream("resources.image." + getEnemyName() + ".jpg");
                    if(inp == null) inp = System.class.getClassLoader().getResourceAsStream("resources.image." + "enemy_default" + ".jpg");
                    if(inp == null) inp = System.class.getClassLoader().getResourceAsStream("resources.image." + "enemy_default" + ".png");
                    
                    if(inp != null) {
                        try 
                        {
                            image = ImageIO.read(inp);
                        } 
                        finally 
                        {
                            inp.close();
                        }
                    }
                    return;
                }
                image = ImageIO.read(target);
            } 
            catch (Exception e)
            {
                
            }
        }
        else
        {
            try
            {
                File target = new File(RunManager.r65279(path + getEnemyName() + ".png"));
                if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + getEnemyName() + ".jpg"));
                if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + "default" + ".png"));
                if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + "default" + ".jpg"));
                if(! target.exists()) 
                {
                    InputStream inp = System.class.getClassLoader().getResourceAsStream("resources.image." + getEnemyName() + ".png");
                    if(inp == null) inp = System.class.getClassLoader().getResourceAsStream("resources.image." + getEnemyName() + ".jpg");
                    if(inp == null) inp = System.class.getClassLoader().getResourceAsStream("resources.image." + "enemy_default" + ".jpg");
                    if(inp == null) inp = System.class.getClassLoader().getResourceAsStream("resources.image." + "enemy_default" + ".png");
                    
                    if(inp != null) {
                        try 
                        {
                            image = ImageIO.read(inp);
                        } 
                        finally 
                        {
                            inp.close();
                        }
                    }
                    return;
                }
                image = ImageIO.read(target);
            } 
            catch (Exception e)
            {
                
            }
        }
    }
    public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
    {
        List<Missile> results = new Vector<Missile>();
        Missile newMissile;
        
        newMissile = new Missile(file_path);
        newMissile.setOwner(owner);
        newMissile.setLaunched(true);
        newMissile.setX(this.getX());
        newMissile.setY(this.getY() + this.getR());
        newMissile.setDy(newMissile.getDy() * -1);
        if(player_own)
        {
            newMissile.setDy(newMissile.getDy() * -1);
            newMissile.setOwner(Missile.SPACESHIP);
            newMissile.setColor(Reflexioner.color_spaceShip_missile);
        }
        else newMissile.setColor(Reflexioner.color_enemy_missile);
        newMissile.setDamage(this.getDamage());
        results.add(newMissile);
        this.setEnergy(0);
        
        return results;
    }
    public String getEnemyName()
    {
        return "normal";
    }
    @Override
    public Area area()
    {
        if(image == null)
            return super.area();
        else
            return new Area(new Rectangle(getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), (int)(getR()), (int)(getR())));
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
    @Override
    public void update()
    {
        setX(getX() + dx);
        setY(getY() + dy);
        
        if(player_own)
        {
            if(getX() < getR())
            {
                dx = (int) ((Arena.getGspeed() / 2) * Math.random());
            }
            if(getX() > Arena.maxWidth() - getR())
            {
                dx = (int) -((Arena.getGspeed() / 2) * Math.random());
            }
            if(getY() < ((Arena.maxHeight() * 2) / 3) - getR())
            {
                dy = (int) ((Arena.getGspeed() / 2) * Math.random());
            }
            if(getY() > (Arena.maxHeight()) - getR())
            {
                dy = (int) -((Arena.getGspeed() / 2) * Math.random());
            }
        }
        else
        {
            if(getX() < getR())
            {
                //System.out.println("!! X " + getX() + "/" + getR());
                dx = (int) ((Arena.getGspeed() / 2) * Math.random());
            }
            if(getX() > Arena.maxWidth() - getR())
            {
                //System.out.println("!! X " + getX() + "/" + (Reflexioner.size_x - getR()));
                dx = (int) -((Arena.getGspeed() / 2) * Math.random());
            }
            if(getY() < getR())
            {
                //System.out.println("!! Y " + getY() + "/" + getR());
                dy = (int) ((Arena.getGspeed() / 2) * Math.random());
            }
            if(getY() > (Arena.maxHeight() / 3) - getR())
            {
                //System.out.println("!! Y " + getY() + "/" + ((Reflexioner.size_y / 5) - getR()));
                dy = (int) -((Arena.getGspeed() / 2) * Math.random());
            }
        }
        if(energy <= max_energy) energy++;
        
        //System.out.println(getDx() + ", " + getDy());
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        
    }
    
    public int getDx()
    {
        return dx;
    }
    public void setDx(int dx)
    {
        this.dx = dx;
    }
    public int getDy()
    {
        return dy;
    }
    public void setDy(int dy)
    {
        this.dy = dy;
    }
    public long getEnergy()
    {
        return energy;
    }
    public void setEnergy(long energy)
    {
        this.energy = energy;
    }
    public long getDamage()
    {
        return damage;
    }
    public void setDamage(long damage)
    {
        this.damage = damage;
    }
    public long getMax_energy()
    {
        return max_energy;
    }
    public void setMax_energy(long max_energy)
    {
        this.max_energy = max_energy;
    }    
    public int getMissiles()
    {
        return 1;
    }
    public boolean isGuide()
    {
        return false;
    }
    public boolean isPlayer_own()
    {
        return player_own;
    }
    public void setPlayer_own(boolean player_own)
    {
        this.player_own = player_own;
    }
    public Enemy clone()
    {
        return clone(false);
    }
    public Enemy clone(boolean imgnull)
    {
        Enemy newOne = new Enemy();
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
        newOne.setColor(getColor());
        newOne.setOwn_unique(isOwn_unique());
        if(imgnull) newOne.setImage(null);
        else newOne.setImage(loadedImage());
        return newOne;
    }
    protected BufferedImage loadedImage()
    {
        return image;
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
            if(randomNumber >= 0.99) return 1;
        }
        else
        {
            if(randomNumber >= 0.995) return 1;
        }
        return 0;
    }
    public boolean isOwn_unique()
    {
        return own_unique;
    }
    public void setOwn_unique(boolean own_unique)
    {
        this.own_unique = own_unique;
    }
	public static String enemyToString(Enemy enemy)
	{
	    Properties prop = new Properties();
	    prop.setProperty("Type", "normal");
	    
	    if(enemy instanceof BigEnemy)
	    {
	    	prop.setProperty("Guide", "" + ((BigEnemy) enemy).isGuide());
	    	prop.setProperty("Missiles", "" + ((Boss) enemy).getMissiles());
	    	
	    	if(enemy instanceof Boss)
	    	{
	    		prop.setProperty("BeamDelay"  , "" + ((Boss) enemy).getBeam_std());
	    		prop.setProperty("AttackRatio", "" + ((Boss) enemy).getRatio());
	    		prop.setProperty("Unique"     , "" + ((Boss) enemy).isUnique());
	    	}
	    }
	    if(enemy instanceof HyperBoss)
	    {
	    	prop.setProperty("Type", "boss_3");
	    }
	    else if(enemy instanceof ExtremeBoss)
	    {
	    	prop.setProperty("Type", "boss_2");
	    }
	    else if(enemy instanceof Boss)
	    {
	    	prop.setProperty("Type", "boss");
	    }
	    else if(enemy instanceof BigEnemy)
	    {
	    	prop.setProperty("Type", "bigenemy");
	    }
	    prop.setProperty("HP"       , "" + enemy.getMax_hp()    );
	    prop.setProperty("Size"     , "" + enemy.getR()         );
	    prop.setProperty("SpeedX"   , "" + enemy.getDx()        );
	    prop.setProperty("SpeedY"   , "" + enemy.getDy()        );
	    prop.setProperty("FireDelay", "" + enemy.getMax_energy());
	    prop.setProperty("Damage"   , "" + enemy.getDamage()    );       
	    
	    return RXUtils.serializeProperty(prop);
	}
	public static Enemy stringToEnemy(String str)
	{
	    Enemy enemy = new Enemy();
	    enemy.setColor(Reflexioner.color_enemy);
	    enemy.setX(Arena.maxWidth());
	    enemy.setY(10);
	    enemy.setR(Arena.getGenemyR());
	    enemy.setColor(Reflexioner.color_enemy);
	    enemy.setHp(500);
	    enemy.setMax_hp(500);
	    enemy.setDamage((enemy.getHp() / 10));
	    
	    Properties prop = RXUtils.extractProperty(str);
	    String type = prop.getProperty("Type");
	    if(type.equals("boss_3"))
	    {
	    	enemy = new HyperBoss();
            enemy.setColor(Reflexioner.color_bigenemy);
            enemy.setX(Arena.maxWidth());
            enemy.setY(10);
            enemy.setR(Arena.getGenemyR() * 2);
            enemy.setColor(Reflexioner.color_enemy);
            enemy.setHp(5000);
            enemy.setMax_hp(5000);
            enemy.setDamage((enemy.getHp() / 10));
            ((Boss) enemy).setBeam_energy(300);
            ((Boss) enemy).setRatio(0.9);
	    }
	    else if(type.equals("boss_2"))
	    {
	    	enemy = new ExtremeBoss();
            enemy.setColor(Reflexioner.color_bigenemy);
            enemy.setX(Arena.maxWidth());
            enemy.setY(10);
            enemy.setR(Arena.getGenemyR() * 2);
            enemy.setColor(Reflexioner.color_enemy);
            enemy.setHp(5000);
            enemy.setMax_hp(5000);
            enemy.setDamage((enemy.getHp() / 10));
            ((Boss) enemy).setBeam_energy(300);
            ((Boss) enemy).setRatio(0.9);
	    }
	    else if(type.equals("boss"))
	    {
	    	enemy = new Boss();
            enemy.setColor(Reflexioner.color_bigenemy);
            enemy.setX(Arena.maxWidth());
            enemy.setY(10);
            enemy.setR(Arena.getGenemyR() * 2);
            enemy.setColor(Reflexioner.color_enemy);
            enemy.setHp(5000);
            enemy.setMax_hp(5000);
            enemy.setDamage((enemy.getHp() / 10));
            ((Boss) enemy).setBeam_energy(300);
            ((Boss) enemy).setRatio(0.9);
	    }
	    else if(type.equals("bigenemy"))
	    {
	    	enemy = new Boss();
            enemy.setColor(Reflexioner.color_bigenemy);
            enemy.setX(Arena.maxWidth());
            enemy.setY(10);
            enemy.setR(Arena.getGenemyR() * 2);
            enemy.setColor(Reflexioner.color_enemy);
            enemy.setHp(5000);
            enemy.setMax_hp(5000);
            enemy.setDamage((enemy.getHp() / 10));
            ((Boss) enemy).setBeam_energy(300);
            ((Boss) enemy).setRatio(0.9);
	    }
	    
	    enemy.setMax_hp(Long.parseLong(prop.getProperty(prop.getProperty("HP"))));
	    enemy.setHp(enemy.getMax_hp());
	    
	    enemy.setMax_energy(Long.parseLong(prop.getProperty("FireDelay")));
	    enemy.setEnergy(enemy.getMax_energy());
	    
	    enemy.setR(Integer.parseInt(prop.getProperty("Size")));
	    enemy.setDx(Integer.parseInt(prop.getProperty("SpeedX")));
	    enemy.setDy(Integer.parseInt(prop.getProperty("SpeedY")));
	    enemy.setDamage(Long.parseLong(prop.getProperty("Damage")));
	    
	    if(prop.containsKey("Guide"))
	    {
	    	((BigEnemy) enemy).setGuide(RXUtils.parseBoolean(prop.getProperty("Guide")));
	    }
	    if(prop.containsKey("Missiles"))
	    {
	    	((BigEnemy) enemy).setMissiles(Integer.parseInt(prop.getProperty("Missiles")));
	    }
	    if(prop.containsKey("BeamDelay"))
	    {
	    	((Boss) enemy).setBeam_std(Integer.parseInt(prop.getProperty("BeamDelay")));
	    	((Boss) enemy).setBeam_energy(0);
	    }
	    if(prop.containsKey("AttackRatio"))
	    {
	    	((Boss) enemy).setRatio(Double.parseDouble(prop.getProperty("AttackRatio")));
	    }
	    if(prop.containsKey("Unique"))
	    {
	    	((Boss) enemy).setUnique(RXUtils.parseBoolean(prop.getProperty("Unique")));
	    }
	    
	    return enemy;
	}
}
