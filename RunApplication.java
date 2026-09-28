import java.util.*;

public class RunApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<RetailOrder> orders = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Capture Order ---");
            try {
                System.out.print("Enter order ID: ");
                String id = sc.nextLine();
                System.out.print("Enter customer name: ");
                String name = sc.nextLine();
                System.out.print("Enter order total: ");
                double total = Double.parseDouble(sc.nextLine());
                System.out.print("Enter order status (PENDING, SHIPPED, DELIVERED, RETURNED): ");
                OrderStatus status = OrderStatus.valueOf(sc.nextLine().toUpperCase());

                RetailOrder order = new RetailOrder(id, name, total, status);
                orders.add(order);
            } catch (InvalidOrderException e) {
                System.out.println("ERROR: " + e.getMessage());
                continue; // re-prompt same order
            } catch (Exception e) {
                System.out.println("Invalid input, try again");
                continue;
            }

            System.out.print("Add another order? (y/n): ");
            if (!sc.nextLine().equalsIgnoreCase("y")) break;
        }

        // Sort descending by total
        Collections.sort(orders, Comparator.comparingDouble(RetailOrder::getOrderTotal).reversed());

        System.out.println("\n===================================================");
        System.out.println("SAVANNAMART ORDER REPORT (SORTED BY TOTAL, HIGHEST FIRST)");
        System.out.println("===================================================");
        double totalPoints = 0;
        for (RetailOrder o : orders) {
            o.printOrderDetails();
            totalPoints += o.calculateLoyaltyPoints();
        }
        System.out.println("---------------------------------------------------");
        System.out.println("TOTAL LOYALTY POINTS EARNED ACROSS ALL ORDERS: " + (int)totalPoints);
        System.out.println("===================================================");

        // Save for Q3 - keep the list for next question
        // For Q3 extension, add method calls below
        public static double calculateCompoundedBonus(int orderCount) {
    // Base case
    if (orderCount <= 1) {
        return 50;
    }
    // Recursive case: 1.5 * previous
    return calculateCompoundedBonus(orderCount - 1) * 1.5;
}

// And in main after the total points part, add:
HashMap<String, Integer> customerCount = new HashMap<>();
for (RetailOrder o : orders) {
    customerCount.put(o.getCustomerName(),
        customerCount.getOrDefault(o.getCustomerName(), 0) + 1);
}

System.out.println("\n===================================================");
System.out.println("REPEAT CUSTOMER BONUS REPORT");
System.out.println("===================================================");
for (Map.Entry<String, Integer> entry : customerCount.entrySet()) {
    if (entry.getValue() > 1) {
        int count = entry.getValue();
        double bonus = calculateCompoundedBonus(count);
        long rounded = Math.round(bonus);
        System.out.println("CUSTOMER: " + entry.getKey() + " ORDERS PLACED: " + count + " COMPOUNDED BONUS: " + rounded + " points");
    }
}
System.out.println("===================================================");
    }
}