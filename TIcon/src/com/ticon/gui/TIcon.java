package com.ticon.gui;

import com.ticon.util.AppConstants;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class TIcon implements ActionListener {

    private PopupMenu popupMenu = null;
    private MenuItem menuItem1 = null;
    private MenuItem menuItem2 = null;

    public TIcon() {
        init();
        if (SystemTray.isSupported()) {
            try {
                SystemTray systemTray = SystemTray.getSystemTray();
                
                TrayIcon trayIcon = new TrayIcon(new ImageIcon(getClass().getResource(AppConstants.FAVICON)).getImage(), "Tray icon", popupMenu);
                
                trayIcon.setImageAutoSize(true);
                
                systemTray.add(trayIcon);
            } catch (AWTException ex) {
                Logger.getLogger(TIcon.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuItem menuItem = (MenuItem) e.getSource();
        if (menuItem.getActionCommand().equals("open")) {
            new HomeFrame();
        } else if (menuItem.getActionCommand().equals("exit")) {
            System.exit(0);
        }
    }

    private void init() {
        
        menuItem1 = new MenuItem("Open");
        menuItem1.addActionListener(this);
        menuItem1.setActionCommand("open");

        menuItem2 = new MenuItem("Exit");
        menuItem2.addActionListener(this);
        menuItem2.setActionCommand("exit");

        popupMenu = new PopupMenu();
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
    }

    public static void main(String[] args) {
        new TIcon();
    }
}
