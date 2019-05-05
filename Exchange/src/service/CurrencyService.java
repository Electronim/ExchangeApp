package service;

import model.Currency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyService {
    private static List<Currency> listOfCurrencies = new ArrayList<>();

    private static CurrencyService ourInstance = new CurrencyService();

    public static CurrencyService getInstance() {
        return ourInstance;
    }

    static {
        try {
            List<List<String>> dataCSV;
            dataCSV = CSVService.getInstance().readCSVData("/home/stl_man/Desktop/Fac/JAVAProjects/Exchange/src/Files/", "currencyInfo.csv");
            dataCSV.remove(0);
            for (List<String> data: dataCSV) {
                String currencyName = data.get(0);
                String currencyCode = data.get(1);
                String currencySymbol = data.get(2);
                listOfCurrencies.add(new Currency(currencyName, currencyCode, currencySymbol));
            }

        } catch(IOException ex) {
            throw new Error(ex);
        }
    }

    private CurrencyService() {
    }

    public static List<Currency> getListOfCurrencies() {
        return listOfCurrencies;
    }

    public Currency getCurrencyByName(String name) {
        for (Currency currency: listOfCurrencies) {
            if (currency.getCurrencyName().equals(name)) {
                return currency;
            }
        }

        return null;
    }

    public Currency getCurrencyByCode(String code) {
        for (Currency currency: listOfCurrencies) {
            if (currency.getCurrencyCode().equals(code)) {
                return currency;
            }
        }

        return null;
    }

    public Currency getCurrencyBySymbol(String symbol) {
        for (Currency currency: listOfCurrencies) {
            if (currency.getCurrencySymbol().equals(symbol)) {
                return currency;
            }
        }

        return null;
    }
}
