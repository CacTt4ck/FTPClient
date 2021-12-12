package com.cactt4ck.ftpc.ui.rootFrame;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PPanel extends JPanel {

    private FTPClient client;
    private boolean isConnected;
    private final PFrame owner;
    private JTree ftpTree;

    public PPanel(PFrame owner) {
        this.owner = owner;
        this.checkConnection();
    }

    private void init() {
        List<DefaultMutableTreeNode> directories = new ArrayList<DefaultMutableTreeNode>();
        DefaultMutableTreeNode root = null;
        try {
            root = new DefaultMutableTreeNode(client.printWorkingDirectory());
            for (FTPFile file : client.listFiles())
                if (file.getName().length() > 2)
                    directories.add(new DefaultMutableTreeNode(file.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (DefaultMutableTreeNode directory : directories)
            root.add(directory);

        this.ftpTree = new JTree(root);
        this.add(this.ftpTree, BorderLayout.WEST);
    }

    private void checkConnection() {
        Thread thread = new Thread(() -> {
            while (true) {
                if (isConnected) {
                    try {
                        this.tryFetch();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    this.init();
                    return;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void tryFetch() throws IOException {
        client.enterLocalPassiveMode();
        FTPFile[] files = client.listFiles();
        System.out.println("--------------Files--------------");
        for (FTPFile file : files)
            if (file.getName().length() > 2)
                System.out.println(file.getName());
        System.out.println("------------End Files------------");
        /*client.logout();
        client.disconnect();*/
    }

    public void setClient(FTPClient client) {
        this.client = client;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
