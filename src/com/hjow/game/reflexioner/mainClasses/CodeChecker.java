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
package com.hjow.game.reflexioner.mainClasses;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.hjow.game.reflexioner.pack.SecuredDist;
import com.hjow.game.reflexioner.reflexioner.Reflexioner;
import com.hjow.game.reflexioner.setting.Setting;

public class CodeChecker implements ActionListener, WindowListener, MouseListener, MouseMotionListener, Openable
{
    boolean independence = false;
    boolean gui = false;
    boolean while_loop = true;
    Setting setting;
    
    private InputStreamReader inread;
    private BufferedReader bfreader;
    
    private JDialog checker_dialog;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JPanel upPanel;
    private JPanel downPanel;
    private JPanel titlePanel;
    private JLabel title;
    private int mousex;
    private int mousey;
    private JPanel inputPanel;
    private JLabel inputLabel;
    private JTextArea ta;
    private JPanel[] inputPns;
    private JTextField inputField;
    private JButton checkButton;
    private JButton closeButton;
    private JPanel contentPanel;
    
    public CodeChecker(boolean independence)
    {
        this.gui = false;
        this.independence = independence;        
    }
    public CodeChecker(boolean independence, Setting setting)
    {
        this.gui = true;
        this.independence = independence;
        this.setting = setting.clone();
        init_frame();
    }
    public CodeChecker(boolean independence, JDialog upper, Setting setting)
    {
        this.gui = true;
        this.independence = independence;
        this.setting = setting.clone();
        init_frame(upper);
    }
    public CodeChecker(boolean independence, Frame upper, Setting setting)
    {
        this.gui = true;
        this.independence = independence;
        this.setting = setting.clone();
        init_frame(upper);
    }
    public CodeChecker(boolean independence, Dialog upper, Setting setting)
    {
        this.gui = true;
        this.independence = independence;
        this.setting = setting.clone();
        init_frame(upper);
    }
    public Properties check_code(String code)
    {
        GZIPInputStream gzipper = null;
        try
        {
            byte[] codeBytes = RXUtils.hexBytes(code);
            ByteArrayInputStream coll = new ByteArrayInputStream(codeBytes);
            Properties resultProp = new Properties();
            
            ByteArrayOutputStream collx = new ByteArrayOutputStream();
            
            gzipper = new GZIPInputStream(coll);
            coll = null;
            
            byte[] buffer = new byte[1024];
            int reads;
            while(true)
            {
                reads = gzipper.read(buffer, 0, buffer.length);
                if(reads < 0) break;
                collx.write(buffer, 0, reads);
            }
            gzipper.close(); gzipper = null;
            
            coll = new ByteArrayInputStream(collx.toByteArray());
            collx = null;
            
            resultProp.loadFromXML(coll);
            coll = null;
            code = null;
            
            return resultProp;
        } 
        catch (NumberFormatException e)
        {
            return null;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            return null;
        }        
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(getWindow(), setting.trans("Error") + " - " + e.getMessage());
            return null;
        }
        finally
        {
            if(gzipper != null) { try { gzipper.close(); } catch(Exception ex) {} }
        }
    }
    public void open()
    {
        if(gui) checker_dialog.setVisible(true);
        else
        {
            inread = new InputStreamReader(System.in);
            bfreader = new BufferedReader(inread);
            while(while_loop)
            {
                int input = 0;                
                String input_code = "";
                System.out.println(setting.trans("Check the code"));
                System.out.println();
                System.out.println("-----------------------------------------");
                System.out.println(setting.trans("Menu"));
                System.out.println("1. " + setting.trans("Check"));
                System.out.println("2. " + setting.trans("Exit" ));
                System.out.println("-----------------------------------------");
                System.out.print(">");
                try
                {
                    input = Integer.parseInt(bfreader.readLine());
                    switch(input)
                    {
                        case 1:
                            System.out.print(setting.trans("Authority") + " " + setting.trans("Input") + " : ");
                            input_code = bfreader.readLine();
                            Properties result = check_code(input_code);
                            check_refresh(result);
                            System.out.println("-----------------------------------------");
                            break;
                        case 2:
                            exit();
                            break;                            
                    }
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                    continue;
                }
                catch(NullPointerException e)
                {
                    System.out.println(setting.trans("Retry, please."));
                    continue;
                }
                catch(NumberFormatException e)
                {
                    System.out.println(setting.trans("Retry, please."));
                    continue;
                }                
            }
        }
    }
    public void exit()
    {
        if(independence) System.exit(0);
        else
        {
            if(gui)
            {
                checker_dialog.setVisible(false);
            }
            else
            {
                while_loop = false;
                try
                {
                    inread.close();
                    bfreader.close();
                } 
                catch (Exception e)
                {
                    
                }
            }
        }
    }
    public void init_frame()
    {
        checker_dialog = new JDialog();
        init_other();
    }
    public void init_frame(JDialog upper)
    {
        if(upper != null) checker_dialog = new JDialog(upper, false);
        else  checker_dialog = new JDialog();
        init_other();
    }
    public void init_frame(Dialog upper)
    {
        if(upper != null) checker_dialog = new JDialog(upper, false);
        else  checker_dialog = new JDialog();
        init_other();
    }
    public void init_frame(Frame upper)
    {
        if(upper != null) checker_dialog = new JDialog(upper, false);
        else  checker_dialog = new JDialog();
        init_other();
    }
    public Window getWindow()
    {
        return checker_dialog;
    }
    public void init_other()
    {
        checker_dialog.setSize(500, 400);
        checker_dialog.setLocation((int) (setting.getScreenSize().getWidth()/2 - checker_dialog.getWidth()/2), (int) (setting.getScreenSize().getHeight()/2 - checker_dialog.getHeight()/2));
        checker_dialog.setUndecorated(setting.getBool("Undecorated"));
        if(! setting.getBool("Undecorated")) checker_dialog.addWindowListener(this);
        mainPanel = new JPanel();
        checker_dialog.getContentPane().setLayout(new BorderLayout());
        checker_dialog.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBorder(new EtchedBorder());
        
        centerPanel = new JPanel();
        upPanel = new JPanel();
        downPanel = new JPanel();
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(upPanel, BorderLayout.NORTH);
        mainPanel.add(downPanel, BorderLayout.SOUTH);
        
        mainPanel.setBackground(setting.getColor("ColorSelectedBack"));
        
        upPanel.setLayout(new BorderLayout());
        upPanel.setBackground(setting.getColor("ColorSelectedBack"));
        
        titlePanel = new JPanel();
        title = new JLabel(setting.trans("Check the code"));
        title.setFont(setting.getUsingFont());
        if(setting.getBool("Undecorated"))
        {
            upPanel.add(titlePanel, BorderLayout.NORTH);
            titlePanel.addMouseListener(this);
            titlePanel.addMouseMotionListener(this);
            titlePanel.setBorder(new EtchedBorder());
            titlePanel.setBackground(setting.getColor("ColorSelectedInsideBack"));
            title.setForeground(setting.getColor("ColorSelectedBack"));
            titlePanel.setLayout(new FlowLayout());
            titlePanel.add(title);
        }
        
        inputPanel = new JPanel();
        upPanel.add(inputPanel, BorderLayout.CENTER);
        inputPanel.setBackground(setting.getColor("ColorSelectedBack"));
        inputPns = new JPanel[2];
        inputPanel.setLayout(new GridLayout(inputPns.length, 1));
        for(int i=0; i<inputPns.length; i++)
        {
            inputPns[i] = new JPanel();
            inputPns[i].setBackground(setting.getColor("ColorSelectedBack"));
            inputPns[i].setLayout(new FlowLayout());
            inputPanel.add(inputPns[i]);
        }
        inputLabel = new JLabel(setting.trans("Input your point authorized code here."));
        if(Reflexioner.usingFont != null)
            inputLabel.setFont(Reflexioner.usingFont);
        inputLabel.setForeground(setting.getColor("ColorSelectedFore"));
        inputPns[0].add(inputLabel);
        inputField = new JTextField(35);
        if(Reflexioner.usingFont != null)
            inputField.setFont(Reflexioner.usingFont);
        inputField.setForeground(setting.getColor("ColorSelectedFore"));
        inputField.setBackground(setting.getColor("ColorSelectedInsideBack"));
        inputPns[1].add(inputField);
        checkButton = new JButton(setting.trans("Check"));
        if(Reflexioner.usingFont != null)
            checkButton.setFont(Reflexioner.usingFont);
        checkButton.addActionListener(this);
        checkButton.setForeground(setting.getColor("ColorSelectedFore"));
        inputPns[1].add(checkButton);
        
        downPanel.setLayout(new FlowLayout());
        downPanel.setBackground(setting.getColor("ColorSelectedBack"));
        closeButton = new JButton(setting.trans("Close"));
        if(Reflexioner.usingFont != null)
            closeButton.setFont(Reflexioner.usingFont);
        closeButton.addActionListener(this);
        closeButton.setForeground(setting.getColor("ColorSelectedFore"));
        downPanel.add(closeButton);
        
        centerPanel.setBackground(setting.getColor("ColorSelectedBack"));
        centerPanel.setLayout(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setBorder(new EtchedBorder());
        contentPanel.setBackground(setting.getColor("ColorSelectedBack"));
        centerPanel.add(contentPanel, BorderLayout.CENTER);
        
        ta = new JTextArea();
        ta.setEditable(false);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(new JScrollPane(ta), BorderLayout.CENTER);
        
        ta.setBackground(setting.getColor("ColorSelectedInsideBack"));
        ta.setForeground(setting.getColor("ColorSelectedFore"));
    }
    public void check_refresh(Properties result)
    {
        StringBuilder report = new StringBuilder("");
        if(result == null)
        {
            report = report.append("No results");
        }
        else
        {
            report = report.append("Results").append("\n\n");
            report = report.append("Name"           ).append("\t: ").append(result.getProperty("Name"           )).append("\n");
            report = report.append("Scenario"       ).append("\t: ").append(result.getProperty("Scenario"       )).append("\n");
            report = report.append("Difficulty"     ).append("\t: ").append(result.getProperty("Difficulty"     )).append("\n");
            report = report.append("Ship"           ).append("\t: ").append(result.getProperty("Ship"           )).append("\n");
            report = report.append("Point"          ).append("\t: ").append(result.getProperty("Point"          )).append("\n");
            report = report.append("PlayDate"       ).append("\t: ").append(result.getProperty("PlayDate"       )).append("\n");
            report = report.append("Version"        ).append("\t: ").append(result.getProperty("Version"        )).append("\n");
            report = report.append("Auto"           ).append("\t: ").append(result.getProperty("Auto"           )).append("\n");
            report = report.append("TodayEvent"     ).append("\t: ").append(result.getProperty("TodayEvent"     )).append("\n");
            
            String code1 = result.getProperty("Code1");
            result.remove("Code1");
            
            boolean authRes = false;
            
            try
            {
                Set<String> keys = result.stringPropertyNames();
                List<String> keyList = new ArrayList<String>();
                keyList.addAll(keys);
                keys = null;
                Collections.sort(keyList);
                
                StringBuilder code1Creator = new StringBuilder("");
                for(String k : keyList)
                {
                    code1Creator = code1Creator.append(k).append(":").append(result.getProperty(k)).append("|");
                }
                keyList = null;
                
                SecuredDist sp = new SecuredDist();
                
                String vm, vs1, vs2;
                vm  = result.getProperty("Version1");
                vs1 = result.getProperty("Version2");
                vs2 = result.getProperty("Version3");
                
                byte[] code1bin = (sp.getLeftPad(Integer.parseInt(vm), Integer.parseInt(vs1), Integer.parseInt(vs2)) + code1Creator.toString() + sp.getRightPad(Integer.parseInt(vm), Integer.parseInt(vs1), Integer.parseInt(vs2))).getBytes("UTF-8");
                code1bin = RXUtils.hash(code1bin);
                
                String code1re = RXUtils.hexString(code1bin);
                authRes = code1.equals(code1re);
            }
            catch(Exception ex)
            {
                authRes = false;
                report = report.append(setting.trans("Authority") + " " + setting.trans("Error") + " - (" + ex.getClass().getSimpleName() + ") " + ex.getMessage());
            }
            
            if(authRes) report = report.append(setting.trans("Authority") + " " + setting.trans("Complete")).append("\n");
            else        report = report.append(setting.trans("Authority") + " " + setting.trans("Fail")).append("\n");
            
            report = report.append("\n");
            report = report.append("Details").append("\n\n");
            report = report.append("ShipKey"        ).append("\t: ").append(result.getProperty("ShipKey"        )).append("\n");
            report = report.append("ShipCode"       ).append("\t: ").append(result.getProperty("ShipCode"       )).append("\n");
            report = report.append("DifficultyValue").append("\t: ").append(result.getProperty("DifficultyValue")).append("\n");
            report = report.append("PlayDateValue"  ).append("\t: ").append(result.getProperty("PlayDateValue"  )).append("\n");
            report = report.append("Version1"       ).append("\t: ").append(result.getProperty("Version1"       )).append("\n");
            report = report.append("Version2"       ).append("\t: ").append(result.getProperty("Version2"       )).append("\n");
            report = report.append("Version3"       ).append("\t: ").append(result.getProperty("Version3"       )).append("\n");
            report = report.append("Code1"          ).append("\t: ").append(code1).append("\n");
        }
        
        
        if(gui)
        {
            ta.setText(report.toString().trim());
            ta.setCaretPosition(0);
        }
        else
        {
            System.out.println(report.toString().trim());
        }
    }
    @Override
    public void mouseDragged(MouseEvent e)
    {
        checker_dialog.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);    
        
    }
    @Override
    public void mouseMoved(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseEntered(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent e)
    {
        mousex = e.getX();
        mousey = e.getY();
        
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowActivated(WindowEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowClosed(WindowEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowClosing(WindowEvent e)
    {
        exit();
        
    }
    @Override
    public void windowDeactivated(WindowEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowDeiconified(WindowEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowIconified(WindowEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void windowOpened(WindowEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object ob = e.getSource();
        if(ob == closeButton)
        {
            exit();
        }
        else if(ob == checkButton)
        {
            //System.out.println(inputField.getText());
            check_refresh(check_code(inputField.getText()));
        }
        
    }
    
}
