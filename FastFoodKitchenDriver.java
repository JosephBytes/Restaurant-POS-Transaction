package fastfoodkitchen;
import java.util.*;
import java.io.*;

/**
 * @author Joseph Abdulwahab
 */

public class FastFoodKitchenDriver {
    public static void main(String[] args) throws FileNotFoundException, IOException{  
        FastFoodKitchen kitchen = new FastFoodKitchen();
        try{
            Scanner fileScanner = new Scanner(new File("src/fastfoodkitchen/BurgerOrdersExportCSV.csv"));
            fileScanner.nextLine(); //ignore the headerline of csv
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] row = line.split(",");
                int hamFromFile = Integer.parseInt(row[1]); //converting string from csv to integer 
                int cheeseFromFile = Integer.parseInt(row[2]);
                int veggieFromFile = Integer.parseInt(row[3]);
                int sodasFromFile = Integer.parseInt(row[4]);
                boolean toGoFromFile = Boolean.parseBoolean(row[5]);
                int order = kitchen.addOrder(hamFromFile, cheeseFromFile, veggieFromFile, sodasFromFile, toGoFromFile); //add the order to begin the day
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Error, file not found. Try again");
        }
        
        Scanner sc = new Scanner(System.in);
        
        while (kitchen.getNumOrdersPending() != 0) { 
        int num = -1;
            //Extra credit attempt - Random holiday coupon sale implementation 
            int randomItemForSale = (int) (Math.random() *5); // 0 - 4 
            int randomDiscount = (int) ((Math.random() * 75) + 10); //10 - 75
            String a = "";
            if(randomItemForSale == 0){ a = "membership"; }
            if(randomItemForSale == 1){ a = "hamburger"; }
            if(randomItemForSale == 2){ a = "cheeseburger"; }
            if(randomItemForSale == 3){ a = "veggie burger"; }
            if(randomItemForSale == 4){ a = "soda"; }
            System.out.println("Merry Christmas and Happy Holidays! Enjoy " + randomDiscount + "% off of your " + a + " today!");
            
            //menu
            while(num < 0){
                try{ // see what the user wants to do
                    Thread.sleep(500);
                    System.out.println("Please select from the following menu of options, by typing a number:");
                    System.out.println("\t 1. Order food");
                    System.out.println("\t 2. Cancel last order");
                    System.out.println("\t 3. Show number of orders currently pending");
                    System.out.println("\t 4. Complete order");
                    System.out.println("\t 5. Check on order");
                    System.out.println("\t 6. Cancel order");
                    System.out.println("\t 7. Exit"); //writes to file with user ordered food 
                    num = sc.nextInt();
                    if(num < 1 || num > 7) throw new IntegerOutOfRangeException();
                }
                catch(InputMismatchException e){
                    System.out.println("**Please enter an integer");
                    sc.nextLine();
                }
                catch(IntegerOutOfRangeException e){
                    System.out.println("**Please enter an integer from 1 - 7");
                }
                catch(Exception e){
                    System.out.println("**Try again");
                }
            }// end menu while 
                
            
            Boolean loopSwitch = true; 
            while(loopSwitch == true){ //loopSwitch prevents returning to menu after an exception occurs in a switch case
            switch (num) {
                case 1:
                    try{
                        System.out.println("How many hamburgers do you want?");
                        int ham = sc.nextInt();
                        System.out.println("How many cheeseburgers do you want?");
                        int cheese = sc.nextInt();
                        System.out.println("How many veggieburgers do you want?");
                        int veggie = sc.nextInt();
                        System.out.println("How many sodas do you want?");
                        int sodas = sc.nextInt();
                        System.out.println("Is your order to go? (Y/N)");
                        char letter = sc.next().charAt(0);
                        boolean TOGO = false;
                        if (letter == 'Y' || letter == 'y') {
                            TOGO = true;
                        }
                        if(letter != 'Y' && letter != 'y' && letter != 'N' && letter != 'n') throw new YNException();
                        int orderNum = kitchen.addOrder(ham, cheese, veggie, sodas, TOGO);
                        System.out.println("You have " + ham + " hamburgers");
                        System.out.println("You have " + cheese + " cheeseburgers");
                        System.out.println("You have " + veggie + " veggieburgers");
                        System.out.println("You have " + sodas + " sodas");
                        System.out.println("Thank you. Your order number is " + orderNum);
                        System.out.println();
                        loopSwitch = false;
                        
                    }
                    catch(YNException e){
                        System.out.println("**Please enter a Y or N");
                    }
                    catch(InputMismatchException e){
                        System.out.println("**Please enter an integer");
                        sc.nextLine();
                    }
                    catch(Exception e){
                        System.out.println("**Try again");
                    }
                    break;
                case 2:
                    boolean ready = kitchen.cancelLastOrder();
                    if (ready) {
                        System.out.println("Thank you. The last order has been canceled");
                    } else {
                        System.out.println("Sorry. There are no orders to cancel.");
                    }
                    System.out.println();
                    loopSwitch = false;
                    break;
                case 3:
                    System.out.println("There are " + kitchen.getNumOrdersPending() + " pending orders");
                    loopSwitch = false;
                    break;
                case 4:
                    try{
                        System.out.println("Enter order number to complete?");
                        int order = sc.nextInt();
                        kitchen.completeSpecificOrder(order);
                        System.out.println("Your order is ready. Thank you!");
                        loopSwitch = false;
                    }
                    catch(InputMismatchException e){
                        System.out.println("**Please enter an integer");
                        sc.nextLine();
                    }
                    catch(Exception e){
                        System.out.println("**Try again");
                    } 
                    break;
                case 5:
                    try{
                        System.out.println("What is your order number?");
                        int order = sc.nextInt();
                        ready = kitchen.isOrderDone(order);
                        if (ready) {
                            System.out.println("Sorry, no order with this number was found.");
                        } else {
                            System.out.println("No, it's not ready, but it should be up soon. Sorry for the wait.");
                        }
                        System.out.println();
                        loopSwitch = false;
                    }
                    catch(InputMismatchException e){
                        System.out.println("**Please enter an integer");
                        sc.nextLine();
                    }
                    catch(Exception e){
                        System.out.println("**Try again");
                    } 
                    break;
                case 6:
                    try{
                        System.out.println("What is your order number?");
                        int order = sc.nextInt();
                        boolean cancel = kitchen.cancelOrder(order);
                        if (cancel) {
                            System.out.println("Your order has been successfully cancelled ");
                        } else {
                            System.out.println("Sorry, we can’t find your order number in the system");
                        }
                        System.out.println();
                        loopSwitch = false;
                    }
                    catch(InputMismatchException e){
                        System.out.println("**Please enter an integer");
                        sc.nextLine();
                    }
                    catch(Exception e){
                        System.out.println("**Try again");
                    } 
                    break;
                case 7:
                    try{
                        FileOutputStream fs = new FileOutputStream("BurgerOrdersExportCSV.txt");
                        PrintWriter ps = new PrintWriter(fs);
                        ps.println("orderID,numHamburgers,numCheeseburgers,numVeggieburgers,numSodas,toGo");
                        for(int i = 0; i < kitchen.getOrderList().size(); i++){
                            ps.println(kitchen.getOrderList().get(i).getOrderNum() + "," +
                                       kitchen.getOrderList().get(i).getNumHamburger() + "," +
                                       kitchen.getOrderList().get(i).getNumCheeseburgers() + "," +
                                       kitchen.getOrderList().get(i).getNumVeggieburgers() + "," +
                                       kitchen.getOrderList().get(i).getNumSodas() + "," +
                                       kitchen.getOrderList().get(i).isOrderToGo() 
                                       );                            
                        }
                        ps.close();
                        fs.close();
                        loopSwitch = false;
                    }
                    catch(FileNotFoundException e){
                        System.out.println("File not found, sorry!");
                    }
                    catch(IOException e){
                        System.out.println("IO Exception, please try again!");
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("Sorry, but you need to enter a 1, 2, 3, 4, 5, 6, or a 7");
                } //end switch
            }//end loopSwitch iterations 
        }//end program while 
    } //end main
}// end class

