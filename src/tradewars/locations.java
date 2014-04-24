/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tradewars;

/**
 *
 * @author bailey
 */
public class locations {

    public locations(int ID, String Name, String description) {
        this.ID = ID;
        this.Name = Name;
        this.description = description;
       
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public commodities[] getMenu() {
        return menu;
    }

    public void setMenu(commodities[] menu) {
        this.menu = menu;
    }
  
    private int ID;
    private String Name;
    private String description;
    private tradewars.commodities[] menu;
    
    
    
    
    
    
}
