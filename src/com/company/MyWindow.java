package com.company;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {
    private DrawPanel dp;

    public MyWindow() throws HeadlessException {
        dp = new DrawPanel();
        this.add(dp);
    }
}