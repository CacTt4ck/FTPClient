package com.cactt4ck.ftpc.ui.rootFrame;

import com.cactt4ck.ftpc.ui.tempFrame.ConnectionFrame;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;

public class PFrame extends JFrame {

    public static String TITLE = "FTP Client";
    private FTPClient client;
    private PPanel panel;

    public PFrame() {
        super(TITLE);
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.panel = new PPanel(this);
        this.setContentPane(this.panel);
        this.setVisible(true);
        new ConnectionFrame(this, "Enter credentials", true);
    }

    public void setClient(FTPClient client) {
        this.client = client;
        System.out.println(String.valueOf(client).split("@")[1]);
        this.panel.setClient(client);
        this.panel.setConnected(true);
    }

    public FTPClient getClient() {
        return this.client;
    }
}
