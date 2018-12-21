package ru.diti.converter.service;

import ru.diti.converter.enums.Currency;
import ru.diti.converter.repository.CurrencyRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConverterService {
    private  CurrencyRepository repository = new CurrencyRepository();
    public String convert(BigDecimal sum, Currency from, Currency to) throws IOException
    {
        return sum.multiply(repository.getCoef(from, to)).setScale(2, RoundingMode.HALF_UP).toString();
    }
}
