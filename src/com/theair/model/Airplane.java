package com.theair.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.theair.model.proxy.ProxyImage;
import com.theair.view.Window;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
/**
 *
 * @author Anoundy
 */
public class Airplane extends GameObj {
    private ProxyImage proxyImage;
    private Tube[] tube;
    public Airplane(int x, int y){
        super(x, y);
        if(proxyImage == null) {
            proxyImage = new ProxyImage("/assets/air.png");
        }
        this.image = proxyImage.loadImage().getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.x -= width;
        this.y -= height;
        tube = new Tube[1];
        tube[0] = new Tube(800, Window.HEIGHT - 60);
        this.dy = 3;
    }
    @Override
    public void tick() {
        if(dy < 5) {
            dy += 1;
        }
        this.y += dy;
        tube[0].tick();
        checkWindowBorder();
    }
    public void fly() {
        if(dy > 0) {
            dy = 0;
        }
        dy -= 16;
    }
    
    private void checkWindowBorder() {
        if(this.x > Window.WIDTH) {
            this.x = Window.WIDTH;
        }
        if(this.x < 0) {
            this.x = 0;
        }
        if(this.y > Window.HEIGHT - 50) {
            this.y = Window.HEIGHT - 50;
        }
        if(this.y < 0) {
            this.y = 0;
        }
    }

    @Override
    public void render(Graphics2D g, ImageObserver obs) {
        g.drawImage(image, x, y, obs);
        tube[0].render(g, obs);
    }
    
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
