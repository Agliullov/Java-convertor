package ru.diti.converter.repository;

import ru.diti.converter.enums.Currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyRepository {
	private static CurrencyRepository ourInstance = new CurrencyRepository();
	private BufferedReader bufferedReader;

	public static CurrencyRepository getInstance() {
		return ourInstance;
	}

	public CurrencyRepository() {

	}

	public BigDecimal getCoef(Currency from, Currency to) throws IOException {
		if (from == to) return BigDecimal.ONE;

		bufferedReader = new BufferedReader(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("currency_pairs.txt")));
		BigDecimal first = null;
		BigDecimal second = null;
		String currencyPair;
		while ((currencyPair = bufferedReader.readLine()) != null) {
			String[] splitedCurrency = currencyPair.split(" ");
				if (from.name() == "USD") {
				if (splitedCurrency[1].equals(to.name())) {
					return new BigDecimal(splitedCurrency[2]);
				}
			} else if (from == to) {
				if (splitedCurrency[1].equals(from.name())) {
					return BigDecimal.ONE.divide(new BigDecimal(splitedCurrency[2]));
				}
			}
			if (splitedCurrency[1].equals(from.name())) {
				second = new BigDecimal(splitedCurrency[2]);
			}
			if (splitedCurrency[1].equals(to.name())) {
				first = new BigDecimal(splitedCurrency[2]);
			}
			if (first != null && second != null) {
				break;
			}
		}
		return first.divide(second, 10, RoundingMode.HALF_EVEN);
	}

	public void close() throws Exception{
		bufferedReader.close();
	}
}