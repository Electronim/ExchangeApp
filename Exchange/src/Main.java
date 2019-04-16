import model.*;
import service.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);

        // --------------------- access to services -----------------------
        UserService US = UserService.getInstance();
        CurrencyService CS = CurrencyService.getInstance();
        TransactionService TS = TransactionService.getInstance();
        CurrencyHistoryService CHS = CurrencyHistoryService.getInstance();
        ExchangeRateService ERS = ExchangeRateService.getInstance();
        ExchangeOffice exchangeOffice = ExchangeOffice.getInstance();
        // ---------------------------------------------------------------

        // -------------------- find available currencies ----------------
        System.out.println("The current available currencies:");
        List <Currency> currencies = CurrencyService.getListOfCurrencies();
        for (Currency currency: currencies) {
            System.out.printf("%s (%s, %s)\n", currency.getCurrencyName(), currency.getCurrencyCode(), currency.getCurrencySymbol());
        }
        // ---------------------------------------------------------------

        // -------------------- exchange rates ------------------------
        ERS.updateExchangeRates();
        List <ExchangeRate> exchangeRates = ExchangeRateService.getListOfExchangeRates();
        System.out.println("\nCurrent Exchange Rates:");
        for (ExchangeRate exchangeRate: exchangeRates) {
            System.out.printf("%s %.6f\n", exchangeRate.getCurrencyCode(), exchangeRate.getRate());
        }
        // ------------------------------------------------------------

        // ------------------- transactions ---------------------------
        System.out.println("Number of transactions to be made: ");
        int nrOfTransactions = Integer.parseInt(obj.nextLine());
        while (nrOfTransactions > 0) {
            TS.makeNewTransaction();
            nrOfTransactions--;
        }

        List <Transaction > transactions = TransactionService.getListOfTransactions();
        System.out.println("\nTotal number of transactions made: " + transactions.size());
        for (Transaction transaction: transactions) {
            if (!transaction.getDone()){
                System.out.printf("The exchange of %.6f %s in %s failed!\n", transaction.getFromSum(), transaction.getFromCurrency().getCurrencyCode(), transaction.getToCurrency().getCurrencyCode());
            } else {
                System.out.printf("Transaction accepted: %.6f %s to %.6f %s\n", transaction.getFromSum(), transaction.getFromCurrency().getCurrencyCode(),
                        transaction.getToSum(), transaction.getToCurrency().getCurrencyCode());
            }
        }
        // ------------------------------------------------------------

        // -------------- search for a specific user -----------------
        System.out.println("\nGive the username of the user to search for ");
        String username = obj.nextLine();

        System.out.println(username);

        User user = US.getUserByUsername(username);

        if (user == null){
            System.out.println("There is no such user.");
        }
        else {
            System.out.println("Last name of the searched user: " + user.getLastName());
            System.out.println(user.getLastName() + "'s Transactions:");
            List <Transaction> transactionList = user.getListOfTransactions();
            for (int i = 0; i < transactionList.size(); i++) {
                Transaction transaction = transactionList.get(i);
                if (!transaction.getDone()){
                    System.out.printf("%d. The exchange of %.6f %s in %s failed!\n", i + 1, transaction.getFromSum(), transaction.getFromCurrency().getCurrencyCode(), transaction.getToCurrency().getCurrencyCode());
                } else {
                    System.out.printf("Transaction accepted: %.6f %s to %.6f %s\n", transaction.getFromSum(), transaction.getFromCurrency().getCurrencyCode(),
                            transaction.getToSum(), transaction.getToCurrency().getCurrencyCode());
                }
            }
        }
        // ------------------------------------------------------------

        // ----------- get info about currency sale and buy -----------
        System.out.println("\nInfo about currency sale: ");
        Map <Currency, Double> salesInfo = TS.getCurrencySaleInfo();

        for (Map.Entry<Currency, Double> entry: salesInfo.entrySet()) {
            System.out.printf("%s <- %.6f\n", entry.getKey().getCurrencyCode(), entry.getValue());
        }

        System.out.println("\nInfo about currency buy: ");
        Map <Currency, Double> buysInfo = TS.getCurrencyBuyInfo();

        for (Map.Entry<Currency, Double> entry: buysInfo.entrySet()) {
            System.out.printf("%s <- %.6f\n", entry.getKey().getCurrencyCode(), entry.getValue());
        }
        // ------------------------------------------------------------

        // ---- get info about currency left in the exchange office ----
        System.out.println("\nInfo about the currency quantity left in the exchange office:");
        Map <Currency, Double> quantity = exchangeOffice.getQuantity();
        for (Map.Entry<Currency, Double> entry: quantity.entrySet()) {
            System.out.printf("%s <- %.6f\n", entry.getKey().getCurrencyCode(), entry.getValue());
        }
        // -------------------------------------------------------------
    }
}
