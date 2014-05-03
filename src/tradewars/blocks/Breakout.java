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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javafx.scene.Parent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Breakout extends JDialog {

    public Breakout()
    {
        
        add(new Board());
        this.setModal(true);
        setTitle("Breakout");
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(Commons.WIDTH, Commons.HEIGTH);
        setLocationRelativeTo(null);
        setIgnoreRepaint(true);
        setResizable(false);
       
       
        
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                  setVisible(false); //you can't see me!

                  dispose(); //Destroy the JFrame object
                }
            }
        };
        
        this.addWindowListener(exitListener);
         setVisible(true);
        
       // return false;
    }

    public static void main(String[] args) {
        new Breakout();
    }
}