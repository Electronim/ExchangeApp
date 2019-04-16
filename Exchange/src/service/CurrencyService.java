package service;

import model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyService {
    private static List <Currency> listOfCurrencies = new ArrayList <>();

    private static CurrencyService ourInstance = new CurrencyService();

    public static CurrencyService getInstance() {
        return ourInstance;
    }

    private CurrencyService() {
        listOfCurrencies.add(new Currency("Euro", "EUR", "€"));
        listOfCurrencies.add(new Currency("United States Dollar", "USD", "$"));
        listOfCurrencies.add(new Currency("British Pound", "GBP", "£"));
        listOfCurrencies.add(new Currency("Romanian leu", "RON", "lei"));
        listOfCurrencies.add(new Currency("Moldovan leu", "MDL", "L"));
        listOfCurrencies.add(new Currency("Russian ruble", "RUB", "\u20BD"));
        listOfCurrencies.add(new Currency("Swiss franc", "CHF", "Fr"));
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
