package com.hjow.game.reflexioner.reflexioner;

import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import com.hjow.game.reflexioner.mainClasses.XMLSerializableObject;

public class Weapon extends XMLSerializableObject
{
    private static final long serialVersionUID = -6352492028137047417L;
    public static final int MISSILE_NORMAL = 0;
    public static final int MISSILE_REFLEX = 1;
    public static final int MISSILE_REFLEX_P = 2;
    public static final int MISSILE_GUIDE = 3;
    public static final int MISSILE_BEAM = 4;
    public static final int MISSILE_SUPER = 5;
    public static final int MISSILE_HELPER = 6;
    public static final int MISSILE_RASER = 7;
    public static final int MISSILE_FORMULA = 8;
    private int missileType = 0;
    private int maxMissile = 0;
    private int minMissile = 0;
    private int interval = 0;
    private int missileHp = 0;
    private int needEnergy = 0;
    private int delay = Arena.getGfireDelay();
    private double damageRatio = 0.0;
    private double size = 0.0;
    private double speed = 0.0;
    private int helperCount = 1, helperType = 0;
    private double progress = 0.0, max_progress = 1000.0;
    private String formula = "";
    private double rangeUnit = 1000.0;
    private double minX = -Arena.maxWidth() * 16, maxX = Arena.maxWidth() * 16, minY = -Arena.maxHeight() * 16, maxY = Arena.maxHeight() * 16;
    private boolean rangeAbsolute = false;
    protected transient int fireMissiles;
    protected transient double center;
    protected transient int getTarget; 
    protected transient Missile newMissile;
    protected transient SpaceShip owner;
    
