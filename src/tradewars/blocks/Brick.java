/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tradewars.blocks;

/**
 *
 * @author bailey
 */
import javax.swing.ImageIcon;


public class Brick extends Sprite {

    String brickie = "/tradewars/blocks/res/brickie.png";

    boolean destroyed;


    public Brick(int x, int y) {
      this.x = x;
      this.y = y;

      ImageIcon ii = new ImageIcon(this.getClass().getResource(brickie));
      image = ii.getImage();

      width = image.getWidth(null);
      heigth = image.getHeight(null);

      destroyed = false;
    }

    public boolean isDestroyed()
    {
      return destroyed;
    }

    public void setDestroyed(boolean destroyed)
    {
      this.destroyed = destroyed;
    }

}