import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Customer {
    String name;
    private double money = 100;
    ArrayList<String> myProducts = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public double getMoney() {
        return this.money;
    }

    public String getName() {
        return this.name;
    }

    public void changeName(Scanner sc) {
        System.out.print("Please enter your name: ");
        this.name = sc.next();
        System.out.println("Name changed to: " + getName() + "\n");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printInfo() {
        System.out.println("\nHello " + getName() + "!\n" +
                "Your account balance is: " + getMoney() + "\n");
    }

    public boolean canAfford(double cartTotal) {
        return this.money >= cartTotal;
    }

    public void viewProducts() {
        System.out.println("\n" + this.name + ", you have the following products: " + this.myProducts + "\n");
    }

    public void transaction(double amount) {
        this.money -= amount;
    }

    public void finishTransaction(ArrayList<String> cart, double total) {
        if (canAfford(total)) {
            transaction(total);
            System.out.println("You bought: " + cart + " For a total of: " + total + "\n" +
                    "You now have: " + getMoney() + "\n");
            myProducts.addAll(cart);
        } else {
            System.out.println("You cant afford all of these, returning to menu.\n");
        }
    }

    public void consumeProducts(Scanner sc) {
        System.out.print("Here are your products: " + this.myProducts + "\n" +
                "What would you like to eat/drink/use?(Enter a number(1 being the first product of the list)): ");
        String productToDelete = sc.next();
        try {
            this.myProducts.remove(Integer.parseInt(productToDelete) - 1);
            System.out.println("Done.");
        } catch (Exception e) {
            System.out.println("Incorrect input." + "\n");
        }
    }

    public void getMoreMoney(Scanner sc) {
        Random rand = new Random();
        int winningNumber = rand.nextInt(10) + 1;
        System.out.print("You will have to do some work for the money.\n" +
                "Guess a number between 1 and 10 and you will be rewarded with 100 euros.\n" +
                "Your guess: ");
        while (true) {
            try {
                int guess = sc.nextInt();
                if (guess == winningNumber) {
                    System.out.println("Congratulations, you have won some money!\n");
                    this.money += 100;
                    break;
                } else System.out.print("Wrong.\nYour guess: ");
            } catch (InputMismatchException ime) {
                System.out.print("Wrong.\nYour guess: ");
                sc.nextLine();
            }
        }
    }



}
