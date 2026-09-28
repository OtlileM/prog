import java.util.Scanner;

public class SalesAnalytics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] stores = {"Cape Town", "Durban", "Johannesburg", "Bloemfontein"};
        String[] categories = {"Electronics", "Clothing", "Groceries"};

        int[][] sales = new int[4][3];

        // 1. Capture into 2D array
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter " + categories[j] + " sales for " + stores[i] + ": ");
                sales[i][j] = sc.nextInt();
            }
        }

        int[] storeTotals = new int[4];
        int[] categoryTotals = new int[3];
        int grandTotal = 0;

        // 2. Row totals + 3. Column totals + 4. Grand total
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                storeTotals[i] += sales[i][j];
                categoryTotals[j] += sales[i][j];
                grandTotal += sales[i][j];
            }
        }

        // 5. Highest store
        int maxIndex = 0;
        for (int i = 1; i < storeTotals.length; i++) {
            if (storeTotals[i] > storeTotals[maxIndex]) maxIndex = i;
        }

        // 6. Lowest category
        int minCatIndex = 0;
        for (int j = 1; j < categoryTotals.length; j++) {
            if (categoryTotals[j] < categoryTotals[minCatIndex]) minCatIndex = j;
        }

        // Report
        System.out.println("===================================================");
        System.out.println("SAVANNAMART SALES ANALYTICS REPORT");
        System.out.println("===================================================");
        System.out.printf("%-15s %-12s %-10s %-10s %s\n", "", "ELECTRONICS", "CLOTHING", "GROCERIES", "TOTAL");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%-15s %-12d %-10d %-10d %d\n", stores[i], sales[i][0], sales[i][1], sales[i][2], storeTotals[i]);
        }
        System.out.println("---------------------------------------------------");
        System.out.printf("CATEGORY TOTALS %-12d %-10d %d\n", categoryTotals[0], categoryTotals[1], categoryTotals[2]);
        System.out.println("GRAND TOTAL SALES: " + grandTotal);
        System.out.println("\nSTORE WITH THE HIGHEST TOTAL SALES: " + stores[maxIndex] + " (" + storeTotals[maxIndex] + ")");
        System.out.println("CATEGORY WITH THE LOWEST TOTAL SALES: " + categories[minCatIndex] + " (" + categoryTotals[minCatIndex] + ")");

        // 7. Self-implemented Bubble Sort for ranking
        // We must sort copies so we don't lose original
        String[] rankedStores = stores.clone();
        int[] rankedTotals = storeTotals.clone();

        for (int i = 0; i < rankedTotals.length - 1; i++) {
            for (int j = 0; j < rankedTotals.length - 1 - i; j++) {
                if (rankedTotals[j] < rankedTotals[j+1]) {
                    // swap totals
                    int tempTotal = rankedTotals[j];
                    rankedTotals[j] = rankedTotals[j+1];
                    rankedTotals[j+1] = tempTotal;
                    // swap names too
                    String tempStore = rankedStores[j];
                    rankedStores[j] = rankedStores[j+1];
                    rankedStores[j+1] = tempStore;
                }
            }
        }

        System.out.println("---------------------------------------------------");
        System.out.println("STORE RANKING (HIGHEST TO LOWEST SALES)");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < rankedStores.length; i++) {
            System.out.println((i+1) + ". " + rankedStores[i] + " " + rankedTotals[i]);
        }
        System.out.println("===================================================");
    }
}
