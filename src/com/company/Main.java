package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        MyWindow frame = new MyWindow();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

    }
}
