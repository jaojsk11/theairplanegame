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
public interface IStrategy {
    
    public void controller(Airplane air, KeyEvent kevent);
    public void controllerReleased(Airplane air, KeyEvent kevent);
}
