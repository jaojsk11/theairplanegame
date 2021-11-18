/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theair.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
/**
 *
 * @author Anoundy
 */
public class Window {
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    public Window(int width, int height, String title, Games game) {
        JFrame frame = new JFrame();
        frame.add(game);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(width, height));
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
    }
    public static void main(String[] args) {
        Games game = new Games();
        
   
        java.awt.EventQueue.invokeLater(() -> {
            
            Window window = new Window(WIDTH, HEIGHT, "The Airplane", game);
            
        });
    }
}
