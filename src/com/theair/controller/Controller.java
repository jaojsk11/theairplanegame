/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theair.controller;
import com.theair.model.Airplane;
import java.awt.event.KeyEvent;
/**
 *
 * @author Anoundy
 */
public class Controller implements IStrategy{
    @Override
    public void controller(Airplane air, KeyEvent kevent) {
    }

    @Override
    public void controllerReleased(Airplane air, KeyEvent kevent) {
        if(kevent.getKeyCode() == KeyEvent.VK_W) {
            air.fly();
        }
    }
}
