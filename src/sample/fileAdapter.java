package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class fileAdapter {
    final FileChooser fileChooser = new FileChooser();
    private BufferedReader br;
    private static Stage primaryStage; // **Declare static Stage**
    static public Stage getPrimaryStage() {
        return primaryStage;
    }
    private final Desktop desktop = Desktop.getDesktop();
    private static void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
    }
    public ArrayList<String> fileRead(){
        ArrayList<String> datalist = new ArrayList<String>();
        configureFileChooser(fileChooser);
        Stage s = getPrimaryStage();
        File file = fileChooser.showOpenDialog(s);
        if (file != null) {
            try {
                FileReader fr=new FileReader(file.getPath());
                br = new BufferedReader(fr);
                String line = "";
                while (br.ready()){
                    datalist.add(br.readLine());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return datalist;
    }
}
