package service;

import model.Currency;
import model.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRateService {
    private static List <ExchangeRate> listOfExchangeRates = new ArrayList<>();

    private static ExchangeRateService ourInstance = new ExchangeRateService();

    public static ExchangeRateService getInstance() {
        return ourInstance;
    }

    private ExchangeRateService() {
        CurrencyService CS = CurrencyService.getInstance();
        List <Currency> currencies = CurrencyService.getListOfCurrencies();

        for (Currency currency: currencies) {
            ExchangeRate exchangeRate = new ExchangeRate(
                    currency.getCurrencyName(),
                    currency.getCurrencyCode(),
                    currency.getCurrencySymbol(),
                    0.000000);

            listOfExchangeRates.add(exchangeRate);
        }

    }

    public static List<ExchangeRate> getListOfExchangeRates() {
        return listOfExchangeRates;
    }

    public static void setListOfExchangeRates(List<ExchangeRate> listOfExchangeRates) {
        ExchangeRateService.listOfExchangeRates = listOfExchangeRates;
    }

    public void updateExchangeRates() {
        for (ExchangeRate exchangeRate: listOfExchangeRates) {
            switch (exchangeRate.getCurrencyCode()) {
                case "EUR":
                    exchangeRate.setRate(0.890452);
                    break;
                case "USD":
                    exchangeRate.setRate(1.000000);
                    break;
                case "GBP":
                    exchangeRate.setRate(0.765682);
                    break;
                case "RON":
                    exchangeRate.setRate(4.243342);
                    break;
                case "MDL":
                    exchangeRate.setRate(17.374685);
                    break;
                case "RUB":
                    exchangeRate.setRate(65.469646);
                    break;
                case "CHF":
                    exchangeRate.setRate(0.994877);
                    break;
                default:
                    exchangeRate.setRate(-1.000000);
            }
        }
    }

    public ExchangeRate getCurrencyByName(String name) {
        for (ExchangeRate exchangeRate: listOfExchangeRates) {
            if (exchangeRate.getCurrencyName().equals(name)) {
                return exchangeRate;
            }
        }

        return null;
    }

    public ExchangeRate getCurrencyByCode(String code) {
        for (ExchangeRate exchangeRate: listOfExchangeRates) {
            if (exchangeRate.getCurrencyCode().equals(code)) {
                return exchangeRate;
            }
        }

        return null;
    }

    public ExchangeRate getCurrencyBySymbol(String symbol) {
        for (ExchangeRate exchangeRate: listOfExchangeRates) {
            if (exchangeRate.getCurrencySymbol().equals(symbol)) {
                return exchangeRate;
            }
        }

        return null;
    }

    public double exchange(String fromCurrencyCode, String toCurrencyCode, double sum) {
        ExchangeRate from = getCurrencyByCode(fromCurrencyCode);
        ExchangeRate to = getCurrencyByCode(toCurrencyCode);

        double sumToDefault = from.getInverseRate() * sum;
        return to.getRate() * sumToDefault;
    }
}
