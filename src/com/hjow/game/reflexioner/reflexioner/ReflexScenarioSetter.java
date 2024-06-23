package com.hjow.game.reflexioner.reflexioner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.hjow.game.reflexioner.pack.InstalledPack;
import com.hjow.game.reflexioner.pack.Pack;
import com.hjow.game.reflexioner.setting.Lint;
import com.hjow.game.reflexioner.setting.Setting;

public class ReflexScenarioSetter
{
    public static List<ReflexScenario> standards()
    {
        return standards(false, null);
    }
    public static List<ReflexScenario> standards(boolean alsoFiles)
    {
        return standards(alsoFiles, Setting.load().getDefaultPath());
    }
    public static List<ReflexScenario> standards(String path)
    {
        return standards(true, path);
    }
    public static List<ReflexScenario> standards(boolean alsoFiles, String path)
    {
        Vector<ReflexScenario> scenarios = new Vector<ReflexScenario>();
        
        ReflexScenario newScenario;
        Vector<EnemyPattern> patterns = new Vector<EnemyPattern>();
        EnemyPattern[] patternArray;
        EnemyPattern newEnemyPattern;
        Enemy newEnemy;
        
        patterns = new Vector<EnemyPattern>();
        newScenario = new ReflexScenario();
        newScenario.setName("Easy to play");
        newScenario.setDescription("This is sample for scenario editor.");        
        newScenario.setKoreanDescription("시나리오 에디터를 위한 샘플입니다.");
        newScenario.setDifficulty(new Integer(1));
        newScenario.setDiffDelay(new Long(7000));
        newScenario.setEnemyLimit(new Integer(30));
        newScenario.setSpaceShip("flex");
        newScenario.setSpaceShipSelectable(new Boolean(true));
        
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(0));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.1);
        newEnemy = new Enemy();
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(100);
        newEnemy.setMax_hp(500);
        newEnemy.setDamage(10);
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        newScenario.setDefaultPattern(newEnemyPattern);    
        
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(8000));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.001);
        newEnemy = new HyperBoss();
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(100);
        newEnemy.setMax_hp(5000);
        newEnemy.setDamage(10);
        ((Boss)newEnemy).setBeam_energy(300);
        ((Boss)newEnemy).setRatio(0.99);
        ((Boss)newEnemy).setUnique(true);
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        patterns.add(newEnemyPattern);
        
        patternArray = new EnemyPattern[patterns.size()];        
        for(int i=0; i<patternArray.length; i++)
        {
            patternArray[i] = patterns.get(i);
        }
        newScenario.setPatterns(patternArray);          
        newScenario.setDeadLine(Lint.big(19999));
        
        newScenario.setSerials(new Long(3259827698746282L));
        scenarios.add(newScenario);
        
        
        patterns = new Vector<EnemyPattern>();
        newScenario = new ReflexScenario();
        newScenario.setName("Hyper Flex");
        newScenario.setDescription("There are only bosses.");        
        newScenario.setKoreanDescription("처음부터 끝까지 대형 적만 등장합니다.");
        newScenario.setDifficulty(new Integer(15000));
        newScenario.setDiffDelay(new Long(3000));
        newScenario.setEnemyLimit(new Integer(3));
        newScenario.setSpaceShip("flex");
        newScenario.setSpaceShipSelectable(new Boolean(true));
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(0));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.01);
        newEnemy = new Boss();
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(100);
        newEnemy.setMax_hp(5000);        
        ((Boss)newEnemy).setBeam_energy(300);
        ((Boss)newEnemy).setRatio(0.99);
        ((Boss)newEnemy).setUnique(false);
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        newScenario.setDefaultPattern(newEnemyPattern);    
        
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(0));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.001);
        newEnemy = new HyperBoss();
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(100);
        newEnemy.setMax_hp(5000);
        ((Boss)newEnemy).setBeam_energy(300);
        ((Boss)newEnemy).setRatio(0.99);
        ((Boss)newEnemy).setUnique(true);
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        patterns.add(newEnemyPattern);
        
        patternArray = new EnemyPattern[patterns.size()];
                
        for(int i=0; i<patternArray.length; i++)
        {
            patternArray[i] = patterns.get(i);
        }
        newScenario.setPatterns(patternArray);      
        newScenario.setSerials(new Long(3259827698736282L));
        scenarios.add(newScenario);
        
        patterns = new Vector<EnemyPattern>();
        newScenario = new ReflexScenario();
        newScenario.setName("See Carefully");
        newScenario.setDescription("Enemies cre sealed from shadow.");        
        newScenario.setKoreanDescription("적들은 그림자에 가려져 있습니다.");
        newScenario.setDifficulty(new Integer(5100));
        newScenario.setDiffDelay(new Long(3000));
        newScenario.setEnemyLimit(new Integer(3));
        newScenario.setSpaceShip("flex");
        newScenario.setSpaceShipSelectable(new Boolean(true));
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(0));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.1));
        newEnemyPattern.setRatio(0.01);
        newEnemy = new SealedEnemy(Reflexioner.getFile_path());
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(100);
        newEnemy.setMax_hp(200);        
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        newScenario.setDefaultPattern(newEnemyPattern);    
        
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(0));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.001);
        newEnemy = new SealedQuickBoss(Reflexioner.getFile_path());
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(100);
        newEnemy.setMax_hp(5000);
        ((Boss)newEnemy).setBeam_energy(300);
        ((Boss)newEnemy).setRatio(0.99);
        ((Boss)newEnemy).setUnique(false);
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        patterns.add(newEnemyPattern);
        
        patternArray = new EnemyPattern[patterns.size()];
                
        for(int i=0; i<patternArray.length; i++)
        {
            patternArray[i] = patterns.get(i);
        }
        newScenario.setPatterns(patternArray);    
        
        newScenario.setSerials(new Long(3259827698746212L));
        scenarios.add(newScenario);
        
        newScenario = new ReflexScenario();
        newScenario.setName("Clipper Experience");
        newScenario.setDescription("Try to control special ship called \'Clipper\'.");
        newScenario.setKoreanDescription("\'Clipper\' 라는 특별한 함선을 체험해 보세요.\n함선 체험 시나리오는 난이도가 매우 높습니다.");
        newScenario.setDifficulty(new Integer(50));
        newScenario.setDiffDelay(new Long(3000));
        newScenario.setEnemyLimit(new Integer(20));
        newScenario.setSpaceShip("clipper");
        newScenario.setSpaceShipSelectable(new Boolean(false));
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(0));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.1);
        newEnemy = new Enemy();
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(100);
        newEnemy.setMax_hp(800);
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        newScenario.setDefaultPattern(newEnemyPattern);
        
        newEnemyPattern = new BossEnemyPattern();
        patterns.add(newEnemyPattern);
        
        patternArray = new EnemyPattern[patterns.size()];
        for(int i=0; i<patternArray.length; i++)
        {
            patternArray[i] = patterns.get(i);
        }
        newScenario.setPatterns(patternArray);
        newScenario.setSerials(new Long(3252827698746282L));
        scenarios.add(newScenario);    
        
        patterns = new Vector<EnemyPattern>();
        newScenario = new ReflexScenario();
        newScenario.setName("Warship Experience");
        newScenario.setDescription("Try to control special ship called \'Warship\'.");
        newScenario.setKoreanDescription("\'Warship\' 라는 특별한 함선을 체험해 보세요.\n함선 체험 시나리오는 난이도가 매우 높습니다.");
        newScenario.setDifficulty(new Integer(25));
        newScenario.setDiffDelay(new Long(3000));
        newScenario.setEnemyLimit(new Integer(20));
        newScenario.setSpaceShip("warship");
        newScenario.setSpaceShipSelectable(false);
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(0));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.1);
        newEnemy = new Enemy();
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(100);
        newEnemy.setMax_hp(800);
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        newScenario.setDefaultPattern(newEnemyPattern);
        
        newEnemyPattern = new BossEnemyPattern();
        patterns.add(newEnemyPattern);        
        
        patternArray = new EnemyPattern[patterns.size()];
        for(int i=0; i<patternArray.length; i++)
        {
            patternArray[i] = patterns.get(i);
        }
        newScenario.setPatterns(patternArray);    
        newScenario.setSerials(new Long(3259817698746212L));
        scenarios.add(newScenario);
        
        patterns = new Vector<EnemyPattern>();
        newScenario = new ReflexScenario();
        newScenario.setName("Chaser Experience");
        newScenario.setDescription("Try to control special ship called \'Chaser\'.");
        newScenario.setKoreanDescription("\'Chaser\' 라는 특별한 함선을 체험해 보세요.\n함선 체험 시나리오는 난이도가 매우 높습니다.");
        newScenario.setDifficulty(new Integer(25));
        newScenario.setDiffDelay(new Long(5500));
        newScenario.setEnemyLimit(new Integer(20));
        newScenario.setSpaceShip("chaser");
        newScenario.setSpaceShipSelectable(false);
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(0));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.1);
        newEnemy = new Enemy();
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(100);
        newEnemy.setMax_hp(800);
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        newScenario.setDefaultPattern(newEnemyPattern);
        
        newEnemyPattern = new BossEnemyPattern();
        patterns.add(newEnemyPattern);        
        
        patternArray = new EnemyPattern[patterns.size()];
        for(int i=0; i<patternArray.length; i++)
        {
            patternArray[i] = patterns.get(i);
        }
        newScenario.setPatterns(patternArray);        
        newScenario.setSerials(new Long(3259827698646282L));
        scenarios.add(newScenario);
        
        patterns = new Vector<EnemyPattern>();
        newScenario = new ReflexScenario();
        newScenario.setName("Carrier Experience");
        newScenario.setDescription("Try to control special ship called \'Carrier\'.");
        newScenario.setKoreanDescription("\'Carrier\' 라는 특별한 함선을 체험해 보세요.\n함선 체험 시나리오는 난이도가 매우 높습니다.");
        newScenario.setDifficulty(new Integer(25));
        newScenario.setDiffDelay(new Long(5550));
        newScenario.setEnemyLimit(new Integer(20));
        newScenario.setSpaceShip("carrier");
        newScenario.setSpaceShipSelectable(false);
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(0));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.1);
        newEnemy = new Enemy();
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(100);
        newEnemy.setMax_hp(800);
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        newScenario.setDefaultPattern(newEnemyPattern);
        
        newEnemyPattern = new BossEnemyPattern();
        patterns.add(newEnemyPattern);
        patternArray = new EnemyPattern[patterns.size()];
        for(int i=0; i<patternArray.length; i++)
        {
            patternArray[i] = patterns.get(i);
        }
        newScenario.setPatterns(patternArray);        
        newScenario.setSerials(new Long(3159827698646212L));
        scenarios.add(newScenario);
        
        List<Pack> packs = InstalledPack.getPacks();
        for(Pack p : packs)
        {
            List<ReflexScenario> pscenarios = p.getScenarios();
            for(ReflexScenario psc : pscenarios)
            {
                if(! scenarios.contains(psc)) scenarios.add(psc);
            }
        }
        
        File scnPath = null;
        try
        {
        	Setting sets = Setting.load();
            File defPath = new File(sets.getDefaultPath());
            if(! defPath.exists()) defPath.mkdirs();
            scnPath = new File(defPath.getAbsolutePath() + File.separator + "scenarios");
            if(! scnPath.exists()) scnPath.mkdirs();
            
            File[] files = scnPath.listFiles(new FileFilter() 
            {	
				@Override
				public boolean accept(File pathname) {
					if(! pathname.exists()) return false;
					if(pathname.isDirectory()) return false;
					String name = pathname.getName().toLowerCase();
					return name.endsWith(".rscn");
				}
			});
            
            FileInputStream stream = null;
            InputStreamReader reader = null;
            BufferedReader buffered = null;
            String lines = "", accum = "";
            for(File f : files)
            {
            	lines = "";
            	accum = "";
            	try
            	{
            		stream = new FileInputStream(f);
                    reader = new InputStreamReader(stream, "UTF-8");
                    buffered = new BufferedReader(reader);
                    while(true)
                    {
                        lines = buffered.readLine();
                        if(lines == null) break;
                        accum = accum + lines + "\n";
                    }
                    buffered.close(); buffered = null;
                    reader.close();   reader   = null;
                    stream.close();   stream   = null;
                    ReflexScenario scen = new ReflexScenario(accum);
                    if(! scenarios.contains(scen)) scenarios.add(scen);
            	}
            	catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            	finally
            	{
            		try { if(buffered != null) buffered.close(); buffered = null; } catch(Exception exc) {}
                    try { if(reader   != null) reader.close();   reader   = null; } catch(Exception exc) {}
                    try { if(stream   != null) stream.close();   stream   = null; } catch(Exception exc) {}
            	}
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        if(alsoFiles)
        {
            try
            {
            	if(path == null && scnPath != null) path = scnPath.getAbsolutePath();
                File folder = new File(path);
                if(! folder.exists()) folder.mkdir();
                File target;
                FileOutputStream fout = null;
                OutputStreamWriter outWriter = null;
                BufferedWriter writer = null;
                String reports = Setting.load().trans("Following files are created.");
                for(ReflexScenario s : scenarios)
                {
                    try
                    {
                        target = new File(path + s.getName() + ".rscn");
                        fout = new FileOutputStream(target);
                        outWriter = new OutputStreamWriter(fout, "UTF-8");
                        writer = new BufferedWriter(outWriter);
                        writer.write(s.stringData());
                        reports = reports + target.getAbsolutePath() + "\n";
                    } 
                    catch (Exception e)
                    {
                        reports = reports + s.getName() + " (" + e.getMessage() + ")\n";
                        e.printStackTrace();
                    }    
                    finally
                    {
                    	try { writer.close();    } catch (Exception e) {}
                    	try { outWriter.close(); } catch (Exception e) {}
                        try { fout.close();      } catch (Exception e) {}
                    }
                }
                JOptionPane.showMessageDialog(null, reports);
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return scenarios;
    }
}
