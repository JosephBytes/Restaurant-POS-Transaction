package fastfoodkitchen;
public class BurgerOrder {
    private int numHamburgers = 0;
    private int numCheeseburgers = 0;
    private int numVeggieburgers = 0;
    private int numSodas = 0;
    private boolean orderToGo = false;
    private int orderNum = 1;
    
    public BurgerOrder(int numHamburgers, int numCheeseburgers, int numVeggieburgers, int numSodas, boolean orderToGo, int orderNum) {
        this.numHamburgers = numHamburgers;
        this.numCheeseburgers = numCheeseburgers;
        this.numVeggieburgers = numVeggieburgers;
        this.numSodas = numSodas;
        this.orderToGo = orderToGo;
        this.orderNum = orderNum;
    }
    
    public int getNumHamburger() {
        return numHamburgers;
    }
    
    public void setNumHamburger(int numHamburgers) {
        if (numHamburgers < 0) {
            System.out.println("Number of hamburgers must be 0 or more.");
        } else {
            this.numHamburgers = numHamburgers;
        }
    }
    
    public int getNumCheeseburgers() {
        return numCheeseburgers;
    }
    
    public void setNumCheeseburgers(int numCheeseburgers) {
        if (numCheeseburgers < 0) {
            System.out.println("Number of cheeseburgers must be 0 or more.");
        } else {
            this.numCheeseburgers = numCheeseburgers;
        }
    }
    
    public int getNumVeggieburgers() {
        return numVeggieburgers;
    }
    
    public void setNumVeggieburgers(int numVeggieburgers) {
        if (numVeggieburgers < 0) {
            System.out.println("Number of veggieburgers must be 0 or more.");
        } else {
            this.numVeggieburgers = numVeggieburgers;
        }
    }
    
    public int getNumSodas() {
        return numSodas;
    }
    
    public void setNumSodas(int numSodas) {
        this.numSodas = numSodas;
    }
    
    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    
    public boolean isOrderToGo() {
        return orderToGo;
    }
    
    public void setOrderToGo(boolean orderToGo) {
        this.orderToGo = orderToGo;
    }

    public int getTotalBurgers() {
        return numHamburgers + numCheeseburgers + numVeggieburgers;
    }

    public String toString() {
        return "BurgerOrder{" + "numHamburgers=" + numHamburgers + ", numCheeseburgers=" + numCheeseburgers
                + ", numVeggieburgers=" + numVeggieburgers + ", numSodas=" + numSodas + ", orderToGo=" + orderToGo
                + ", orderNum=" + orderNum + '}';
    }
}
