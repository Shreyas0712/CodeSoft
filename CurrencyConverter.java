import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] currencies = {"USD", "EUR", "INR", "JPY"};
        double[][] rates = {
            {1.0, 0.85, 83.0, 110.0},  // from USD
            {1.18, 1.0, 98.0, 130.0},  // from EUR
            {0.012, 0.010, 1.0, 1.55}, // from INR
            {0.0091, 0.0077, 0.64, 1.0} // from JPY
        };

        System.out.println("🌐 Currency Converter");
        System.out.println("Available Currencies:");
        for (int i = 0; i < currencies.length; i++) {
            System.out.println(i + 1 + ". " + currencies[i]);
        }

        System.out.print("Select base currency (1-4): ");
        int base = scanner.nextInt() - 1;

        System.out.print("Select target currency (1-4): ");
        int target = scanner.nextInt() - 1;

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        double rate = rates[base][target];
        double converted = amount * rate;

        System.out.printf("💱 Converted Amount: %.2f %s\n", converted, currencies[target]);

        scanner.close();
    }
}
