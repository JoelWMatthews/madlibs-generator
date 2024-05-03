package classwork.madlibsgenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateController 
{
    @FXML
    private Label lblStep1;
    @FXML
    private TextArea txtStep1;
    @FXML
    private Label lblStep2;
    @FXML
    private ListView<String> lstStep2;
    @FXML
    private Button btnNextStep1;
    @FXML
    private Label lblStep2Word;
    @FXML
    private TextField txtStep2Entry;
    @FXML
    private Button btnStep2Replace;
    @FXML
    private Button btnExport;
    
    private List<String> words = new ArrayList<String>();
    private ObservableList<String> wordsOL;
    
    private int trackedIndex = 0;
    
    private HashMap<Integer, String> replacements = new HashMap<>();
    
    @FXML
    public void onNew() 
    {
        lblStep1.setDisable(false);
        txtStep1.setDisable(false);
        btnNextStep1.setDisable(false);
        
        lblStep2.setDisable(true);
        lstStep2.setDisable(true);
        btnStep2Replace.setDisable(true);
        btnExport.setDisable(true);
        
        words.clear();
        wordsOL.clear();
        replacements.clear();
        
        txtStep1.textProperty().set("");
        txtStep2Entry.textProperty().set("");
    }
    
    @FXML
    public void onTitle() throws IOException 
    {
        App.setRoot("title");
    }

    @FXML
    public void onStep1NextButtonClick() 
    {
        lblStep1.setDisable(true);
        txtStep1.setDisable(true);
        btnNextStep1.setDisable(true);
        
        lblStep2.setDisable(false);
        lstStep2.setDisable(false);
        btnStep2Replace.setDisable(false);
        btnExport.setDisable(false);
        
        words = List.of(txtStep1.textProperty().get().split(" "));
        wordsOL = FXCollections.observableArrayList(words);
        lstStep2.setItems(wordsOL);
    }
    
    @FXML
    public void onStep2ReplaceButtonClick() 
    {
        Integer index = words.indexOf(lstStep2.getSelectionModel().getSelectedItem());
        String replacement = txtStep2Entry.getText();
        
        wordsOL.set(index, "[" + replacement + "]");
        
        replacements.put(trackedIndex, replacement);
        trackedIndex++;
    }
    
    @FXML
    public void onExportButtonClick(ActionEvent e) 
    {
        String result = "";
        Integer index = 0;
        
        for (int i = 0; i < wordsOL.size(); i++) 
        {
            if (wordsOL.get(i).contains("[")) 
            {
                result += "{{" + replacements.get(index) + "}}";
                index++;
            }
            else 
            {
                if (wordsOL.get(i).contains(".") || wordsOL.get(i).contains("!") || wordsOL.get(i).contains("?")) 
                {
                    String punctuation = "";
                    String word = wordsOL.get(i);
                    
                    while (word.contains(".")) 
                    {
                        word = word.substring(0, word.length() - 1);
                        punctuation += ".";
                    }
                    
                    while (word.contains("!")) 
                    {
                        word = word.substring(0, word.length() - 1);
                        punctuation += "!";
                    }
                    
                    while (word.contains("?")) 
                    {
                        word = word.substring(0, word.length() - 1);
                        punctuation += "?";
                    }
                    
                    result += "[[" + word + "]]_((" + punctuation + "))";
                }
                else 
                {
                    result += "[[" + wordsOL.get(i) + "]]";
                }
            }
            if (i+1 < wordsOL.size()) 
            {
                result += "_";
            }
        }
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save MadLibs");
        File file = fileChooser.showSaveDialog((Stage)((Node) e.getSource()).getScene().getWindow());
        if (file != null) 
        {
            try (BufferedWriter bw = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) 
            {
                bw.write(result);
            } 
            catch (IOException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    @FXML
    public void onListItemClick() 
    {
        lblStep2Word.textProperty().set(lstStep2.getSelectionModel().getSelectedItem());
    }
}
