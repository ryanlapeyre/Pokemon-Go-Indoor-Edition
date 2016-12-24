/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rml3mdfinalproject;

/**
 *
 * @author Ryan
 */
public class Trophy {
    
    private String trophyMessage;
    
    public Trophy() 
    {
        System.out.println("\nNew Pokemon earned!");
    }
    
    public String getMessage() {
        return trophyMessage;
    }
    
    public void setMessage(String trophyMessage) {
        this.trophyMessage = trophyMessage;
    }
    
    
    
}
