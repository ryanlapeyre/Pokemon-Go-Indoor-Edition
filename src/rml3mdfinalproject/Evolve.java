/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rml3mdfinalproject;

import javafx.scene.control.Alert;

/**
 *
 * @author Ryan
 */
public class Evolve extends Trophy implements Evolvable {
     
    private String evolveMessage;
    
    public Evolve() {
        System.out.println("New Pokemon earned!");
    }
    
    public String getEvolveMessage() {
        return evolveMessage;
    }
    
    public void setEvolveMessage(String message) {
        this.evolveMessage = message;
    }
    
    /**
     *
     * @param pokemonName
     */
    @Override
    public void showEvolve(String pokemonName) {
        Alert achievementAlert = new Alert(Alert.AlertType.INFORMATION);
        achievementAlert.setTitle("Pokemon");
        achievementAlert.setHeaderText("Pokemon Earned: " + pokemonName);
        achievementAlert.setContentText("You are closer to completing the Pokedex!!");
        achievementAlert.showAndWait();
    }
}   
