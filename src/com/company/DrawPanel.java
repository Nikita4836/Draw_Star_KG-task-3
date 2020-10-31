package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private ScreenConverter sc = new ScreenConverter(-2, 2, 4, 4,
            800, 600);



    public DrawPanel (){
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
    }

    private DrawStar ds = new DrawStar();

    @Override
    public void paint(Graphics g) {

        Graphics2D gr = (Graphics2D) g;
        ds.paintComponent(gr);

        sc.setScreenHeight(getHeight());
        sc.setScreenWidth(getWidth());

    }

    private ScreenPoint lastPostion = null;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            lastPostion = new ScreenPoint(e.getX(), e.getY());
        }
        repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            lastPostion = null;
        }
        repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint currentPosition = new ScreenPoint(e.getX(), e.getY());
        if(lastPostion != null){
            ScreenPoint deltaScreen = new ScreenPoint(currentPosition.getX() - lastPostion.getX(),
                    currentPosition.getY() - lastPostion.getY());
            RealPoint deltaReal = sc.s2r(deltaScreen);
            RealPoint zeroReal = sc.s2r(new ScreenPoint(0, 0));
            RealPoint vector = new RealPoint(deltaReal.getX() - zeroReal.getX(), deltaReal.getY() - zeroReal.getY());
            sc.setCornerX(sc.getCornerX() - vector.getX());
            sc.setCornerY(sc.getCornerY() - vector.getY());
            lastPostion = currentPosition;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int clicks = e.getWheelRotation();
        double scale = 1;
        double coef = clicks < 0 ? 1.1 : 0.9;
        for (int i = 0; i < Math.abs(clicks); i++){
            scale *= coef;
        }

        sc.setRealWidth(sc.getRealWidth() * scale);
        sc.setRealHeight(sc.getRealHeight() * scale);

        repaint();
    }
}
