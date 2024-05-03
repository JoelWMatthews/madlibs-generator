/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package classwork.madlibsgenerator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author jellk
 */
public class TitleController
{

    @FXML
    public void switchToPlay() throws IOException
    {
        App.setRoot("play");
    }
    
    @FXML
    public void switchToCreate() throws IOException
    {
        App.setRoot("create");
    }
}
