/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rml3mdfinalproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static rml3mdfinalproject.Rml3mdFinalProject.stage;

/**
 *
 * @author Ryan
 */
public class GameController implements Initializable {
    
    
    
    private GameManager gameManager;
    
    
    private Image startImage;
    
    @FXML
    private ImageView startImageView;
  
    @FXML
    Label miles;
    
    @FXML
    private Button evolveButton;
    
    
    @FXML
    private Label levelsRequired;
    
    
    @FXML
    private void exitGame(ActionEvent event) {
        System.exit(0);
    }
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        gameManager = new GameManager();
        startImage = new Image(this.getClass().getResourceAsStream("pokemonEgg.png"));
        startImageView.setImage(startImage);  
        miles.setText("" + gameManager.getMiles());
        levelsRequired.setText("" + gameManager.getMilesRequired());
        
        
    }
    
    
       @FXML
    private void returnToMenu(ActionEvent event) {
        try {
            Parent menuRoot = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));
            Scene menuScene = new Scene(menuRoot);
            stage.setScene(menuScene);
            stage.show();
        } catch(Exception ex) {
            System.out.println("Can't load menu scene.");
        }
    }
    
  
    @FXML
    private void pokemonClick() {
        int count = gameManager.getMiles();
        count++;
        gameManager.setMiles(count);
        miles.setText("" + gameManager.getMiles());
    }
    

    
    
    
    
    @FXML
    private void evolveClick(ActionEvent event) {
          
        
        
       
          if(gameManager.getNumberOfEvolves() != 8  )
          {
                 if (gameManager.canEvolvePokemon(levelsRequired, gameManager.getMiles()) == true)
                 {
                    gameManager.evolvePokemon(miles, evolveButton , levelsRequired);
                  
                       startImage = new Image(this.getClass().getResourceAsStream(gameManager.getPictureString()));
                startImageView.setImage(startImage); 
                    
                 } 
                }  
      
                else
                {
                System.out.println("Evolution does not exist!");
        }
    }
    
        private GameManager createCurrentManager() {
        GameManager savedGame = new GameManager();
        
        savedGame.setMiles(this.gameManager.getMiles());
        
        savedGame.setMilesRequired(this.gameManager.getMilesRequired());
        
        savedGame.setEvolveList(this.gameManager.getEvolveList());
        
        savedGame.setPictureString(this.gameManager.getPictureString());
        
        return savedGame;
    }
    
        @FXML
    private void handleSave(ActionEvent event) {
        GameManager gameSave;
        gameSave = createCurrentManager();
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try
            {
               FileOutputStream fileOut = new FileOutputStream(file.getPath());
               ObjectOutputStream out = new ObjectOutputStream(fileOut);
               out.writeObject(gameSave);
               out.close();
               fileOut.close();
            }catch(IOException ioex)
            {
                System.out.println("Exception occurred while saving to " + file.getPath());
            } 
        } 
    }
        
    
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    public GameManager getGameManager() {
        return this.gameManager;
    }
  
    public Label getMilesRequiredLabel() {
        return this.levelsRequired;
    }    
      
    public Label getMilesLabel() {
        return this.miles;
    }
        
    
    
    public ImageView getImageView()
    {
    return this.startImageView;

}
    
    public Image getImage()
    {
           return this.startImage;
        
    }
    
    
    
}