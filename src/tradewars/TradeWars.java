package tradewars;

//~--- JDK imports ------------------------------------------------------------
import java.awt.Color;
import java.io.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import tradewars.blocks.Breakout;

public class TradeWars {
    public static int                     counter               = 0;
    public static int                     currentPlayerLocation = 0;
    public static tradewars.locations[]   gameLocations         = new tradewars.locations[8];
    static gameFrame2                     bob                   = new gameFrame2();
    public static player                  p1                    = new player("Bailey");
    public static Properties              gameLocationsP        = new Properties();
    public static Properties              gameCommoditiesP      = new Properties();
    public static Properties              gameEventsP           = new Properties();
    public static tradewars.commodities[] gameBag2;
    public static tradewars.commodities[] playerBag;
    public static tradewars.event[]       gameEvents;
    public static MyTableModel            gameTableModel;
    public static MyTableModelPlayer      playerTableModel;

    public static void main(String[] args) throws IOException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                bob.setVisible(true);
            }
        });
        bob.jTable1.getColumnModel().getColumn(0).setHeaderValue("Name");
        bob.jTable1.getColumnModel().getColumn(1).setHeaderValue("Available");
        bob.jTable1.getColumnModel().getColumn(2).setHeaderValue("Buy Price");
        bob.jTable1.getColumnModel().getColumn(3).setHeaderValue("Quanity Available");
        bob.jTableBag.getColumnModel().getColumn(0).setHeaderValue("Name");
        bob.jTableBag.getColumnModel().getColumn(1).setHeaderValue("Available");
        bob.jTableBag.getColumnModel().getColumn(2).setHeaderValue("Purchase Price");
        bob.jTableBag.getColumnModel().getColumn(3).setHeaderValue("Quanity Held");
        bob.jButtonBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjButtonBuyPerformed(evt);
            }
        });
        bob.jButtonSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjButtonSellPerformed(evt);
            }
        });
        bob.jButtonL0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjButtonL0ActionPerformed(evt);
            }
        });
        bob.jButtonL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjButtonL1ActionPerformed(evt);
            }
        });
        bob.jButtonL2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjButtonL2ActionPerformed(evt);
            }
        });
        bob.jButtonL3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjButtonL3ActionPerformed(evt);
            }
        });
        bob.jButtonL4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjButtonL4ActionPerformed(evt);
            }
        });
        bob.jButtonL5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjButtonL5ActionPerformed(evt);
            }
        });
        bob.jButtonL6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjButtonL6ActionPerformed(evt);
            }
        });
        bob.jButtonL7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnjButtonL7ActionPerformed(evt);
            }
        });

        // Setup player info.
        p1.setBagSize(50);
        p1.setBagSpaceUsed(0);
        p1.setPlayerCash(2000);
        p1.setPlayerLife(100);

        // Setup game locations - loop and read from file, populate array of locations and label buttons!
        gameLocationsP.load(TradeWars.class.getClassLoader().getResourceAsStream("tradewars/locations.properties"));

        for (int F = 0; F < 8; F++) {
            gameLocations[F] = new tradewars.locations((Integer.parseInt(gameLocationsP.getProperty("location" + F
                    + ".id"))), gameLocationsP.getProperty("location" + F + ".name"),
                                gameLocationsP.getProperty("location" + F + ".description"));
            bob.button_array[F].setText(gameLocations[F].getName());
        }

        // Setup game EVENTS
        gameEventsP.load(TradeWars.class.getClassLoader().getResourceAsStream("tradewars/events.properties"));
        gameEvents = new tradewars.event[(Integer.parseInt(gameEventsP.getProperty("events.numberOfEntries")))];

        for (int F = 0; F < (Integer.parseInt(gameEventsP.getProperty("events.numberOfEntries"))); F++) {
            gameEvents[F] = new tradewars.event(gameEventsP.getProperty("event" + F + ".description"),
                    gameEventsP.getProperty("event" + F + ".type"),
                    (Integer.parseInt(gameEventsP.getProperty("event" + F + ".quantity"))));
        }

        // Populate commodities
        gameCommoditiesP.load(TradeWars.class.getClassLoader().getResourceAsStream("tradewars/commodities.properties"));
        gameBag2 =
            new tradewars.commodities[(Integer.parseInt(gameCommoditiesP.getProperty("commodity.numberOfEntries")))];
        playerBag =
            new tradewars.commodities[(Integer.parseInt(gameCommoditiesP.getProperty("commodity.numberOfEntries")))];

        for (int F = 0; F < (Integer.parseInt(gameCommoditiesP.getProperty("commodity.numberOfEntries"))); F++) {
            gameBag2[F] = new tradewars.commodities(gameCommoditiesP.getProperty("commodity" + F + ".name"),
                    gameCommoditiesP.getProperty("commodity" + F + ".description"),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".minCost"))),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".maxCost"))),
                    (Boolean.parseBoolean(gameCommoditiesP.getProperty("commodity" + F + ".available"))),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".chanceToCrash"))),
                    gameCommoditiesP.getProperty("commodity" + F + ".msgForCrash"),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".chanceToRush"))),
                    gameCommoditiesP.getProperty("commodity" + F + ".msgForRush"),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".availability"))),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".minQuantity"))),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".maxQuantity"))));
            playerBag[F] = new tradewars.commodities(gameCommoditiesP.getProperty("commodity" + F + ".name"),
                    gameCommoditiesP.getProperty("commodity" + F + ".description"),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".minCost"))),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".maxCost"))),
                    (Boolean.parseBoolean(gameCommoditiesP.getProperty("commodity" + F + ".available"))),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".chanceToCrash"))),
                    gameCommoditiesP.getProperty("commodity" + F + ".msgForCrash"),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".chanceToRush"))),
                    gameCommoditiesP.getProperty("commodity" + F + ".msgForRush"),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".availability"))),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".minQuantity"))),
                    (Integer.parseInt(gameCommoditiesP.getProperty("commodity" + F + ".maxQuantity"))));
            playerBag[F].setCurrentQuantity(0);
            playerBag[F].setAvailable(false);
        }

        gameTableModel = new MyTableModel();
        bob.jTable1.setModel(gameTableModel);
        gameTableModel.fireTableDataChanged();
        playerTableModel = new MyTableModelPlayer();
        bob.jTableBag.setModel(playerTableModel);
        playerTableModel.fireTableDataChanged();
        updateGameLoop();
    }

    public static void updateGameLoop() 
    {      try {
//        File foop = new File((TradeWars.class.getResource("/tradewars/res/l" + currentPlayerLocation+ ".wav")).toURI() );
        File foop = new File((TradeWars.class.getResource("/tradewars/res/l0.wav")).toURI() );
        playSound(foop);
        
        
        ImageIcon image = new ImageIcon(TradeWars.class.getResource("/tradewars/res/l" + currentPlayerLocation
                + ".jpg"));
        
        bob.jLabelLocation.setIcon(image);
        int chancer = tradewars.commodities.getRandomNumberFrom(1, 100);
        if (chancer>70){handleGameEvent();}
        
        
        // updateBag();
        // enable locations buttons except the current player location
        for (int x = 0; x < 8; x++) {
            bob.button_array[x].setEnabled(true);
        }

        bob.button_array[currentPlayerLocation].setEnabled(false);

        // itterate game turns
        // Consider adding time limit here?
        counter++;

        // update player stats on form
        bob.jTextField1.setText(String.valueOf(counter));
        bob.jTextFieldCash.setText("£" + String.valueOf(p1.getPlayerCash()));
        bob.jTextFieldBagSpaceTotal.setText(String.valueOf(p1.getBagSize()));
        bob.jTextFieldBagSpaceFree.setText(String.valueOf(p1.getBagSize() - p1.getBagSpaceUsed()));
        bob.jTextField2.setText(gameLocations[currentPlayerLocation].getName());
        bob.jTextField3.setText(gameLocations[currentPlayerLocation].getDescription());

        // update all of the commodities
        for (int x = 0; x < gameBag2.length; x++) {
            gameBag2[x].update3();
            if (!gameBag2[x].getStatusMsg().equals(""))
            {
                //bob.jTextArea2.setForeground(Color.BLUE);
                bob.jTextArea2.append("[MARKET]\t"+gameBag2[x].getStatusMsg()+"\n");
                bob.jTextArea2.setCaretPosition(bob.jTextArea2.getDocument().getLength());
                //bob.jTextArea2.setForeground(Color.BLACK);
            }
        }

        gameTableModel.fireTableDataChanged();
        } catch (URISyntaxException ex) {
            Logger.getLogger(TradeWars.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }

    // Does this serve any purpose after the first loop?
    public static void updateBag() {}

    private static void btnjButtonSellPerformed(java.awt.event.ActionEvent evt) {
        if (bob.jTableBag.getSelectedRow() == -1) {                         // No row chosen to sell?
            bob.jTextArea2.append("No selection\n");
        } else {
            int    key             = bob.jTableBag.getSelectedRow();
            String message         = JOptionPane.showInputDialog(null,
                                         "How many " + gameBag2[key].getTitle() + "to Sell? ");
            int    desiredQuantity = Integer.parseInt(message);

            if (desiredQuantity > playerBag[key].getCurrentQuantity()) {    // Try to sell more than you have
                JOptionPane.showMessageDialog(null, "Sorry, not that many to sell!");
            } else {
                int sellPriceWouldBe = desiredQuantity * gameBag2[key].getCurrentCost();

                if ((gameBag2[key].isAvailable())) {
                    p1.setBagSpaceUsed((p1.getBagSpaceUsed() - desiredQuantity));
                    bob.jTextFieldBagSpaceFree.setText(String.valueOf((p1.getBagSize() - p1.getBagSpaceUsed())));
                    p1.setPlayerCash((p1.getPlayerCash() + sellPriceWouldBe));
                    bob.jTextFieldCash.setText("£" + String.valueOf(p1.getPlayerCash()));

                    int quantityRemaining = playerBag[key].getCurrentQuantity() - desiredQuantity;

                    JOptionPane.showMessageDialog(null, "Should be " + quantityRemaining + " left");
                    playerBag[key].setCurrentQuantity((quantityRemaining));

                    if ((quantityRemaining == 0)) {
                        bob.jTableBag.setValueAt("", key, 2);
                    } else {
                        bob.jTableBag.setValueAt(quantityRemaining, key, 3);
                    }

                    gameBag2[key].setCurrentQuantity((gameBag2[key].getCurrentQuantity() + desiredQuantity));

                    // bob.jTableBag.setValueAt((playerBag[bob.jTableBag.getSelectedRow()].getCurrentQuantity()),bob.jTableBag.getSelectedRow(),3);
                } else {    // NOT available to SELL here
                    JOptionPane.showMessageDialog(null, "No market for that here!");
                }
            }
        }

        playerTableModel.fireTableDataChanged();
    }

    private static void btnjButtonBuyPerformed(java.awt.event.ActionEvent evt) {
        if (bob.jTable1.getSelectedRow() == -1) {                          // No row selected  to buy
            bob.jTextArea2.append("No selection\n");
        } else {
            int    key             = bob.jTable1.getSelectedRow();
            String message         = JOptionPane.showInputDialog(null,
                                         "How many " + gameBag2[key].getTitle() + "to buy? ");
            int    desiredQuantity = Integer.parseInt(message);

            if (desiredQuantity > gameBag2[key].getCurrentQuantity()) {    // Try to buy more than available
                JOptionPane.showMessageDialog(null, "Sorry, not that many for sale!");
            } else {                                                       // Buy within range of available quantity
                int costWouldBe = desiredQuantity * gameBag2[key].getCurrentCost();

                if (costWouldBe < p1.getPlayerCash()) {                    // can afford
                    if (addToBag(desiredQuantity)) {                       // room, can afford and that much is available
                        bob.jTextFieldBagSpaceFree.setText(String.valueOf((p1.getBagSize() - p1.getBagSpaceUsed())));
                        p1.setPlayerCash((p1.getPlayerCash() - costWouldBe));
                        bob.jTextFieldCash.setText("£" + String.valueOf(p1.getPlayerCash()));

                        int quantityRemaining = gameBag2[key].getCurrentQuantity() - desiredQuantity;

                        JOptionPane.showMessageDialog(null, "Should be " + quantityRemaining + " left");
                        gameBag2[key].setCurrentQuantity((quantityRemaining));

                        // bob.jTable1.setValueAt(quantityRemaining, key,3);
                        playerBag[key].setCurrentQuantity((playerBag[key].getCurrentQuantity() + desiredQuantity));

                        // bob.jTableBag.setValueAt((playerBag[bob.jTable1.getSelectedRow()].getCurrentQuantity()),bob.jTable1.getSelectedRow(),3);
                        // -------need to add code to do mean average purchase price in cases where a second quantity is purchased @ a different price
                        // CRAZY PRICE PRICE
                        // bob.jTableBag.setValueAt((gameBag2[bob.jTable1.getSelectedRow()].getCurrentCost()),bob.jTable1.getSelectedRow(),2);
                    } else {    // not enough room?
                        JOptionPane.showMessageDialog(null, "Not enough bag space!");
                    }
                } else {        // can not afford
                    JOptionPane.showMessageDialog(null,
                                                  "You can not afford to buy " + desiredQuantity + " "
                                                  + gameBag2[bob.jTable1.getSelectedRow()].getTitle());
                }
            }
        }

        gameTableModel.fireTableDataChanged();
        playerTableModel.fireTableDataChanged();
    }

    private static boolean addToBag(int ammountToAdd) {
        boolean success = true;

        if (p1.getBagSpaceUsed() + ammountToAdd <= p1.getBagSize()) {
            p1.setBagSpaceUsed((p1.getBagSpaceUsed() + ammountToAdd));
        } else {
            success = false;
        }

        return success;
    }

    private static void btnjButtonL0ActionPerformed(java.awt.event.ActionEvent evt) {

        // we don't need this any more?
        bob.jTextField1.setText(String.valueOf(counter));
        currentPlayerLocation = 0;
        updateGameLoop();
    }

    private static void btnjButtonL1ActionPerformed(java.awt.event.ActionEvent evt) {
        bob.jTextField1.setText(String.valueOf(counter));
        currentPlayerLocation = 1;
        updateGameLoop();
    }

    private static void btnjButtonL2ActionPerformed(java.awt.event.ActionEvent evt) {
        bob.jTextField1.setText(String.valueOf(counter));
        currentPlayerLocation = 2;
        updateGameLoop();
    }

    private static void btnjButtonL3ActionPerformed(java.awt.event.ActionEvent evt) {
        bob.jTextField1.setText(String.valueOf(counter));
        currentPlayerLocation = 3;
        updateGameLoop();
    }

    private static void btnjButtonL4ActionPerformed(java.awt.event.ActionEvent evt) {
        bob.jTextField1.setText(String.valueOf(counter));
        currentPlayerLocation = 4;
        updateGameLoop();
    }

    private static void btnjButtonL5ActionPerformed(java.awt.event.ActionEvent evt) {
        bob.jTextField1.setText(String.valueOf(counter));
        currentPlayerLocation = 5;
        updateGameLoop();
    }

    private static void btnjButtonL6ActionPerformed(java.awt.event.ActionEvent evt) {
        bob.jTextField1.setText(String.valueOf(counter));
        currentPlayerLocation = 6;
        updateGameLoop();
    }

    private static void btnjButtonL7ActionPerformed(java.awt.event.ActionEvent evt) {
        bob.jTextField1.setText(String.valueOf(counter));
        currentPlayerLocation = 7;
        updateGameLoop();
    }

    private static void btnSetNameActionPerformed2(java.awt.event.ActionEvent evt) {
        bob.jTextField1.setText(String.valueOf(counter));
        updateGameLoop();
    }

    private static void handleGameEvent() {
        int    choice          = tradewars.commodities.getRandomNumberFrom(0, gameEvents.length - 1);
        int    randomCommodity = tradewars.commodities.getRandomNumberFrom(0, playerBag.length - 1);
        String foo2            = (gameEvents[choice].getDescription());
        String foo3            = foo2;

        if ((gameEvents[choice].getType()).equals("find")) {
            playerBag[randomCommodity].setCurrentQuantity((playerBag[randomCommodity].getCurrentQuantity()
                    + gameEvents[choice].getQuantity()));
            p1.setBagSpaceUsed(p1.getBagSpaceUsed() + gameEvents[choice].getQuantity());
            playerTableModel.fireTableDataChanged();
            foo3 = foo2.replace("*", gameBag2[randomCommodity].getTitle());
        } else if ((gameEvents[choice].getType()).equals("lose")) {
            {
                if (((playerBag[randomCommodity].getCurrentQuantity()) >= gameEvents[choice].getQuantity())) {
                    bob.jTextArea2.append("more than " + gameEvents[choice].getQuantity() + " to lose!");
                    playerBag[randomCommodity].setCurrentQuantity((playerBag[randomCommodity].getCurrentQuantity()
                            - gameEvents[choice].getQuantity()));
                    p1.setBagSpaceUsed(p1.getBagSpaceUsed() - gameEvents[choice].getQuantity());
                    playerTableModel.fireTableDataChanged();
                    foo3 = foo2.replace("*", playerBag[randomCommodity].getTitle());
                } else {
                    foo3 = foo2.replace("*", "cash");
                    p1.setPlayerCash(p1.getPlayerCash() - gameEvents[choice].getQuantity());
                }
            }

            // }
        } else if ((gameEvents[choice].getType()).equals("bagBigger")) {
            p1.setBagSize((p1.getBagSize() + gameEvents[choice].getQuantity()));
        } else if ((gameEvents[choice].getType()).equals("bagSmaller")) {
            p1.setBagSize((p1.getBagSize() - gameEvents[choice].getQuantity()));
            bob.disable();
            Breakout goon = new Breakout();
            bob.enable();
        }
        
        bob.jTextArea2.append("[EVENT]\t"+foo3 + "\n");
    }

    public static class MyTableModel extends AbstractTableModel {
        private String[] columnNames = { "Name", "Available", "Price", "Quantity" };

        public int getRowCount() {
            return gameBag2.length;
        }

        public int getColumnCount() {
            return 4;
        }

        public String getColumnName(int column) {
            return columnNames[column];
        }

        public Object getValueAt(int row, int column) {
            switch (column) {
            case 0 :
                return gameBag2[row].getTitle();

            case 1 :
                if (gameBag2[row].isAvailable()) {
                    return "Y";
                } else {
                    return "N";
                }
            case 2 :
                if (gameBag2[row].isAvailable()) {
                    return gameBag2[row].getCurrentCost();
                } else {
                    return "";
                }
            case 3 :
                if (gameBag2[row].isAvailable()) {
                    return gameBag2[row].getCurrentQuantity();
                } else {
                    return "";
                }
            default :
                return "";
            }
        }
    }


    public static class MyTableModelPlayer extends AbstractTableModel {
        private String[] columnNames = { "Name", "Available", "Price Paid", "Held" };

        public int getRowCount() {
            return playerBag.length;
        }

        public int getColumnCount() {
            return 4;
        }

        public String getColumnName(int column) {
            return columnNames[column];
        }

        public Object getValueAt(int row, int column) {
            switch (column) {
            case 0 :
                return playerBag[row].getTitle();

            case 1 :
                if ((playerBag[row].getCurrentQuantity() > 0)) {
                    return "Y";
                } else {
                    return "N";
                }
            case 2 :
                if ((playerBag[row].getCurrentQuantity() > 0)) {
                    return playerBag[row].getCurrentCost();
                } else {
                    return "";
                }
            case 3 :
                if ((playerBag[row].getCurrentQuantity() > 0)) {
                    return playerBag[row].getCurrentQuantity();
                } else {
                    return "";
                }
            default :
                return "";
            }
        }
    }
public static void playSound(File yourFile)
{
    System.out.println("I'm prinitng 1");
try {
    System.out.println("I'm prinitng 2");
    //File yourFile = yourFileName;
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;
    
    stream = AudioSystem.getAudioInputStream(yourFile);
    format = stream.getFormat();
    info = new DataLine.Info(Clip.class, format);
    clip = (Clip) AudioSystem.getLine(info);
    clip.open(stream);
    clip.start();
}
catch (Exception e) {
    //whatevers
}}
    
}

