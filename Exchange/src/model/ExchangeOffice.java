package model;

import service.CurrencyService;
import service.TransactionService;

import java.util.Map;

public class ExchangeOffice {
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    private Map<Currency, Double> quantity;

    private static ExchangeOffice ourInstance = new ExchangeOffice();

    public static ExchangeOffice getInstance() {
        return ourInstance;
    }

    private ExchangeOffice() {
        this.name = "Exchange SRL";
        this.country = "Romania";
        this.address = "str. Splaiul Independentei 204";
        this.phoneNumber = "+40732671236";

        TransactionService TS = TransactionService.getInstance();
        CurrencyService CS = CurrencyService.getInstance();
        quantity = TS.getMappedCurrency();

        quantity.put(CS.getCurrencyByCode("EUR"), 1000.0);
        quantity.put(CS.getCurrencyByCode("USD"), 1000.0);
        quantity.put(CS.getCurrencyByCode("GBP"), 1000.0);
        quantity.put(CS.getCurrencyByCode("RON"), 10000.0);
        quantity.put(CS.getCurrencyByCode("MDL"), 20000.0);
        quantity.put(CS.getCurrencyByCode("RUB"), 80000.0);
        quantity.put(CS.getCurrencyByCode("CHF"), 1000.0);
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

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Map<Currency, Double> getQuantity() {
        return quantity;
    }

    public void setQuantity(Map<Currency, Double> quantity) {
        this.quantity = quantity;
    }
}
