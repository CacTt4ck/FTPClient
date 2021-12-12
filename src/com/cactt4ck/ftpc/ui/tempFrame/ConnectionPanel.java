package com.cactt4ck.ftpc.ui.tempFrame;

import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static javax.swing.SwingConstants.CENTER;

public class ConnectionPanel extends JPanel {

    private String hostname, username, password;
    private int port;
    private JLabel title, hostnameLabel, portLabel, usernameLabel, passwordLabel;
    private JTextField hostnameField, usernameField;
    private JSpinner portField;
    private JPasswordField passwordField;
    private final ConnectionFrame owner;
    private JPanel gridLayoutPanel;
    private JButton connectButton;
    private boolean isConnected;

    public ConnectionPanel(ConnectionFrame owner) {
        super(new BorderLayout());
        this.owner = owner;
        this.init();
    }

    private void init() {
        //Init labels
        this.title = new JLabel("Enter your credentials");
        this.hostnameLabel = new JLabel("Hostname");
        this.portLabel = new JLabel("Port");
        this.usernameLabel = new JLabel("Username");
        this.passwordLabel = new JLabel("Password");

        //Init hostname and username fields
        this.hostnameField = new JTextField();
        this.usernameField = new JTextField();

        //Init port chooser
        this.portField = new JSpinner(new SpinnerNumberModel(21, 1, 65535, 1));

        //Init password field
        this.passwordField = new JPasswordField();

        this.gridLayoutPanel = new JPanel(new GridLayout(4, 2));

        this.connectButton = new JButton("Connect");

        this.configureComponents();
    }

    private void configureComponents() {
        this.title.setForeground(Color.BLACK);
        this.title.setFont(new Font("Calibri", Font.PLAIN, 30));
        this.title.setHorizontalAlignment(CENTER);

        this.hostnameLabel.setForeground(Color.BLACK);
        this.hostnameLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        this.portLabel.setForeground(Color.BLACK);
        this.portLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        this.usernameLabel.setForeground(Color.BLACK);
        this.usernameLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        this.passwordLabel.setForeground(Color.BLACK);
        this.passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        this.portField.addMouseWheelListener(e -> {
            if (e.getWheelRotation() < 0)
                this.portField.setValue((int) this.portField.getValue() + 1);
            else {
                if ((int) this.portField.getValue() > 0)
                    this.portField.setValue((int) this.portField.getValue() - 1);
            }
        });

        this.hostnameField.addActionListener(this.buttonClickListener());
        this.usernameField.addActionListener(this.buttonClickListener());
        this.passwordField.addActionListener(this.buttonClickListener());

        /*this.connectButton.addActionListener(e -> {
            this.hostname = this.hostnameField.getText();
            this.port = (int) this.portField.getValue();
            this.username = this.usernameField.getText();
            this.password = String.valueOf(passwordField.getPassword());
            this.connect();
        });*/
        this.connectButton.addActionListener(this.buttonClickListener());

        this.componentPlacing();
    }

    private ActionListener buttonClickListener() {
        return e -> {
            this.hostname = this.hostnameField.getText();
            this.port = (int) this.portField.getValue();
            this.username = this.usernameField.getText();
            this.password = String.valueOf(passwordField.getPassword());
            this.connect();
        };
    }

    private void componentPlacing() {
        this.add(this.title, BorderLayout.NORTH);

        this.gridLayoutPanel.add(this.hostnameLabel);
        this.gridLayoutPanel.add(this.portLabel);
        this.gridLayoutPanel.add(this.hostnameField);
        this.gridLayoutPanel.add(this.portField);

        this.gridLayoutPanel.add(this.usernameLabel);
        this.gridLayoutPanel.add(this.passwordLabel);
        this.gridLayoutPanel.add(this.usernameField);
        this.gridLayoutPanel.add(this.passwordField);

        this.add(gridLayoutPanel, BorderLayout.CENTER);

        this.add(this.connectButton, BorderLayout.SOUTH);
        this.fillFields();
    }

    private void fillFields() {
        this.hostnameField.setText("51.254.169.65");
        this.usernameField.setText("8918-cactt4ck");
        this.passwordField.setText("pioupiou13");
    }


    private void connect() {
        FTPClient client = new FTPClient();
        try {
            client.connect(this.hostname, this.port);
            this.isConnected = client.login(this.username, this.password);
        } catch (IOException ignored) {}
        if (!isConnected)
            JOptionPane.showMessageDialog(this, "Credential error", "Connection refused!", JOptionPane.ERROR_MESSAGE);
        else {
            System.out.println(String.valueOf(client).split("@")[1]);
            this.owner.setClient(client);
            this.owner.dispose();
        }
    }

    /*51.254.169.65
    21
    8918-cactt4ck
    pioupiou13*/
}
