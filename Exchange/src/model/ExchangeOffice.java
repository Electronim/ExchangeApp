package model;

import service.CSVService;
import service.CurrencyService;
import service.TransactionService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExchangeOffice {
    private static String name;
    private static String country;
    private static String address;
    private static String phoneNumber;
    private static Map<Currency, Double> quantity;

    private static ExchangeOffice ourInstance = new ExchangeOffice();

    public static ExchangeOffice getInstance() {
        return ourInstance;
    }

    {
        try {
            List<List<String>> dataCSVCurrency, dataCSVOffice;

            dataCSVCurrency = CSVService.getInstance().readCSVData("/home/stl_man/Desktop/Fac/JAVAProjects/Exchange/src/Files/", "currencyInfo.csv");
            dataCSVOffice = CSVService.getInstance().readCSVData("/home/stl_man/Desktop/Fac/JAVAProjects/Exchange/src/Files/", "exchangeOfficeInfo.csv");
            dataCSVCurrency.remove(0);
            dataCSVOffice.remove(0);

            ExchangeOffice.name = dataCSVOffice.get(0).get(0);
            ExchangeOffice.country = dataCSVOffice.get(0).get(1);
            ExchangeOffice.address  = dataCSVOffice.get(0).get(2);
            ExchangeOffice.phoneNumber = dataCSVOffice.get(0).get(3);

            TransactionService TS = TransactionService.getInstance();
            CurrencyService CS = CurrencyService.getInstance();
            quantity = TS.getMappedCurrency();

            for (List<String> data: dataCSVCurrency) {
                double quan = Double.parseDouble(data.get(3));
                String code = data.get(1);

                quantity.put(CS.getCurrencyByCode(code), quan);
            }

        } catch(IOException ex) {
            throw new Error(ex);
        }
    }

    private ExchangeOffice() {
    }

    public void addCurrencyQuantity(Currency currency, double quan) {
        double prev = quantity.get(currency);
        prev += quan;
        quantity.put(currency, prev);
    }

    public void subtractCurrencyQuantity(Currency currency, double quan) {
        double prev = quantity.get(currency);
        prev -= quan;
        quantity.put(currency, prev);
    }

    public String getName() {
        return name;
    }

    public static void setName(String name) {
        ExchangeOffice.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        ExchangeOffice.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        ExchangeOffice.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        ExchangeOffice.phoneNumber = phoneNumber;
    }

    public Map<Currency, Double> getQuantity() {
        return quantity;
    }

    public void setQuantity(Map<Currency, Double> quantity) {
        ExchangeOffice.quantity = quantity;
    }
}
