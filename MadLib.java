/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classwork.madlibsgenerator;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author jellk
 */
public class MadLib 
{
    public List<MadLibEntry> paragraph = new ArrayList<>();
    
    private Dictionary<Integer, String> replacements = new Hashtable<>();
    
    public MadLib(String str) 
    {
        initializeFromString(str);
    }
    
    public void initializeFromString(String str) 
    {
        //example:
        //[[The]]_{{Adjective}}_{{Color}}_({Animal})_[[jumps]]_[[over]]_[[the]]_{{Adjective}}_{{Animal}}_((.))
        //
        //[[]] - word
        //(()) - punctuation
        //{{}} - replaceable
        
        paragraph.clear();
        replacements = new Hashtable<>();
        
        String[] words = str.split("_");
        
        int key = 0;
        
        for (int i = 0; i < words.length; i++) 
        {
            if (words[i].startsWith("[[") && words[i].endsWith("]]")) 
            {
                //Word
                MadLibEntryWord word = new MadLibEntryWord();
                word.displayString = words[i].substring(2, words[i].length() - 2);
                
                paragraph.add(word);
                continue;
            }
            
            if (words[i].startsWith("{{") && words[i].endsWith("}}")) 
            {
                //Replaceable
                MadLibEntryReplaceable replaceable = new MadLibEntryReplaceable(key);
                key++;
                replaceable.displayString = words[i].substring(2, words[i].length() - 2);
                
                paragraph.add(replaceable);
                continue;
            }
            
            if (words[i].startsWith("((") && words[i].endsWith("))")) 
            {
                //Punctuation
                MadLibEntryWord punctuation = new MadLibEntryWord();
                punctuation.displayString = words[i].substring(2, words[i].length() - 2);
                punctuation.isPunctuation = true;
                
                paragraph.add(punctuation);
            }
        }
    }
    
    public List<MadLibEntryReplaceable> getReplaceables() 
    {
        List<MadLibEntryReplaceable> result = new ArrayList<>();
        
        for (int i = 0; i < paragraph.size(); i++) 
        {
            if (paragraph.get(i).isReplaceable()) 
            {
                result.add((MadLibEntryReplaceable) paragraph.get(i));
            }
        }
        
        return result;
    }
    
    public void addReplacement(int key, String value) 
    {
        replacements.put(key, value);
    }
    
    public String getReadableString() 
    {
        String readable = "";
        
        for (int i = 0; i < paragraph.size(); i++) 
        {
            if (paragraph.get(i).isReplaceable()) 
            {
                MadLibEntryReplaceable entry = (MadLibEntryReplaceable) paragraph.get(i);
                readable += replacements.get(entry.getKey()) + " ";
            } 
            else 
            {
                MadLibEntryWord entry = (MadLibEntryWord) paragraph.get(i);
                
                if (entry.isPunctuation) 
                {
                    readable = readable.substring(0, readable.length() - 1) + entry.getDisplayString() + " ";
                } 
                else 
                {
                    readable += entry.getDisplayString() + " ";
                }
                
            }
        }
        
        return readable.substring(0, readable.length() - 1);
    }
}
