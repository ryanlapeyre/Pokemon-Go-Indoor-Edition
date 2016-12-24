/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rml3mdfinalproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import static rml3mdfinalproject.Rml3mdFinalProject.stage;

/**
 *
 * @author Ryan
 */
public class MenuController implements Initializable {
      

    private Image startImage;
    
    @FXML
    private ImageView startImageView;
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        startImage = new Image(this.getClass().getResourceAsStream("pokemonGo.png"));
        startImageView.setImage(startImage);  
        
        
    }    
    
    // do load game 
    
    @FXML
    private void aboutMenu(ActionEvent event) {
        Alert about = new Alert(AlertType.INFORMATION);
        about.setTitle("About");
        about.setHeaderText("Pokemon Go : Indoor Version");
        about.setContentText("Ryan Lapeyre's final project for CS 3330.\n\nClick on the starting egg to run. When you have ran enough miles, choose to evolve and get a Pokemon!");
        about.showAndWait();
    }
    
      
    @FXML
    private void exitGame(ActionEvent event) {
        System.exit(0);
    }

    
    @FXML
    private void startNewGame(ActionEvent event) {
        try {
            Parent gameRoot = FXMLLoader.load(getClass().getResource("FXMLGame.fxml"));
            Scene gameScene = new Scene(gameRoot);

            stage.setScene(gameScene);
            stage.show();
        } catch(Exception ex) {
            System.out.println("Can't load the game scene!");
        }
    }
    
        private void loadManagerFromFile(GameController controller, GameManager loadedGame) 
        {
        controller.setGameManager(loadedGame);
        
        controller.getGameManager().setEvolveList(loadedGame.getEvolveList());
   
                
        controller.getMilesRequiredLabel().setText("" + controller.getGameManager().getMilesRequired().toString());
        
        controller.getMilesLabel().setText("" + controller.getGameManager().getMiles().toString());
        
        Image image = new Image(this.getClass().getResourceAsStream(controller.getGameManager().getPictureString()));        
                
        controller.getImageView().setImage(image);
        
        
        }       

            @FXML
    private void handleLoadGameButtonAction(ActionEvent event) {   
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGame.fxml"));
            Parent root = (Parent)loader.load();
            GameController controller = (GameController)loader.getController();
    
            GameManager loadedGame;
            
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try
                {
                   FileInputStream fileIn = new FileInputStream(file.getPath());
                   ObjectInputStream in = new ObjectInputStream(fileIn);
                   loadedGame = (GameManager) in.readObject();
                   in.close();
                   fileIn.close();
                   
                 
                   loadManagerFromFile(controller, loadedGame);
                   
            
                   Scene scene = new Scene(root);
                   stage.setScene(scene);
                   stage.show();
                   
                }catch(IOException ioex)
                {
                   System.out.println("Error loading game manager object from file from "+ file.getPath());
                }catch(ClassNotFoundException cnfex)
                {
                   System.out.println("Class not found exception occurred while opening " + file.getPath());
                }
            }
        } catch (IOException ioex) {
            System.out.println("IOException when trying to load game.");
        }
    }
    
        
        
        
        
        
}
