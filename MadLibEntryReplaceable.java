/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classwork.madlibsgenerator;

/**
 *
 * @author jellk
 */
public class MadLibEntryReplaceable extends MadLibEntry
{
    private Integer key;
    
    public MadLibEntryReplaceable(Integer _key) 
    {
        key = _key;
    }
    
    @Override
    public boolean isReplaceable() 
    {
        return true;
    }
    
    public Integer getKey() 
    {
        return key;
    }
}