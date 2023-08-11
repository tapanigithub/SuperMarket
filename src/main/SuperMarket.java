import java.util.*;

public class SuperMarket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Double> superMarketProducts = getStringDoubleHashMap();
        ArrayList<String> cart = new ArrayList<>();
        String choice;

        System.out.print("Welcome to supermarket, your account balance is 100 euros.\n" +
                "Can i have your name please: ");
        String name = sc.next();
        Customer customer = new Customer(name);
        customer.printInfo();
        while (true) {
            System.out.print("What would you like to do?\n" +
                    "(1) Go shopping\n(2) View your products\n(3) Consume your products\n" +
                    "(4) Check balance\n(5) Change your name\n(6) Add new products to supermarket\n" +
                    "(7) Make some money\n(0) Exit?: ");
            choice = sc.next();
            if(choice.equals("0")) break;
            switch (choice) {
                case "1" -> goShopping(customer,superMarketProducts,sc,cart);
                case "2" -> customer.viewProducts();
                case "3" -> customer.consumeProducts(sc);
                case "4" -> customer.printInfo();
                case "5" -> customer.changeName(sc);
                case "6" -> addNewProductsToSupermarket(sc,superMarketProducts);
                case "7" -> customer.getMoreMoney(sc);
            }
        }
        System.out.println("Closing program..");
        sc.close();
    }

    private static HashMap<String, Double> getStringDoubleHashMap() {
        HashMap<String, Double> superMarketProducts = new HashMap<>();
        superMarketProducts.put("Beef", 4.2);
        superMarketProducts.put("Burger", 7.5);
        superMarketProducts.put("Pizza", 9.0);
        superMarketProducts.put("Tomato", 0.7);
        superMarketProducts.put("Spaghetti", 1.8);
        superMarketProducts.put("Cola", 2.2);
        superMarketProducts.put("Water", 0.9);
        superMarketProducts.put("Apple juice", 1.4);
        superMarketProducts.put("Beer", 1.1);
        superMarketProducts.put("Vodka", 10.2);
        superMarketProducts.put("Toilet paper", 4.8);
        superMarketProducts.put("Diapers", 9.8);
        superMarketProducts.put("Toy", 17.8);
        superMarketProducts.put("Toothpaste", 2.3);
        superMarketProducts.put("Cool shirt", 22.8);
        return superMarketProducts;
    }


    private static void addNewProductsToSupermarket(Scanner sc, HashMap<String,Double> superMarketProducts) {
        System.out.print("Enter the name of the new product: ");
        String newProduct = sc.next();
        System.out.print("Enter the price of the new product: ");
        String newProductPrice = sc.next();
        try {
            Double price = Double.parseDouble(newProductPrice);
            superMarketProducts.put(newProduct, price);
            System.out.println("Added product: " + newProduct + ", Price: " + price + "\n");
        } catch (Exception e) {
            System.out.println("Add a valid price.\n");
        }
    }

    private static void goShopping(Customer customer, HashMap<String,Double> superMarketProducts, Scanner sc, ArrayList<String> cart) {
        ArrayList<String> productsToCart = new ArrayList<>();
        ArrayList<Double> pricesForTotal = new ArrayList<>();
        for(Map.Entry<String,Double> entry : superMarketProducts.entrySet())
        {
            productsToCart.add(entry.getKey());
            pricesForTotal.add(entry.getValue());
        }

        double total = 0;
        int i = 1;
        System.out.println("\nYour account balance is: " + customer.getMoney());
        while (true) {
            System.out.println("Total:" + total);
            for(Map.Entry<String,Double> entry : superMarketProducts.entrySet())
            {
                System.out.println("("+i+")" + entry.getKey() + " : " + entry.getValue());
                i++;
            }
            System.out.print("Choose what you want(0 to quit):");
            i = 1;
            int choice = getValidInt(sc) - 1;
            if (choice == -1) {
                customer.finishTransaction(cart, total);
                cart.clear();
                productsToCart.clear();
                pricesForTotal.clear();
                break;
                }
            else {
                try {
                    cart.add(productsToCart.get(choice));
                    total += pricesForTotal.get(choice);
                    System.out.println("Product added to cart.\n");
                } catch (Exception e) {
                    System.out.println("Invalid choice.");
                    break;
                }
            }
        }
    }

    private static int getValidInt(Scanner sc) {
        try {
            return sc.nextInt();
        } catch (InputMismatchException ime) {
            return -1;
        }
    }

}
