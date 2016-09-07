/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justapp;


import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;


 /*
  * @author admin
  */
public class MusicPlayer extends Application{
    
    private MediaPlayer mp;
    private Media media;
    private Stage stageP;
    private MediaView mv;
    
    //Jako string musí být jméno, resp. uri hudebního souboru
    public MusicPlayer() {
    //Media vrací informace o hudebním souboru   
   String s = MusicPlayer.class
         .getResource("Dead_Combo.mp3").toString();
       // String s = "Dead_Combo.mp3";
    media = new Media(s);
    //S kombinací s výše uvedenou třídou přehraje MediaPlayer medium
    mp = new MediaPlayer(media);
    mv = new MediaView(mp);

    }

 @Override
 public void start(Stage primaryStage) 
 {
 mp.setAutoPlay(false);
 mv = new MediaView(mp);
 
 BorderPane bp = new BorderPane();
 bp.setCenter(mv);
 bp.setBottom(toolBar());
 bp.setStyle("-fx-background-color: #ffffff");
 
 Scene sc = new Scene(bp, 600, 600);
 sc.setFill(Color.BLACK);
 
 stageP = primaryStage;
 stageP.setTitle("iPlayer");
 stageP.setScene(sc);
 stageP.show();
 
 }
 
 private HBox toolBar() 
 {
     HBox hb = new HBox();
     hb.setPadding(new Insets(20));
     hb.setAlignment(Pos.CENTER);
     hb.alignmentProperty().isBound();
     hb.setSpacing(5);
     hb.setStyle("-fx-background-color: #bbbbbb");
     
     Image play = new Image(getClass()
             .getResourceAsStream("play-button-1.png"));
     Image pause = new Image(getClass()
             .getResourceAsStream("pause-1.png"));
     Image forw = new Image(getClass()
             .getResourceAsStream("fast-forward.png"));
     Image back = new Image(getClass()
             .getResourceAsStream("rewind.png"));
     Image rev = new Image(getClass()
             .getResourceAsStream("back.png"));
     Image next = new Image(getClass()
             .getResourceAsStream("next.png"));
     Image rep = new Image(getClass()
             .getResourceAsStream("repeat.png"));
     Image med = new Image(getClass()
             .getResourceAsStream("compact-disc-1.png"));
     Image eql = new Image(getClass()
             .getResourceAsStream("controls-6.png"));
     Image res = new Image(getClass()
             .getResourceAsStream("television.png"));
     
     ImageView playVi = resize(play, 35, 35);
     ImageView pausVi = resize(pause, 35, 35);
     ImageView forVi  = resize(forw, 35, 35);
     ImageView bacVi  = resize(back, 35, 35);
     ImageView revVi  = resize(rev, 35, 35);
     ImageView nextVi = resize(next,35, 35);
     ImageView repVi  = resize(rep, 35, 35);
     ImageView medVi  = resize(med, 35, 35);
     ImageView resVi  = resize(res, 35, 35);
     ImageView eqlVi  = resize(eql, 35, 35);
 
     
     hb.getChildren()
             .addAll(button(medVi,"medVi")  ,button(revVi,"revVi")
                    ,button(bacVi,"bacVi")  ,button(playVi,"playVi")
                    ,button(pausVi,"pausVi"),button(forVi,"forVi")
                    ,button(nextVi,"nextVi"),button(repVi,"repVi")
                    ,button(eqlVi,"eqlVi")  ,button(resVi,"resVi")
                    );
     
    

  
     
     return hb;
 }
 
    private ImageView resize(Image i,double w, double h) 
    {
       ImageView iv = new ImageView(i);
       iv.setFitHeight(h);
       iv.setFitWidth(w);
       iv.setPreserveRatio(true);
       return iv;
    }
    
    private Button button(Node n, String name)
    {
    Button but = new Button();
   
    but.setStyle("-fx-background-color: #bbbbbb");
    but.setGraphic(n);
    but.setOnAction((ActionEvent e) -> { 
       switch (name) 
       {
           case "medVi":System.out.print("med");
                        this.pickA();
           break; 
           case "revVi":System.out.println("reverse");
                        mp.seek(mp.getStartTime());
                        mp.stop();
           break;
           case "bacVi":System.out.println("previous");
                        mp.seek(mp.getCurrentTime().divide(1.5));
           break;
           case "playVi":System.out.println("play");
                         mp.play();
           break;
           case "forVi":System.out.println("forward");
                        mp.seek(mp.getCurrentTime().multiply(1.5));
           break;
           case "nextVi":System.out.println("next");
                        mp.seek(mp.getTotalDuration());
                        mp.stop();
           break;
           case "repVi":System.out.println("repeat");
                        mp.seek(mp.getStartTime());
           break;
           case "eqlVi":System.out.println("equalizer");
           break;
           case "resVi":System.out.println("resolution");
                        fullScreen(stageP);
           break;
           case "pausVi":System.out.println("pause"); 
                         mp.pause();
           break;
       } 
    });
    but.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
    but.setStyle("-fx-border-color: #ddbbbb");
    });
    but.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
    but.setStyle("-fx-background-color: #bbbbbb");
    });
    return but;
    }

    private void fullScreen(Stage st) 
    {
    if (st.isFullScreen()) {
    st.setFullScreen(false);
    }
    st.setFullScreen(true);
    }
    
    private void pickA()
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters()
                .add(new FileChooser
                .ExtensionFilter("*.mp3","*.mp4","*.wma","*.avc"
                                ,"*.mpeg","*.aac","*.flv","*.pcm"));
        File file = fc.showOpenDialog(null);
        String path = file.getAbsolutePath().replace("\\","/");
        media = new Media(new File(path).toURI().toString());
        mp.stop();
        mp = new MediaPlayer(media);
        mp.setAutoPlay(true);
        mv.setMediaPlayer(mp);
    }
   
 
    public static void main(String[] args) {
        launch(args);
    }
   
            
    
}
