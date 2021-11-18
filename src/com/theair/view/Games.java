/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theair.view;
import com.theair.controller.Controller;
import com.theair.model.Airplane;
import com.theair.model.Tube;
import com.theair.model.TubeColum;
import com.theair.model.proxy.ProxyImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author Anoundy
 */
public class Games extends JPanel implements ActionListener  {
     private boolean isRunning = false;
    private ProxyImage proxyImage;
    private Image background;
    private Airplane air;
    private TubeColum tubeColumn;
    private int score;
    private int highScore;

    public Games() {

        proxyImage = new ProxyImage("/assets/bg.png");
        background = proxyImage.loadImage().getImage();
        setFocusable(true);
        setDoubleBuffered(false);
        addKeyListener(new GameKeyAdapter());
        Timer timer = new Timer(15, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        if (isRunning) {
      
            air.tick();
            tubeColumn.tick();
            checkColision();
            score++;
         
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, 0, null);
        if (isRunning) {
            
            this.air.render(g2, this);
            this.tubeColumn.render(g2, this);
            g2.setColor(Color.black);
            g.setFont(new Font("Arial", 1, 20));
            g2.drawString("score: " + this.tubeColumn.getPoints(), 10, 20);
            ///////////////////////////////
        } else {
            g2.setColor(Color.black);
             g.setFont(new Font("Arial", 1, 20));
            g2.drawString("Enter to Start the Game", Window.WIDTH / 2 - 150, Window.HEIGHT / 2);
            g2.setColor(Color.black);
            g.setFont(new Font("Arial", 1, 15));
            
        }
        g2.setColor(Color.black);
        g.setFont(new Font("Arial", 1, 20));
        g2.drawString("High Score: " + highScore, Window.WIDTH - 160, 20);

        g.dispose();
    }

    private void restartGame() {
        if (!isRunning) {
            this.isRunning = true;
            this.air = new Airplane(Window.WIDTH / 2, Window.HEIGHT / 2);
            this.tubeColumn = new TubeColum();
        }
    }

    private void endGame() {
        this.isRunning = false;
        if (this.tubeColumn.getPoints() > highScore) {
            this.highScore = this.tubeColumn.getPoints();
        }
        this.tubeColumn.setPoints(0);

    }

    private void checkColision() {
        Rectangle rectAir = this.air.getBounds();
        Rectangle rectTube;

        for (int i = 0; i < this.tubeColumn.getTubes().size(); i++) {
            Tube tempTube = this.tubeColumn.getTubes().get(i);
            rectTube = tempTube.getBounds();
            if (rectAir.intersects(rectTube)) {
                endGame();
            }
        }
    }

    // Key
    private class GameKeyAdapter extends KeyAdapter {

        private final Controller controller;

        public GameKeyAdapter() {
            controller = new Controller();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                restartGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (isRunning) {
                controller.controllerReleased(air, e);
            }
        }
    }
}
