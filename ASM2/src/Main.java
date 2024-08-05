import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class product {
    private String code;
    private String name;
    private float price;

    public product(String code, String name, float price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

public class Main {

    static List<Product> productList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select function:");
            System.out.println("1. Enter new product information");
            System.out.println("2. Print product list");
            System.out.println("3. Delete products by code");
            System.out.println("4. Sort products by price in descending order");
            System.out.println("5. Search for products by product code or product name");
            System.out.println("6. Search for products with prices >= x");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    input();
                    break;
                case 2:
                    output();
                    break;
                case 3:
                    System.out.println("Enter the product code to delete:");
                    String code = scanner.nextLine();
                    removeByCode(code);
                    break;
                case 4:
                    sortByPriceDesc();
                    break;
                case 5:
                    System.out.println("Enter the product code or product name to search:");
                    String keyword = scanner.nextLine();
                    Product product = findByCodeOrName(keyword);
                    if (product != null) {
                        System.out.println(product.toString());
                    } else {
                        System.out.println("No products found");
                    }
                    break;
                case 6:
                    System.out.println("Enter price x:");
                    float x = scanner.nextFloat();
                    List<Product> filteredProducts = filterByPrice(x);
                    if (!filteredProducts.isEmpty()) {
                        for (Product p : filteredProducts) {
                            System.out.println(p.toString());
                        }
                    } else {
                        System.out.println("No products with prices found >= " + x);
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    public static void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product information:");
        System.out.print("Product code: ");
        String code = scanner.nextLine();
        System.out.print("Product's name: ");
        String name = scanner.nextLine();
        System.out.print("Product price: ");
        float price = scanner.nextFloat();
        scanner.nextLine();  // Consume newline

        Product product = new Product(code, name, price);
        productList.add(product);
        System.out.println("Sản phẩm đã được thêm.");
    }

    public static void output() {
        if (productList.isEmpty()) {
            System.out.println("Product list is empty.");
        } else {
            for (Product product : productList) {
                System.out.println(product.toString());
            }
        }
    }

    public static void removeByCode(String code) {
        Product productToRemove = null;
        for (Product product : productList) {
            if (product.getCode().equalsIgnoreCase(code)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            productList.remove(productToRemove);
            System.out.println("Product has been removed.");
        } else {
            System.out.println("No products found with code: " + code);
        }
    }

    public static void sortByPriceDesc() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Float.compare(p2.getPrice(), p1.getPrice());
            }
        });
        System.out.println("The product list has been sorted by price in descending order.");
    }

    public static Product findByCodeOrName(String keyword) {
        for (Product product : productList) {
            if (product.getCode().equalsIgnoreCase(keyword) || product.getName().equalsIgnoreCase(keyword)) {
                return product;
            }
        }
        return null;
    }

    public static List<Product> filterByPrice(float price) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getPrice() >= price) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
