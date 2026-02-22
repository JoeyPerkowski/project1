import java.util.Scanner;

public class HomeRenov {

    //Paint room constants
    static final double DOOR_AREA = 21.0;
    static final double WINDOW_AREA = 12.0;

    static final double PAINT_COVERAGE = 350.0;
    static final double PAINT_PRICE = 34.99;
    static final double TAX_RATE = 0.06;

    //Flooring constants
    static final double LAMINATE_PRICE = 2.25;
    static final double LAMINATE_WASTE = 0.07;
    static final double HARDWOOD_PRICE = 5.50;
    static final double HARDWOOD_WASTE = 0.10;
    static final double TILE_PRICE = 3.90;
    static final double TILE_WASTE = 0.08;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalSubtotal = 0.0;
        int choice = 0;

        //Menu 1
        while (choice != 4) {
            System.out.println("=== Home Renovator ===");
            System.out.println("1) Paint Room");
            System.out.println("2) Flooring");
            System.out.println("3) View Totals");
            System.out.println("4) exit");
            System.out.println("Choice:");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("ERROR, please choose a valid input.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    totalSubtotal += handlePaintRoom(scanner);
                case 2:
                    totalSubtotal += handleFlooring(scanner);
                case 3:
                    displayTotals(totalSubtotal);
                case 4:
                    System.out.println("Exiting. Total Estimate: $" + String.format("%.2f", totalSubtotal * (1 + TAX_RATE)));
                    break;
                default:
                    System.out.println("Error, please choose a valid input.");
            }
        }
        scanner.close();
    }

    private static double handlePaintRoom(Scanner scanner) {
        System.out.println("Enter room length (ft):");
        double length = getValidDouble(scanner);
        System.out.println("Enter room width (ft):");
        double width = getValidDouble(scanner);
        System.out.println("Enter room height (ft):");
        double height = getValidDouble(scanner);
        System.out.println("Enter number of doors:");
        int doors = (int) getValidDouble(scanner);
        System.out.println("Enter number of windows:");
        int windows = (int) getValidDouble(scanner);

        double perimeter = 2 * (length + width);
        double wallArea = perimeter * height;
        double netArea = wallArea - (doors * DOOR_AREA) - (windows * WINDOW_AREA);
        double gallons = Math.ceil(netArea / PAINT_COVERAGE);
        double materialCost = gallons * PAINT_PRICE;
        double lineTotal = materialCost;

        System.out.printf("Net Paint Area: %.2f sq ft%n", netArea);
        System.out.printf("Gallons needed: %.0f%n", gallons);
        System.out.printf("Material Cost: $%.2f%n", materialCost);
        System.out.printf("Line Total: $%.2f%n", lineTotal);

        return lineTotal;
    }

    private static double handleFlooring(Scanner scanner) {
        System.out.println("Select Flooring Type:");
        System.out.println("1) Laminate ($2.25/sq ft, 7% waste)");
        System.out.println("2) Hardwood (5.50/sq ft, 10% waste)");
        System.out.println("3) Tile ($3.90/sq ft, 8% waste)");
        System.out.println("Choice:");
        int type = (int) getValidDouble(scanner);

        System.out.print("Enter room length (ft):");
        double length = getValidDouble(scanner);
        System.out.print("Enter room width (ft):");
        double width = getValidDouble(scanner);

        double area = length * width;
        double price = 0;
        double waste = 0;

        switch (type) {
            case 1:
                price = LAMINATE_PRICE;
                waste = LAMINATE_WASTE;
                break;
            case 2:
                price = HARDWOOD_PRICE;
                waste = HARDWOOD_WASTE;
                break;
            case 3:
                price = TILE_PRICE;
                waste = TILE_WASTE;
                break;
            default:
                System.out.println("Error, please choose a valid input");
                price = LAMINATE_PRICE;
                waste = LAMINATE_WASTE;

        }

        double adjustedArea = area * (1 + waste);
        double materialCost = adjustedArea * price;

        System.out.printf("Adjusted Area (with waste): %.2f sq ft%n", adjustedArea);
        System.out.printf("Price per sq ft: $%.2f%n", price);
        System.out.printf("Material Cost: $%.2f%n", materialCost);
        System.out.printf("Line Total: $%.2f5n", materialCost);

        return materialCost;
    }

    private static void displayTotals(double subtotal) {
        double tax = subtotal * TAX_RATE;
        double grandTotal = subtotal + tax;
        System.out.println("-- Current Totals --");
        System.out.printf("Subtotal: $5.2f%n", subtotal);
        System.out.printf("Tax (6%%):$%.2f%n", tax);
        System.out.printf("Grand Total: $%.2f%n", grandTotal);

    }

    private static double getValidDouble(Scanner scanner) {
        while (true) {
            if (scanner.hasNextDouble()) {
                double val = scanner.nextDouble();
                if (val >= 0) return val;
            }
            System.out.print("Invalid input. Please enter a positive number: ");
            scanner.next();
        }

    }
}
