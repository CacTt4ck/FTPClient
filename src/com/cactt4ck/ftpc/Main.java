package com.cactt4ck.ftpc;

import com.cactt4ck.ftpc.ui.rootFrame.PFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(PFrame::new);

        /*FTPClient client = new FTPClient();
        try {
            client.connect("51.254.169.65", 21);
            System.out.println(client.login("8918-cactt4ck", "pioupiou13") ? "Connection established" : "Connection refused");
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();
            FTPFile[] files = client.listFiles();
            System.out.println("--------------Files--------------");
            for (FTPFile file : files)
                if (file.getName().length() > 2)
                    System.out.println(file.getName());
            System.out.println("------------End Files------------");
            client.logout();
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
