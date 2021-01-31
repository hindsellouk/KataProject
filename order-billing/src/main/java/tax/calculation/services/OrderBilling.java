package tax.calculation.services;

import tax.calculation.exceptions.TaxCalculationException;
import tax.calculation.pojos.Bill;
import tax.calculation.pojos.Order;

public interface OrderBilling {

	Bill pricesCalculation(Order order) throws TaxCalculationException;

}
