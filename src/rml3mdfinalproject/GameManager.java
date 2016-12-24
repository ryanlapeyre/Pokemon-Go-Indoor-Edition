/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rml3mdfinalproject;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 *
 * @author Ryan
 */


public class GameManager implements Serializable {
    
    private Integer milesRan;
    private Integer milesRequired;

    private ArrayList<String> evolveList;
   
    private String[] pictures= {
    "pokemonEgg.png" , "charmanderPic.png" , "charmeleonPic.png" , "charizardPic.png" 
    , "pokemonEgg.png" , "pichuPic.png" , "pikachuPic.png" , "raichuPic.png"  
    
    };
    
    public GameManager() 
    {
        this.milesRan = 0;
        this.milesRequired = 10;
        evolveList = new ArrayList<>();
        this.addEvolveToList("0");
    }
    
    public void setPictureString(String newPicture) // method isn't used, getPictureString acts more like an update; 
    {
        this.pictures[this.getNumberOfEvolves()  - 1] = newPicture;
      
    }
    
    public String getPictureString()
    {
         return this.pictures[this.getNumberOfEvolves()  -  1];
        
    }
    
    
    
    public Integer getMiles() {     
        return this.milesRan;
    }
    
    public void setMiles(Integer distance) {
        this.milesRan = distance;
    }
    
    public Integer getNumberOfEvolves() {
        Integer numberOfTimesEvolved = 0;
        
        for(String currentEvolve: this.evolveList)
        {
            numberOfTimesEvolved++;
        }
        
        return numberOfTimesEvolved;
    }
    
    public ArrayList<String> getEvolveList() {
        return this.evolveList;
    }
    
    public void setEvolveList(ArrayList evolveList) {
        this.evolveList = evolveList;
    }
       
    public Integer getMilesRequired() {
        return this.milesRequired;
    }
    
    public void setMilesRequired(Integer distance)
    {
        this.milesRequired = distance;  
    }
    
    public void addEvolveToList(String evolve) {
        this.evolveList.add(evolve);
    }

    public boolean canEvolvePokemon(Label displayMiles, Integer currentMilesRan) {
        if (currentMilesRan >= Integer.parseInt(displayMiles.getText())) {
            return true;
        } 
            return false;
    }
    
    
    
    public void evolvePokemon(Label milesRanLabel, Button evolveButton , Label milesRequiredLabel) {
        
        Evolve evolve = new Evolve();
        
          
            if(evolveList.size() % 4 == 1)
            {
                this.setMiles(0);
                evolve.setMessage("Congrats! Your egg hatched!");
                evolve.showEvolve(evolve.getMessage());
                addEvolveToList("1");
                this.setMilesRequired(20);
                printCurrentEvolutions(evolveList);
            }
            else if (evolveList.size() % 4 == 2)
                {
                this.setMiles(0);
                evolve.setEvolveMessage("A first evolution! You're on your way!");
                evolve.showEvolve(evolve.getEvolveMessage());
                addEvolveToList("2");
                this.setMilesRequired(30);
                printCurrentEvolutions(evolveList);
              
                }
                else if (evolveList.size() % 4 == 3)
                {
                   this.setMiles(0);
                evolve.setEvolveMessage("A fully evolved Pokemon! Wow, you sure are an outdoor enthusiast!");
                evolve.showEvolve(evolve.getEvolveMessage());
                this.setMilesRequired(40);
                addEvolveToList("3");
                printCurrentEvolutions(evolveList);
                this.checkFinish();
   
                }
                else if( evolveList.size() % 4 == 0 )
            {
                this.setMiles(0);
                evolve.setEvolveMessage("Wow! You found an egg!");
                evolve.showEvolve(evolve.getEvolveMessage());
                addEvolveToList("0");
                this.setMilesRequired(10);
                printCurrentEvolutions(evolveList);
            }
            
        
        milesRanLabel.setText(this.getMiles().toString());
        milesRequiredLabel.setText(this.getMilesRequired().toString());
        
    }
    
    
     public void printCurrentEvolutions(ArrayList<String> list) {
        if (list.size() > 0) {
            System.out.println("You currently have these evolutions: ");
            
            for (String upgradeNum : list) {   
                System.out.print(upgradeNum + "   ");
            }
        } else {
            System.out.println("You have no evolutions.");
        }
    } 
    
    
    
    public void checkFinish() {
        if (evolveList.size() ==  8) {
            this.setMilesRequired(0);
            Alert finish = new Alert(Alert.AlertType.INFORMATION);
            finish.setTitle("Winner");
            finish.setHeaderText("Congratulations!");
            finish.setContentText("You've beaten Pokemon Go Outside! While there are no more evolutions, you may continue to \"run!\"");
            finish.showAndWait();
        }
    

}

    
}
    
    
    
    
    
    
    
