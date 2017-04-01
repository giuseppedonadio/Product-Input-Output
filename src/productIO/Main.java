package productIO;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		// display a welcome message
		Console.displayLine("Welcome to the Product I/O JAVA App\n");
		
		// display the command menu
        displayMenu();
        
        // perform 1 or more actions
        String action = "";
        while (!action.equalsIgnoreCase("exit")) {
            // get the input from the user
            action = Console.getString("Enter a command: ");
            System.out.println();

            if (action.equalsIgnoreCase("display")) {
                displayAllProducts();
            } else if (action.equalsIgnoreCase("add")) {
            	addProduct();
            } else if (action.equalsIgnoreCase("del") || 
                       action.equalsIgnoreCase("delete")) {
                deleteProduct();
            } else if (action.equalsIgnoreCase("help") || 
                       action.equalsIgnoreCase("menu")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit")) {
            	Console.displayLine("Bye.\n");
            } else {
            	Console.displayLine("Error! Not a valid command.\n");
            }
        }
		

	}
	
	public static void displayMenu() {
		Console.displayLine("COMMAND MENU");
		Console.displayLine("display - List of all the products");
		Console.displayLine("add     - Add a product");
		Console.displayLine("del     - Delete a product");
		Console.displayLine("help    - Show this menu");
		Console.displayLine("exit    - Exit this application\n");
    }
	
	public static void displayAllProducts() {
		Console.displayLine("PRODUCT LIST");

        List<Product> products = ProductIO.getAll();
        
        if (products == null) {
        	Console.displayLine("\nError! Unable to get products.\n");
        } else {
            Product p;
            StringBuilder sb = new StringBuilder();
            for (Product product : products) {
                p = product;
                sb.append(StringUtil.padWithSpaces(
                        p.getCode(), 12));
                sb.append(StringUtil.padWithSpaces(
                        p.getDescription(), 34));
                sb.append(p.getFormattedPrice());
                sb.append("\n");
            }
            Console.displayLine(sb.toString());
        }
    }
	
	public static void deleteProduct() {
        String code = Console.getString("Enter product code to delete: ");

        Product product = ProductIO.get(code);
        if (product == null) {
        	Console.displayLine("\nError! Unable to get product.");
        }
        else {        
            ProductIO.delete(product);        
            Console.displayLine("\n" + product.getDescription() + 
                    " was deleted from the database.\n");
        }
    }

	
	public static void addProduct() {
        String code = Console.getString("Enter product code: ");
        String description = Console.getString("Enter product description: ");
        double price = Console.getDouble("Enter price: ");

        Product product = new Product();
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);
        
        
        ProductIO.add(product);
        Console.displayLine("\n" + description
                + " was added to the database.\n");
        
    }

}
