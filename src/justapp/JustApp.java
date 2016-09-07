/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justapp;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


/**
 *
 * @author admin
 */
public class JustApp extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         launch(args);
               }

    @Override
    public void start(Stage stage) throws Exception {
        String path = JustApp.class.getResource("Dead_Combo.mp3").toString();
        Media m = new Media(path);
        MediaPlayer mp = new MediaPlayer(m);
        mp.play();
        
     

        System.out.print("Právě hraje ...");
    }
}
