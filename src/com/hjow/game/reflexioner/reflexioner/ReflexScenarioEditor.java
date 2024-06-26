package com.hjow.game.reflexioner.reflexioner;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import com.hjow.game.reflexioner.mainClasses.Openable;
import com.hjow.game.reflexioner.mainClasses.RunManager;
import com.hjow.game.reflexioner.setting.Setting;

public class ReflexScenarioEditor extends JDialog implements Openable, ActionListener, MouseListener, MouseMotionListener
{
    private static final long serialVersionUID = 1940512597716306791L;
    private Setting sets;
    private JPanel mainPanel;
    private JPanel upPanel;
    private JPanel downPanel;
    private JPanel centerPanel;
    private JTextArea contents;
    private JScrollPane contentScroll;
    private JMenuBar menuBar;
    private JMenu menu_file;
    private JMenuItem menu_file_exit;
    private JMenuItem menu_file_save;
    private JMenuItem menu_file_load;
    private JMenuItem menu_file_sample;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JFileChooser fileChooser;
    private FileFilter fileFilter;
    private JMenuItem menu_file_auth;
    private Reflexioner sp;
    private JButton bt_play;
    private JButton bt_add;
    private JButton bt_close;
    private int mouse_x = 0;
    private int mouse_y = 0;
    
    public ReflexScenarioEditor()
    {
        super();
    }
    public ReflexScenarioEditor(Setting sets)
    {
        super();
        this.sets = sets;
        if(this.sets == null) this.sets = Setting.load(RunManager.getIndexClass());
        init();
    }
    public ReflexScenarioEditor(JDialog upper)
    {
        this(upper, null, null);
    }
    public ReflexScenarioEditor(JDialog upper, Reflexioner reflex)
    {
        this(upper, null, reflex);
    }
    public ReflexScenarioEditor(JDialog upper, Setting sets)
    {
        this(upper, sets, null);
    }
    public ReflexScenarioEditor(JDialog upper, Setting sets, Reflexioner reflex)
    {
        super(upper);
        sp = reflex;
        this.sets = sets;
        if(this.sets == null) this.sets = Setting.load(RunManager.getIndexClass());
        init();
    }
    private void init()
    {
        setUndecorated(true);
        setSize(620, 410);
        setLocation((int)(sets.getScreenSize().getWidth()/2 - getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - getHeight()/2));
        getContentPane().setLayout(new BorderLayout());
        mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBackground(sets.getColor("ColorSelectedBack"));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EtchedBorder());
        upPanel = new JPanel();
        downPanel = new JPanel();
        centerPanel = new JPanel();
        upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        mainPanel.add(upPanel, BorderLayout.NORTH);
        mainPanel.add(downPanel, BorderLayout.SOUTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        titlePanel = new JPanel();
        titlePanel.addMouseListener(this);
        titlePanel.addMouseMotionListener(this);
        titlePanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        titlePanel.setBorder(new EtchedBorder());
        titlePanel.setLayout(new FlowLayout());
        titleLabel = new JLabel(sets.trans("Scenario Editor"));
        titleLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            titleLabel.setFont(Reflexioner.usingFont);
        }
        titlePanel.add(titleLabel);
        
