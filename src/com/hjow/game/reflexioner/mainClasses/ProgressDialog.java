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
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

import com.hjow.game.reflexioner.setting.Setting;

public class ProgressDialog extends JDialog implements ActionListener, Openable, Runnable
{
    private static final long serialVersionUID = 2438713500715589367L;
    private static ProgressDialog progress = null;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel controlPanel;
    private JLabel title;
    private JProgressBar progressBar;
    private JButton close;
    //private Thread thread;
    private volatile boolean threadSwitch = true;
    private int threadKey = 0;
    private JProgressBar progressBar2;
    private JPanel centerPanel;
    private long progresses = 0;
    public static ProgressDialog getInstance(Setting sets)
    {
        if(progress == null)
        {
            progress = new ProgressDialog();
            progress.init(sets.clone());
        }        
        return progress;
    }
    public static ProgressDialog getInstance(JFrame upper, Setting sets)
    {
        if(progress == null)
        {
            progress = new ProgressDialog(upper);
            progress.init(sets.clone());
        }        
        return progress;
    }
    public static ProgressDialog getInstance(JDialog upper, Setting sets)
    {
        if(progress == null)
        {
            progress = new ProgressDialog(upper);
            progress.init(sets.clone());
        }        
        return progress;
    }
    private ProgressDialog()
    {
        super();
    }
    private ProgressDialog(JFrame upper)
    {
        super(upper, false);
    }
    private ProgressDialog(JDialog upper)
    {
        super(upper, false);
    }
    private void init(Setting set)
    {
        this.setUndecorated(true);
        this.setSize(200, 80);
        this.setLocation((int)(set.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(set.getScreenSize().getHeight()/2 - this.getHeight()/2));
        mainPanel = new JPanel();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EtchedBorder());
        titlePanel = new JPanel();
        controlPanel = new JPanel();
        centerPanel = new JPanel();
        mainPanel.add(titlePanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.SOUTH);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(controlPanel, BorderLayout.CENTER);        
        title = new JLabel(set.trans("Reflexioner"));
        title.setFont(new Font(null, Font.BOLD, 25));
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(title);
        progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString(String.valueOf(0));
        progressBar2 = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);    
        //centerPanel.add(progressBar2, BorderLayout.SOUTH);
        close = new JButton("X");
        close.addActionListener(this);
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(progressBar, BorderLayout.CENTER);
        controlPanel.add(close, BorderLayout.EAST);
        titlePanel.setBackground(Color.DARK_GRAY);
        title.setForeground(Color.GRAY);
        progresses = 0;
        //thread = new Thread(this);
    }
    public void setValue(int value)
    {        
        progressBar.setValue(value);
        progressBar.setString(String.valueOf(value));
    }
    public void setValue(double value)
    {
        progressBar.setValue((int)(value * 100));
        progressBar.setString(String.valueOf((int)(value * 100)));
    }
    public long getProgresses()
    {
        return progresses;
    }
    public void setProgresses(long progresses)
    {
        this.progresses = progresses;
    }
    public static void progress(int value)
    {
        progress.setValue(value);
    }
    public static void progress(long value)
    {
        try
        {
            progress.setValue(value);
        
        }
        catch(Exception e)
        {
            
        }
    }
    public static void progress(double value)
    {
        try
        {
            progress.setValue(value);
        }
        catch(Exception e)
        {
            
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object ob = e.getSource();
        if(ob == close)
        {
            System.exit(0);
        }
    }

    @Override
    public void open()
    {
        this.setVisible(true);
        setValue(0);
        threadSwitch = true;
        //thread.start();
    }
    
    @Override
    public void exit()
    {
        threadSwitch = false;
        this.setVisible(false);        
    }
    public void closeThis()
    {
        threadSwitch = false;
        this.setVisible(false);
    }
    public static void close()
    {
        progress.closeThis();
    }
    public static void show(Setting set)
    {
        ProgressDialog.getInstance(set).open();
    }
    public static void show(Setting set, JFrame upper)
    {
        ProgressDialog.getInstance(upper, set).open();
    }
    public static void show(Setting set, JDialog upper)
    {
        ProgressDialog.getInstance(upper, set).open();
    }
    @Override
    public void run()
    {
        while(threadSwitch)
        {
            threadKey++;
            if(threadKey >= 1000) threadKey = 0;
            if(threadKey % 10 == 0)
            {
                progressBar2.setValue(threadKey / 10);
            }
            try
            {
                Thread.sleep(20);
            }
            catch(Exception e)
            {
                
            }
        }        
    }
}
