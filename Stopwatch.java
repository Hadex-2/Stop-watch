package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JButton takeTimeButton = new JButton("TAKE TIME");
    JButton wipeTimeListButton = new JButton("WIPE LIST");
    JLabel timeLabel = new JLabel();
    DefaultListModel<String> dlm = new DefaultListModel<>();
    JList<String> timesTakenList = new JList<>(dlm);
    int elapsedTime=0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_String = String.format("%02d", seconds);  //This is setting a format, if there is only ope digit it
    String minutes_String = String.format("%02d", minutes);  // Will display 0 in front of it.
    String hours_String = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            seconds_String = String.format("%02d", seconds);
            minutes_String = String.format("%02d", minutes);
            hours_String = String.format("%02d", hours);
            timeLabel.setText(hours_String+":"+minutes_String+":"+seconds_String);
        }
    });

    Stopwatch(){//Constructor for this class

        timeLabel.setText(hours_String+":"+minutes_String+":"+seconds_String);
        timeLabel.setBounds(10,10,200,100);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(10,120,100,50);
        startButton.setFont(new Font("Verdana",Font.PLAIN,20));
        startButton.setFocusable(true);
        startButton.addActionListener(this);

        resetButton.setBounds(110,120,100,50);
        resetButton.setFont(new Font("Verdana",Font.PLAIN,20));
        resetButton.setFocusable(true);
        resetButton.addActionListener(this);

        takeTimeButton.setBounds(10,170,200,50);
        takeTimeButton.setFont(new Font("Verdana",Font.PLAIN,20));
        takeTimeButton.setFocusable(true);
        takeTimeButton.addActionListener(this);

        wipeTimeListButton.setBounds(10, 220, 200,50);
        wipeTimeListButton.setFont(new Font("Verdana",Font.PLAIN,20));
        wipeTimeListButton.setFocusable(true);
        wipeTimeListButton.addActionListener(this);

        timesTakenList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane timeTakenListScrollPane = new JScrollPane(timesTakenList);
        timeTakenListScrollPane.setBorder(BorderFactory.createBevelBorder(1));
        timeTakenListScrollPane.setBounds(10,280,200,130);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.add(takeTimeButton);
        frame.add(wipeTimeListButton);
        frame.add(timeTakenListScrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Window close operation
        frame.setSize(240,460);// Window size
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton){
            if(!started){
                started = true;
                startButton.setText("STOP");
                start();
            }else {
                started = false;
                startButton.setText("START");
                stop();
            }
        }
        if(e.getSource()==resetButton){
            started = false;
            startButton.setText("START");
            reset();
        }
        if(e.getSource()==takeTimeButton){
            takeTime();
        }
        if(e.getSource()==wipeTimeListButton){
            wipeTimeList();
        }
    }
    void start() {
        timer.start();
    }
    void stop() {
        timer.stop();
    }
    void reset() {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_String = String.format("%02d", seconds);
        minutes_String = String.format("%02d", minutes);
        hours_String = String.format("%02d", hours);
        timeLabel.setText(hours_String+":"+minutes_String+":"+seconds_String);
    }
    void takeTime(){
        String currentTime = hours_String+":"+minutes_String+":"+seconds_String;
        dlm.addElement(currentTime);
    }
    void wipeTimeList(){
        dlm.removeAllElements();
    }
}