    public Weapon()
    {
        super();
    }
    public Weapon(String commands, SpaceShip owner)
    {        
        super();
        
        StringTokenizer lineToken, equalToken;
        String lineTarget, option, contents;
        double getTarget2;
        lineToken = new StringTokenizer(commands, "\n");
        this.owner = owner;
        while(lineToken.hasMoreTokens())
        {
            lineTarget = lineToken.nextToken();
            if((lineTarget != null) && (! lineTarget.trim().startsWith("#")) && (! lineTarget.trim().equals("")))
            {
                equalToken = new StringTokenizer(lineTarget, "=");
                option = equalToken.nextToken().trim();
                
                if(equalToken.hasMoreTokens())
                {
                    contents = equalToken.nextToken().trim();
                }
                else
                {
                    contents = null;
                }
                
                if(contents == null)
                {
                    
                }
                else
                {
                    if(option.equalsIgnoreCase("type"))
                    {
                        try
                        {
                            getTarget = Integer.parseInt(contents);
                            this.missileType = getTarget;
                        }
                        catch(NumberFormatException e)
                        {
                            if(contents.equalsIgnoreCase("normal"))
                            {
                                this.missileType = MISSILE_NORMAL;
                            }
                            else if(contents.equalsIgnoreCase("guide"))
                            {
                                this.missileType = MISSILE_GUIDE;
                            }
                            else if(contents.equalsIgnoreCase("reflex"))
                            {
                                this.missileType = MISSILE_REFLEX;
                            }
                            else if(contents.equalsIgnoreCase("reflex_perfect"))
                            {
                                this.missileType = MISSILE_REFLEX_P;
                            }
                            else if(contents.equalsIgnoreCase("beam"))
                            {
                                this.missileType = MISSILE_BEAM;
                            }
                            else if(contents.equalsIgnoreCase("super"))
                            {
                                this.missileType = MISSILE_SUPER;
                            }
                            else if(contents.equalsIgnoreCase("helper"))
                            {
                                this.missileType = MISSILE_HELPER;
                            }
                            else if(contents.equalsIgnoreCase("raser"))
                            {
                                this.missileType = MISSILE_RASER;
                            }
                            else if(contents.equalsIgnoreCase("formula"))
                            {
                                this.missileType = MISSILE_FORMULA;
                            }
                            else
                            {
                                this.missileType = MISSILE_NORMAL;
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("max"))
                    {
                        try
                        {
                            getTarget = Integer.parseInt(contents);
                            this.maxMissile = getTarget;
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if(option.equalsIgnoreCase("min"))
                    {
                        try
                        {
                            getTarget = Integer.parseInt(contents);
                            this.minMissile = getTarget;
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if(option.equalsIgnoreCase("interval"))
                    {
                        try
                        {
                            getTarget = Integer.parseInt(contents);
                            this.interval = getTarget;
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if(option.equalsIgnoreCase("hp"))
                    {
                        try
                        {
                            getTarget = Integer.parseInt(contents);
                            this.missileHp = getTarget;
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if(option.equalsIgnoreCase("needs"))
                    {
                        try
                        {
                            getTarget = Integer.parseInt(contents);
                            this.needEnergy = getTarget;
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if(option.equalsIgnoreCase("delay"))
                    {
                        try
                        {
                            getTarget = Integer.parseInt(contents);
                            this.delay = getTarget;
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if(option.equalsIgnoreCase("size"))
                    {
                        try
                        {
                            getTarget2 = Double.parseDouble(contents);
                            this.size = getTarget2;
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if(option.equalsIgnoreCase("speed"))
                    {
                        try
                        {
                            getTarget2 = Double.parseDouble(contents);
                            this.speed = getTarget2;
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if(option.equalsIgnoreCase("damage"))
                    {
                        try
                        {
                            getTarget2 = Double.parseDouble(contents);
                            this.damageRatio = getTarget2;
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if(option.equalsIgnoreCase("helper_type"))
                    {
                        if(this.missileType == MISSILE_HELPER)
                        {                            
                            try
                            {
                                getTarget = Integer.parseInt(contents);
                                this.helperType = getTarget;
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("helper_count"))
                    {
                        if(this.missileType == MISSILE_HELPER)
                        {                            
                            try
                            {
                                getTarget = Integer.parseInt(contents);
                                this.helperCount = getTarget;
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("formula_progress"))
                    {
                        if(this.missileType == MISSILE_RASER || this.missileType == MISSILE_FORMULA)
                        {
                            try
                            {
                                getTarget2 = Double.parseDouble(contents);
                                this.progress = getTarget2;
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("formula_max_progress"))
                    {
                        if(this.missileType == MISSILE_RASER || this.missileType == MISSILE_FORMULA)
                        {
                            try
                            {
                                getTarget2 = Double.parseDouble(contents);
                                this.max_progress = getTarget2;
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("formula_range_unit"))
                    {
                        if(this.missileType == MISSILE_RASER || this.missileType == MISSILE_FORMULA)
                        {
                            try
                            {
                                getTarget2 = Double.parseDouble(contents);
                                this.rangeUnit = getTarget2;
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("formula_range_absolute"))
                    {
                        if(this.missileType == MISSILE_RASER || this.missileType == MISSILE_FORMULA)
                        {
                            try
                            {
                                this.rangeAbsolute = Boolean.parseBoolean(contents);
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("formula_min_x"))
                    {
                        if(this.missileType == MISSILE_RASER || this.missileType == MISSILE_FORMULA)
                        {
                            try
                            {
                                getTarget2 = Double.parseDouble(contents);
                                this.minX = getTarget2;
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("formula_min_y"))
                    {
                        if(this.missileType == MISSILE_RASER || this.missileType == MISSILE_FORMULA)
                        {
                            try
                            {
                                getTarget2 = Double.parseDouble(contents);
                                this.minY = getTarget2;
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("formula_max_x"))
                    {
                        if(this.missileType == MISSILE_RASER || this.missileType == MISSILE_FORMULA)
                        {
                            try
                            {
                                getTarget2 = Double.parseDouble(contents);
                                this.maxX = getTarget2;
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("formula_max_y"))
                    {
                        if(this.missileType == MISSILE_RASER || this.missileType == MISSILE_FORMULA)
                        {
                            try
                            {
                                getTarget2 = Double.parseDouble(contents);
                                this.maxY = getTarget2;
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(option.equalsIgnoreCase("formula"))
                    {
                        if(this.missileType == MISSILE_RASER || this.missileType == MISSILE_FORMULA)
                        {
                            try
                            {
                                this.formula = this.formula + contents + "\n";
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }                
            }
        }
    }
    public List<Missile> fire(long damage, int missiles, int owner_x, int owner_y, int owner_r, int owner, int player_x, int player_y, HaveEnergy owner_ref)
    {        
        Vector<Missile> result_missiles = new Vector<Missile>();
        
        if(owner_ref.getEnergy() < getNeed_e())
        {
            return result_missiles;
        }
        else
        {
            owner_ref.setEnergy(owner_ref.getEnergy() - getNeed_e());
        }
        
        fireMissiles = missiles;
        if(fireMissiles < minMissile)
        {
            fireMissiles = minMissile;
        }
        else if(fireMissiles > maxMissile)
        {
            fireMissiles = maxMissile;
        }
        center = fireMissiles / 2.0;
        for(int i=0; i<fireMissiles; i++)
        {
            newMissile = null;
            switch (missileType)
            {
                case MISSILE_NORMAL:
                    newMissile = new Missile(Reflexioner.getFile_path(), Missile.SPACESHIP);
                    getTarget = 5;
                    break;
                case MISSILE_REFLEX:
                    newMissile = new ReflexMissile(Reflexioner.getFile_path(), Missile.SPACESHIP);
                    ((ReflexMissile) newMissile).setSide_reflex(false);
                    getTarget = 2;
                    break;
                case MISSILE_REFLEX_P:
                    newMissile = new ReflexMissile(Reflexioner.getFile_path(), Missile.SPACESHIP);
                    ((ReflexMissile) newMissile).setSide_reflex(true);
                    getTarget = 3;
                    break;
                case MISSILE_GUIDE:
                    newMissile = new GuidedMissile(this.owner.getEnemies(), this.owner, Missile.SPACESHIP, Reflexioner.getFile_path());
                    getTarget = 1;
                    break;
                case MISSILE_BEAM:
                    newMissile = new Beam(Missile.SPACESHIP, Reflexioner.getFile_path());
                    getTarget = 10;
                    break;
                case MISSILE_SUPER:
                    newMissile = new SuperMissile(Reflexioner.getFile_path(), Missile.SPACESHIP);
                    getTarget = 5;
                    break;
                case MISSILE_HELPER:
                    newMissile = new HelperSpread(Reflexioner.getFile_path(), Missile.SPACESHIP);                    
                    getTarget = 10;
                    break;
                case MISSILE_RASER:
                    newMissile = new Raser(Reflexioner.getFile_path(), Missile.SPACESHIP, "");                    
                    getTarget = 10;
                    break;
                case MISSILE_FORMULA:
                    newMissile = new FormulaMissile(Reflexioner.getFile_path(), Missile.SPACESHIP, "");                    
                    getTarget = 10;
                    break;
                default:
                    newMissile = new Missile(Reflexioner.getFile_path(), Missile.SPACESHIP);
                    getTarget = 5;
                    break;
            }            
            
            if(newMissile != null)
            {
                newMissile.setDamage(Math.round(this.damageRatio * damage));
                newMissile.setHp(this.missileHp);
                newMissile.setW((int) Math.round(this.size * getTarget));
                newMissile.setOwner(owner);
                newMissile.setLaunched(true);                
                if(owner == Missile.SPACESHIP)
                {
                    newMissile.setDy((int) - (Arena.getGspeed() * speed));
                    newMissile.setX(owner_x + (int) Math.round((center - i - 0.5) * 30));
                    newMissile.setY(owner_y - owner_r);
                    newMissile.setColor(Reflexioner.color_spaceShip_missile);
                    if(newMissile instanceof ReflexMissile)
                    {
                        ((ReflexMissile) newMissile).setDx(0);
                    }                    
                }
                else
                {
                    newMissile.setDy((int) (Arena.getGspeed() * speed));
                    newMissile.setX(owner_x + (int) Math.round((center - i - 0.5) * 30));
                    newMissile.setY(owner_y + owner_r);
                    newMissile.setColor(Reflexioner.color_enemy_missile);
                }
                if(newMissile instanceof Beam)
                {
                    if(owner == Missile.SPACESHIP)
                    {
                        newMissile.setY(0);
                        newMissile.setH(newMissile.getH() - (Arena.maxHeight() - owner_y) - 10);
                    }
                    else
                    {
                        newMissile.setH(newMissile.getH() - (Arena.maxHeight() - owner_y) - 10);
                        newMissile.setH((int)Math.round(newMissile.getH() * 2.0));
                        newMissile.setY(owner_y + newMissile.getH());
                        if(newMissile.getY() > Arena.maxHeight()) newMissile.setY(Arena.maxHeight() - 1);
                    }
                }
                if(newMissile instanceof HelperSpread)
                {
                    ((HelperSpread) newMissile).setHelper_type(helperType);
                    ((HelperSpread) newMissile).setHelper_count(helperCount);
                }
                if(newMissile instanceof FormulaUsedMissile)
                {
                    ((FormulaUsedMissile) newMissile).setFormula(formula);
                    ((FormulaUsedMissile) newMissile).setMax_progress(max_progress);
                    ((FormulaUsedMissile) newMissile).setProgress(progress);
                    ((FormulaUsedMissile) newMissile).setRange_absolute(rangeAbsolute);
                    ((FormulaUsedMissile) newMissile).setRange_unit(rangeUnit);
                }
                result_missiles.add(newMissile);
            }
        }
        return result_missiles;
    }
    public int getMissile_type()
    {
        return missileType;
    }
    public void setMissile_type(int missile_type)
    {
        this.missileType = missile_type;
    }
    public int getMax_missile()
    {
        return maxMissile;
    }
    public void setMax_missile(int max_missile)
    {
        this.maxMissile = max_missile;
    }
    public int getMin_missile()
    {
        return minMissile;
    }
    public void setMin_missile(int min_missile)
    {
        this.minMissile = min_missile;
    }
    public int getMissile_hp()
    {
        return missileHp;
    }
    public void setMissile_hp(int missile_hp)
    {
        this.missileHp = missile_hp;
    }
    public double getSize()
    {
        return size;
    }
    public void setSize(double size)
    {
        this.size = size;
    }
    public double getSpeed()
    {
        return speed;
    }
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
    public int getInterval()
    {
        return interval;
    }
    public void setInterval(int interval)
    {
        this.interval = interval;
    }
    public double getDamage_ratio()
    {
        return damageRatio;
    }
    public void setDamage_ratio(double damage_ratio)
    {
        this.damageRatio = damage_ratio;
    }
    public int getNeed_e()
    {
        return needEnergy;
    }
    public void setNeed_e(int need_e)
    {
        this.needEnergy = need_e;
    }
    public void setSpaceShip(SpaceShip owner)
    {
        this.owner = owner;
    }
    public int getDelay()
    {
        return delay;
    }
    public void setDelay(int delay)
    {
        this.delay = delay;
    }
    public int getHelper_count()
    {
        return helperCount;
    }
    public void setHelper_count(int helper_count)
    {
        this.helperCount = helper_count;
    }
    public int getHelper_type()
    {
        return helperType;
    }
    public void setHelper_type(int helper_type)
    {
        this.helperType = helper_type;
    }
    public double getProgress()
    {
        return progress;
    }
    public void setProgress(double progress)
    {
        this.progress = progress;
    }
    public double getMax_progress()
    {
        return max_progress;
    }
    public void setMax_progress(double max_progress)
    {
        this.max_progress = max_progress;
    }
    public String getFormula()
    {
        return formula;
    }
    public void setFormula(String formula)
    {
        this.formula = formula;
    }
    public double getRange_unit()
    {
        return rangeUnit;
    }
    public void setRange_unit(double range_unit)
    {
        this.rangeUnit = range_unit;
    }
    public double getMin_x()
    {
        return minX;
    }
    public void setMin_x(double min_x)
    {
        this.minX = min_x;
    }
    public double getMax_x()
    {
        return maxX;
    }
    public void setMax_x(double max_x)
    {
        this.maxX = max_x;
    }
    public double getMin_y()
    {
        return minY;
    }
    public void setMin_y(double min_y)
    {
        this.minY = min_y;
    }
    public double getMax_y()
    {
        return maxY;
    }
    public void setMax_y(double max_y)
    {
        this.maxY = max_y;
    }
    public boolean isRange_absolute()
    {
        return rangeAbsolute;
    }
    public void setRange_absolute(boolean range_absolute)
    {
        this.rangeAbsolute = range_absolute;
    }
    public int getMissileType() {
        return missileType;
    }
    public void setMissileType(int missileType) {
        this.missileType = missileType;
    }
    public int getMaxMissile() {
        return maxMissile;
    }
    public void setMaxMissile(int maxMissile) {
        this.maxMissile = maxMissile;
    }
    public int getMinMissile() {
        return minMissile;
    }
    public void setMinMissile(int minMissile) {
        this.minMissile = minMissile;
    }
    public int getMissileHp() {
        return missileHp;
    }
    public void setMissileHp(int missileHp) {
        this.missileHp = missileHp;
    }
    public int getNeedEnergy() {
        return needEnergy;
    }
    public void setNeedEnergy(int needEnergy) {
        this.needEnergy = needEnergy;
    }
    public double getDamageRatio() {
        return damageRatio;
    }
    public void setDamageRatio(double damageRatio) {
        this.damageRatio = damageRatio;
    }
    public int getHelperCount() {
        return helperCount;
    }
    public void setHelperCount(int helperCount) {
        this.helperCount = helperCount;
    }
    public int getHelperType() {
        return helperType;
    }
    public void setHelperType(int helperType) {
        this.helperType = helperType;
    }
    public double getRangeUnit() {
        return rangeUnit;
    }
    public void setRangeUnit(double rangeUnit) {
        this.rangeUnit = rangeUnit;
    }
    public double getMinX() {
        return minX;
    }
    public void setMinX(double minX) {
        this.minX = minX;
    }
    public double getMaxX() {
        return maxX;
    }
    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }
    public double getMinY() {
        return minY;
    }
    public void setMinY(double minY) {
        this.minY = minY;
    }
    public double getMaxY() {
        return maxY;
    }
    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }
    public boolean isRangeAbsolute() {
        return rangeAbsolute;
    }
    public void setRangeAbsolute(boolean rangeAbsolute) {
        this.rangeAbsolute = rangeAbsolute;
    }    
}
