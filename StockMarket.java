package com.mycompany.stockmarket;

import java.util.*;

class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return symbol + " - $" + price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance = 10000.0; // Initial virtual money

    public void buyStock(String symbol, int quantity, double price) {
        double cost = price * quantity;
        if (balance >= cost) {
            balance -= cost;
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void sellStock(String symbol, int quantity, double price) {
        if (holdings.getOrDefault(symbol, 0) >= quantity) {
            balance += price * quantity;
            holdings.put(symbol, holdings.get(symbol) - quantity);
            System.out.println("Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Not enough shares to sell!");
        }
    }

    public void viewPortfolio() {
        System.out.println("\nPortfolio:");
        holdings.forEach((symbol, quantity) -> System.out.println(symbol + ": " + quantity + " shares"));
        System.out.println("Balance: $" + balance);
    }
}

public class StockMarket {
    static Map<String, Stock> stockMarket = new HashMap<>();
    static Portfolio portfolio = new Portfolio();

    public static void main(String[] args) {
        stockMarket.put("AAPL", new Stock("AAPL", 150.0));
        stockMarket.put("GOOGL", new Stock("GOOGL", 2800.0));
        stockMarket.put("TSLA", new Stock("TSLA", 700.0));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStock Trading System");
            System.out.println("1. View Market Prices");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewMarket();
                case 2 -> buyStock(scanner);
                case 3 -> sellStock(scanner);
                case 4 -> portfolio.viewPortfolio();
                case 5 -> {
                    System.out.println("Exiting. Happy Trading!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewMarket() {
        System.out.println("\nStock Market:");
        for (Stock stock : stockMarket.values()) {
            System.out.println(stock);
        }
    }

    private static void buyStock(Scanner scanner) {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().toUpperCase();
        if (stockMarket.containsKey(symbol)) {
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            portfolio.buyStock(symbol, quantity, stockMarket.get(symbol).price);
        } else {
            System.out.println("Stock not found!");
        }
    }

    private static void sellStock(Scanner scanner) {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().toUpperCase();
        if (stockMarket.containsKey(symbol)) {
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            portfolio.sellStock(symbol, quantity, stockMarket.get(symbol).price);
        } else {
            System.out.println("Stock not found!");
        }
    }
}
