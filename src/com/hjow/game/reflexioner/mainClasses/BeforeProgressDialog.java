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
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeforeProgressDialog extends Frame implements ActionListener, Openable, Runnable
{
    private static final long serialVersionUID = 2438713500715589367L;
    private static BeforeProgressDialog progress = null;
    private Panel mainPanel;
    private Panel titlePanel;
    private Panel controlPanel;
    private Label title;
    private Label progressBar;
    private Button close;
    private Thread thread;
    private Panel centerPanel;
    private int value = 0;
    
    private volatile boolean threadSwitch = true;
    
    public static BeforeProgressDialog getInstance()
    {
        if(progress == null)
        {
            progress = new BeforeProgressDialog();
            progress.init();
        }        
        return progress;
    }
    private BeforeProgressDialog()
    {
        super();
    }
    private void init()
    {
        this.setUndecorated(true);
        this.setSize(200, 80);
        this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - this.getHeight()/2));
        mainPanel = new Panel();
        this.setLayout(new BorderLayout());
        this.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        titlePanel = new Panel();
        controlPanel = new Panel();
        centerPanel = new Panel();
        mainPanel.add(titlePanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.SOUTH);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(controlPanel, BorderLayout.CENTER);        
        title = new Label("Reflexioner");
        title.setFont(new Font(null, Font.BOLD, 25));
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(title);
        progressBar = new Label();
        close = new Button("X");
        close.addActionListener(this);
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(progressBar, BorderLayout.CENTER);
        controlPanel.add(close, BorderLayout.EAST);
        titlePanel.setBackground(Color.DARK_GRAY);
        title.setForeground(Color.GRAY);
        thread = new Thread(this);
    }
    protected void setValue(int value)
    {
        this.value = value;
        if(this.value < 0  ) this.value =   0;
        if(this.value > 100) this.value = 100;
        
        String str = "";
        for(int i=0; i<this.value; i++)
            str = str + "l";
        progressBar.setText(str);
    }
    protected void setValue(double value)
    {
        setValue((int) (value * 100.0));
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object ob = e.getSource();
        if(ob == close)
        {
            threadSwitch = false;
            System.exit(0);
        }
    }

    @Override
    public void open()
    {
        this.setVisible(true);
        threadSwitch = true;
        thread.start();
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
    public static void showNow()
    {
        BeforeProgressDialog.getInstance().open();
    }
    @Override
    public void run()
    {
        while(threadSwitch)
        {
            this.value++;
            setValue(value);
            
            try { Thread.sleep(50L); } catch(InterruptedException e) { break; } 
        }        
    }
}
