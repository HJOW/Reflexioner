package reflexioner;

import java.util.List;

public class StunGuidedMissile extends GuidedMissile	
{
	private static final long serialVersionUID = 5725302446449334604L;
	private int old_dx = 0, old_dy = 0;
	protected transient int calc_old = 0;
	public StunGuidedMissile()
	{
		
	}
	public StunGuidedMissile(List<Enemy> enemyList, SpaceShip spaceShip)
	{
		super();
		this.enemyList = enemyList;
		this.spaceShip = spaceShip;
		setY(-200);
		setW(5);
		setH(5);
		setHp(10000);
		setSpeed(32);
		setDamage(getDamage() / 2);
		setDy(- (Reflexioner.getSpeed()));
		setColor(Reflexioner.color_spaceShip_missile);
	}
	public StunGuidedMissile(List<Enemy> enemyList, SpaceShip spaceShip, String path)
	{
		this(enemyList, spaceShip);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public StunGuidedMissile(List<Enemy> enemyList, SpaceShip spaceShip, int owner, String path)
	{
		this(enemyList, spaceShip);
		setOwner(owner);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	public void update()
	{		
		if(isLaunched())
		{
			setY(getY() + getDy());
			setX(getX() + getDx());
		}
		setHp(getHp() - 1);
		maxValue = 0;
		setOld_dx(getDx());
		setOld_dy(getDy());
		if(enemyList.size() >= 1)
		{
			if(target != null)
			{
				exist = false;
				for(Enemy e : enemyList)
				{
					if(e.equals(target))
					{
						exist = true;
						break;
					}
				}
				if(! exist)
					target = null;
			}
			if(target == null)
			{
				if(getOwner() == SPACESHIP)
				{
					for(Enemy e : enemyList)
					{			
						distance = (int) Math.round(Math.sqrt(Math.pow(this.getX() - e.getX(), 2) + Math.pow(this.getY() - e.getY(), 2)));
						if(maxValue < distance)
						{
							maxValue = distance;
							target = e;
						}
					}
				}
				else
				{
					target = spaceShip;
				}
			}
			if(target != null)
			{
				distance = (int) Math.round(Math.sqrt(Math.pow(this.getX() - target.getX(), 2) + Math.pow(this.getY() - target.getY(), 2)));
				
				maxValue = target.getX() - this.getX();
				maxValue = maxValue * getSpeed();
				maxValue = (int) Math.ceil(maxValue / (distance + 0.01));
				maxValue = (int) Math.ceil(maxValue / 4.0);
				setDx(maxValue);
				if(getDx() >= 0)
				{
					calc_old = 1;
				}
				else
				{
					calc_old = -1;
				}
				setDx((int)Math.round(getOld_dx() + calc_old));
				
				maxValue = target.getY() - this.getY();
				maxValue = maxValue * getSpeed();
				maxValue = (int) Math.ceil(maxValue / (distance + 0.01));
				maxValue = (int) Math.ceil(maxValue / 4.0);				
				
				if(Math.abs(maxValue) <= 0) maxValue = (int) Math.ceil(getSpeed() / 16.0);
				setDy(maxValue);				
				
				
			}
			else
			{
				//setDx(0);
				setDy((int) Math.ceil(Reflexioner.getSpeed() / 16.0));
			}
		}
		else target = null;
		//if(getY() <= -10 || getY() >= Reflexioner.size_y + 10) setLaunched(false);
	}
	public String getMissileName()
	{
		return "stun_guide";
	}
	public int getOld_dx()
	{
		return old_dx;
	}
	public void setOld_dx(int old_dx)
	{
		this.old_dx = old_dx;
	}
	public int getOld_dy()
	{
		return old_dy;
	}
	public void setOld_dy(int old_dy)
	{
		this.old_dy = old_dy;
	}
	public Missile clone()
	{
		return clone(false);
	}
	public Missile clone(boolean imgnull)
	{		
		StunGuidedMissile newMissile = new StunGuidedMissile();
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
		newMissile.setSpeed(getSpeed());
		newMissile.setGuideRound(getGuideRound());
		newMissile.setDx(getDx());
		newMissile.setColor(getColor());
		newMissile.setOld_dx(getOld_dx());
		newMissile.setOld_dy(getOld_dy());
		return newMissile;
	}
}