        centerPanel.setLayout(new BorderLayout());
        contents = new JTextArea();
        contents.setLineWrap(true);
        contentScroll = new JScrollPane(contents);
        centerPanel.add(contentScroll, BorderLayout.CENTER);
        centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        contents.setBackground(sets.getColor("ColorSelectedInsideBack"));
        contents.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            contents.setFont(Reflexioner.usingFont);
        }
        
        upPanel.setLayout(new BorderLayout());
        upPanel.add(titlePanel, BorderLayout.CENTER);
        
        downPanel.setLayout(new BorderLayout());
        
        bt_play = new JButton(sets.trans("Play"));
        bt_play.addActionListener(this);
        bt_play.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            bt_play.setFont(Reflexioner.usingFont);
        }
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_play.setBackground(sets.getColor("ColorSelectedBack"));
        }
        downPanel.add(bt_play, BorderLayout.CENTER);
        if(sp == null) bt_play.setVisible(false);
        
                
        bt_add = new JButton(sets.trans("Add"));
        bt_add.addActionListener(this);
        bt_add.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            bt_add.setFont(Reflexioner.usingFont);
        }
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_add.setBackground(sets.getColor("ColorSelectedBack"));
        }
        downPanel.add(bt_add, BorderLayout.WEST);
        if(sp == null) bt_add.setVisible(false);
        
        bt_close = new JButton(sets.trans("Close"));
        bt_close.addActionListener(this);
        bt_close.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            bt_close.setFont(Reflexioner.usingFont);
        }
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_close.setBackground(sets.getColor("ColorSelectedBack"));
        }
        downPanel.add(bt_close, BorderLayout.EAST);
        
        menuBar = new JMenuBar();
        upPanel.add(menuBar, BorderLayout.SOUTH);
        menuBar.setBackground(sets.getColor("ColorSelectedInsideBack"));
        menuBar.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            menuBar.setFont(Reflexioner.usingFont);
        }
        
        menu_file = new JMenu(sets.trans("File"));
        menu_file.setBackground(sets.getColor("ColorSelectedInsideBack"));
        menu_file.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            menu_file.setFont(Reflexioner.usingFont);
        }
        menuBar.add(menu_file);
        
        menu_file_auth = new JMenuItem(sets.trans("Authority"));
        menu_file_auth.addActionListener(this);
        menu_file_auth.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file_auth.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            menu_file_auth.setFont(Reflexioner.usingFont);
        }
        menu_file.add(menu_file_auth);
        
        menu_file_save = new JMenuItem(sets.trans("Save"));
        menu_file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.CTRL_MASK));    
        menu_file_save.addActionListener(this);
        menu_file_save.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file_save.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            menu_file_save.setFont(Reflexioner.usingFont);
        }
        menu_file.add(menu_file_save);
        
        menu_file_load = new JMenuItem(sets.trans("Load"));
        menu_file_load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.CTRL_MASK));    
        menu_file_load.addActionListener(this);
        menu_file_load.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file_load.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            menu_file_load.setFont(Reflexioner.usingFont);
        }
        menu_file.add(menu_file_load);
        
        menu_file_sample = new JMenuItem(sets.trans("Load Sample")); 
        menu_file_sample.addActionListener(this);
        menu_file_sample.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file_sample.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            menu_file_sample.setFont(Reflexioner.usingFont);
        }
        menu_file.add(menu_file_sample);
        
        menu_file_exit = new JMenuItem(sets.trans("Exit"));
        menu_file_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        menu_file_exit.addActionListener(this);
        menu_file_exit.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file_exit.setForeground(sets.getColor("ColorSelectedFore"));
        if(Reflexioner.usingFont != null)
        {
            menu_file_exit.setFont(Reflexioner.usingFont);
        }
        menu_file.add(menu_file_exit);
    }
    private void prepareFileChooser()
    {
        if(fileChooser == null)
            fileChooser = new JFileChooser(new File(sets.getDefaultPath()));
        if(Reflexioner.usingFont != null)
        {
            Reflexioner.setFontRecursively(fileChooser, Reflexioner.usingFont);
        }
        if(fileFilter == null)
        {
            fileFilter = new FileFilter()
            {                
                @Override
                public boolean accept(File file1)
                {
                    if(file1 != null)
                    {
                        if(file1.isDirectory()) return false;
                        if(file1.getName().toLowerCase().endsWith(".rscn")) return true;
                    }
                    return false;
                }
                @Override
                public String getDescription()
                {                    
                    return "Reflexioner - Scenario (.rscn)";
                }
            };
        }
        
        fileChooser.setFileFilter(fileFilter);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object ob = e.getSource();
        if(ob == menu_file_exit)
        {
            exit();
        }
        else if(ob == menu_file_load)
        {
            load();
        }
        else if(ob == menu_file_save)
        {
            save();
        }
        else if(ob == menu_file_sample)
        {
            loadSamples();
        }
        else if(ob == bt_play)
        {
            try
            {
                sp.playScenario(new ReflexScenario(contents.getText(), false));
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, sets.trans("Error") + " : " + ex.getMessage());
            }
            
        }
        else if(ob == bt_add)
        {
            try
            {
                sp.inputScenario(new ReflexScenario(contents.getText(), false));
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, sets.trans("Error") + " : " + ex.getMessage());
            }
        }
        else if(ob == bt_close)
        {
            exit();
        }
        else if(ob == menu_file_auth)
        {
            try
            {
                ReflexScenario bf = new ReflexScenario(contents.getText());
                bf.createAuthorize();
                contents.setText(bf.stringData());
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(this, sets.trans("Error") + " : " + e1.getMessage());
            }
        }
    }
    private void saveScenario(File file)
    {
        FileOutputStream stream = null;
        OutputStreamWriter writer = null;
        BufferedWriter buffered = null;
        try
        {
            ReflexScenario sc = new ReflexScenario(contents.getText());
            stream = new FileOutputStream(file);
            writer = new OutputStreamWriter(stream, "UTF-8");
            buffered = new BufferedWriter(writer);
            buffered.write(sc.stringData());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, sets.trans("Error") + " : " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            try
            {
                buffered.close();
            } 
            catch (IOException e)
            {
                
            }
            try
            {
                writer.close();
            } 
            catch (IOException e)
            {
                
            }
            try
            {
                stream.close();
            } 
            catch (IOException e)
            {
                
            }
        }
    }
    private void loadScenario(File file)
    {
        FileInputStream stream = null;
        InputStreamReader reader = null;
        BufferedReader buffered = null;
        String lines = "", accum = "";
        try
        {
            stream = new FileInputStream(file);
            reader = new InputStreamReader(stream, "UTF-8");
            buffered = new BufferedReader(reader);
            while(true)
            {
                lines = buffered.readLine();
                if(lines == null) break;
                accum = accum + lines + "\n";
            }
            setScenario(new ReflexScenario(accum));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, sets.trans("Error") + " : " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            try
            {
                buffered.close();
            } 
            catch (IOException e)
            {
                
            }
            try
            {
                reader.close();
            } 
            catch (IOException e)
            {
                
            }
            try
            {
                stream.close();
            } 
            catch (IOException e)
            {
                
            }
        }
    }
    public void save()
    {
        prepareFileChooser();
        
        File selectedFile = null;
        if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();
            if(selectedFile.getAbsolutePath().endsWith(".rscn") || selectedFile.getAbsolutePath().endsWith(".RSCN") || selectedFile.getAbsolutePath().endsWith(".Rscn"))
            {
                saveScenario(selectedFile);
            }
            else
            {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".rscn");
                saveScenario(selectedFile);
            }
        }        
    }
    public void load()
    {
        prepareFileChooser();
        
        File selectedFile = null;
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();
            if(selectedFile.getAbsolutePath().endsWith(".rscn") || selectedFile.getAbsolutePath().endsWith(".RSCN") || selectedFile.getAbsolutePath().endsWith(".Rscn"))
            {
                loadScenario(selectedFile);
            }
            else
            {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".rscn");
                if(selectedFile.exists())
                {
                    loadScenario(selectedFile);
                }
                else
                {
                    selectedFile = new File(selectedFile.getAbsolutePath() + ".rscn");
                    if(selectedFile.exists())
                    {
                        loadScenario(selectedFile);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, sets.trans("Error") + " : " + sets.trans("None"));
                    }
                }
            }
        }        
    }
    public void loadSamples()
    {
        List<ReflexScenario> samples = ReflexScenarioSetter.standards();
        for(ReflexScenario sm : samples)
        {
            if(sm.getSerials() == 3259827698746282L)
            {
                setScenario(sm);
                break;
            }
        }
    }
    @Override
    public void open()
    {
        this.setVisible(true);
        
    }
    @Override
    public void exit()
    {
        this.setVisible(false);
        
    }
    public void setScenario(ReflexScenario scen)
    {
        contents.setText(scen.stringData(false));
    }
    public ReflexScenario toScenario() throws Exception
    {
        return new ReflexScenario(contents.getText(), false);
    }
    @Override
    public void mouseDragged(MouseEvent e)
    {
        Object ob = e.getSource();
        if(ob == titlePanel)
        {
            setLocation(e.getXOnScreen() - mouse_x, e.getYOnScreen() - mouse_y);
        }        
    }
    @Override
    public void mouseMoved(MouseEvent arg0)
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
        Object ob = e.getSource();
        if(ob == titlePanel)
        {
            mouse_x = e.getX();
            mouse_y = e.getY();
        }        
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }
}
