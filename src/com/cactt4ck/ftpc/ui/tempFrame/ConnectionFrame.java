package com.cactt4ck.ftpc.ui.tempFrame;

import com.cactt4ck.ftpc.ui.rootFrame.PFrame;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ConnectionFrame extends JDialog implements WindowListener {

    private FTPClient client;
    private PFrame owner;

    public ConnectionFrame(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.owner = (PFrame) owner;
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setContentPane(new ConnectionPanel(this));
        this.pack();
        this.setLocationRelativeTo(owner);
        this.addWindowListener(this);
        this.setVisible(true);
    }

    public void setClient(FTPClient client) {
        this.client = client;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        owner.setClient(client);
        System.out.println(String.valueOf(client).split("@")[1]);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
