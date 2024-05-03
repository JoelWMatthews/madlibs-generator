/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classwork.madlibsgenerator;

/**
 *
 * @author jellk
 */
public abstract class MadLibEntry
{
    protected String displayString;
    
    public abstract boolean isReplaceable();
    
    public String getDisplayString() 
    {
        return displayString;
    }
    
    public void setDisplayString(String str) 
    {
        displayString = str;
    }
}
