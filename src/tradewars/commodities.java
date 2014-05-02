
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package tradewars;

//~--- JDK imports ------------------------------------------------------------

import java.util.Random;

/**
 *
 * @author bailey
 */
public class commodities {
    private Random  randomGenerator = new Random();
    private String  statusMsg;
    private int     minQuantity;
    private int     maxQuantity;
    private String  title;
    private String  description;
    private int     minCost;
    private int     maxCost;
    private boolean available;
    private int     chanceToCrash;
    private String  msgForCrash;
    private int     chanceToRush;
    private String  msgForRush;
    private int     currentCost;
    private int     availability;
    private int     startingQuantity;
    private int     currentQuantity;

    public commodities(String title, String description, int minCost, int maxCost, boolean available,
                       int chanceToCrash, String msgForCrash, int chanceToRush, String msgForRush, int availability,
                       int minQuantity, int maxQuantity) {
        this.title         = title;
        this.description   = description;
        this.minCost       = minCost;
        this.maxCost       = maxCost;
        this.available     = available;
        this.chanceToCrash = chanceToCrash;
        this.msgForCrash   = msgForCrash;
        this.chanceToRush  = chanceToRush;
        this.msgForRush    = msgForRush;
        this.availability  = availability;
        this.minQuantity   = minQuantity;
        this.maxQuantity   = maxQuantity;
        this.statusMsg     ="";
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getStartingQuantity() {
        return startingQuantity;
    }

    public void setStartingQuantity(int startingQuantity) {
        this.startingQuantity = startingQuantity;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Random getRandomGenerator() {
        return randomGenerator;
    }

    public void setRandomGenerator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public static int getRandomNumberFrom(int min, int max) {
        Random foo          = new Random();
        int    randomNumber = foo.nextInt((max + 1) - min) + min;

        return randomNumber;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getCurrentCost() {
        return currentCost;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the minCost
     */
    public int getMinCost() {
        return minCost;
    }

    /**
     * @param minCost the minCost to set
     */
    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    /**
     * @return the maxCost
     */
    public int getMaxCost() {
        return maxCost;
    }

    /**
     * @param maxCost the maxCost to set
     */
    public void setMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * @return the chanceToCrash
     */
    public int getChanceToCrash() {
        return chanceToCrash;
    }

    /**
     * @param chanceToCrash the chanceToCrash to set
     */
    public void setChanceToCrash(int chanceToCrash) {
        this.chanceToCrash = chanceToCrash;
    }

    /**
     * @return the msgForCrash
     */
    public String getMsgForCrash() {
        return msgForCrash;
    }

    /**
     * @param msgForCrash the msgForCrash to set
     */
    public void setMsgForCrash(String msgForCrash) {
        this.msgForCrash = msgForCrash;
    }

    /**
     * @return the chanceToRush
     */
    public int getChanceToRush() {
        return chanceToRush;
    }

    /**
     * @param chanceToRush the chanceToRush to set
     */
    public void setChanceToRush(int chanceToRush) {
        this.chanceToRush = chanceToRush;
    }

    /**
     * @return the msgForRush
     */
    public String getMsgForRush() {
        return msgForRush;
    }

    /**
     * @param msgForRush the msgForRush to set
     */
    public void setMsgForRush(String msgForRush) {
        this.msgForRush = msgForRush;
    }

    public void setCurrentCost() {
        int roll = randomGenerator.nextInt(100);

        // System.out.println("Roll was " + roll);
        if (roll >= (100 - chanceToRush)) {
            System.out.println(this.msgForRush);

            // Rush
            // this.currentCost=  getRandomNumberFrom(this.minCost, this.maxCost);
            int bonus = getRandomNumberFrom(5, 10);

            // System.out.println("Bonus is "+ bonus);
            this.currentCost = (roll * bonus);
            System.out.println(this.currentCost);
        } else if (roll <= chanceToCrash) {

            // crash
            System.out.println(this.msgForCrash);
        } else {

            // normal
            //
            System.out.println("Normal Range");
            this.currentCost = getRandomNumberFrom(this.minCost, this.maxCost);
            System.out.print("Cost is :" + "\t" + this.currentCost);
        }
    }

    public String setCurrentCost2() {
        String returnMsg = "";
        int    roll      = randomGenerator.nextInt(100);

        // System.out.println("Roll was " + roll);
        if (roll >= (100 - chanceToRush)) {
            returnMsg = returnMsg + (this.msgForRush);

            // Rush
            // this.currentCost=  getRandomNumberFrom(this.minCost, this.maxCost);
            int bonus = getRandomNumberFrom(5, 10);

            // System.out.println("Bonus is "+ bonus);
            this.currentCost = (roll * bonus);

            // System.out.println(this.currentCost);
        } else if (roll <= chanceToCrash) {

            // crash
            returnMsg = returnMsg + this.getMsgForCrash();

            int bonus = getRandomNumberFrom(5, 10);

 
            // FLOOR COSTS
            this.currentCost = (roll / bonus);

            if (this.currentCost < 1) {
                this.currentCost = 1;
            }

            // returnMsg=returnMsg+("DEBUG  CURRENT PRICE POST " + this.currentCost);
        } else {

            // normal

            returnMsg        = returnMsg + ("Normal Range");
            this.currentCost = getRandomNumberFrom(this.minCost, this.maxCost);

        }

        return returnMsg;
    }

    public void update3() {
        int roll = randomGenerator.nextInt(100);

        if (roll < this.availability) {
            this.available = true;

            // begin
            this.currentQuantity = getRandomNumberFrom(this.minQuantity, this.maxQuantity);
            roll                 = randomGenerator.nextInt(100);

            // System.out.println("Roll was " + roll);
            if (roll >= (100 - chanceToRush)) {
                this.statusMsg = (this.msgForRush);

                // Rush

                int bonus = getRandomNumberFrom(5, 10);

                // System.out.println("Bonus is "+ bonus);
                this.currentCost = (roll * bonus);

                // System.out.println(this.currentCost);
            } else if (roll <= chanceToCrash) {

                // crash
                this.statusMsg = this.getMsgForCrash();

                int bonus = getRandomNumberFrom(5, 10);

                this.currentCost = (roll / bonus);

                if (this.currentCost < 1) {
                    this.currentCost = 1;
                }

            } else {

                // normal
                //
                this.statusMsg   = ("");
                this.currentCost = getRandomNumberFrom(this.minCost, this.maxCost);

                // System.out.print("Cost is :" + "\t" +this.currentCost);
            }


        } else {
            this.available = false;


        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
