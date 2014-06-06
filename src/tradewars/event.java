/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tradewars;

/**
 *
 * @author baileya
 */
public class event {
  private String description;
  private String type;
  private int quantity;

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
  private String sound;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public event(String description, String type, int quantity, String sound) {
        this.description = description;
        this.type = type;
        this.quantity = quantity;
        this.sound = sound;
    }
  
    
}
