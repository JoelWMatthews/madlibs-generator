package classwork.madlibsgenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jellk
 */
public class PlayController
{
    private MadLib ml;
    List<MadLibEntryReplaceable> replaceables;
    
    private int index = 0;
    
    @FXML
    private Button btnOpenFile;
    @FXML
    private Label lblDirections;
    @FXML
    private Label lblWordType;
    @FXML
    private TextField txtReplacement;
    @FXML
    private Button btnNext;
    @FXML
    private Label lblResults;
    @FXML
    private TextArea txtResults;
    
    @FXML
    public void onTitle() throws IOException 
    {
        App.setRoot("title");
    }
    
    @FXML
    public void openFile(ActionEvent e) 
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save MadLibs");
        File file = fileChooser.showOpenDialog((Stage)((Node) e.getSource()).getScene().getWindow());
        if (file != null) 
        {
            try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) 
            {
                ml = new MadLib(br.readLine());
                br.close();
            } 
            catch (IOException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
        
        replaceables = ml.getReplaceables();
        
        btnOpenFile.setDisable(true);
        lblDirections.setDisable(false);
        lblWordType.setDisable(false);
        txtReplacement.setDisable(false);
        btnNext.setDisable(false);
        
        nextWord();
    }
    
    public void nextWord() 
    {
        if (index >= replaceables.size()) 
        {
            ml.addReplacement(index - 1, txtReplacement.getText());
            txtReplacement.setText("");
            
            lblDirections.setDisable(true);
            lblWordType.setDisable(true);
            txtReplacement.setDisable(true);
            btnNext.setDisable(true);
            
            lblResults.setDisable(false);
            txtResults.setDisable(false);
            txtResults.setText(ml.getReadableString());
            return;
        }
        
        lblWordType.setText(replaceables.get(index).getDisplayString());
        
        if (index != 0) 
        {
            ml.addReplacement(index - 1, txtReplacement.getText());
            txtReplacement.setText("");
        }
        
        
        index++;
    }
}
