package tax.calculation.services.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tax.calculation.configuration.properties.Taxes;
import tax.calculation.exceptions.TaxCalculationException;
import tax.calculation.pojos.Bill;
import tax.calculation.pojos.Order;
import tax.calculation.pojos.Product;
import tax.calculation.services.OrderBilling;

@Service
@Slf4j
public class OrderBillingImpl implements OrderBilling {

	private Taxes taxes;

	public OrderBillingImpl(Taxes taxes) {
		super();
		this.taxes = taxes;
	}

	@Override
	public Bill pricesCalculation(Order order) throws TaxCalculationException {

		log.info("Calculating prices");

		if (order == null || CollectionUtils.isEmpty(order.getProducts())) {
			log.error("Order is empty");
			throw new TaxCalculationException("Order is empty");
		}

		Bill bill = new Bill();
		double totalPrice = 0, totalTaxes = 0;
		for (Product product : order.getProducts()) {

			double priceHT = product.getPriceHT() * product.getQuantity();
			double taxValue = priceHT * taxes.getTaxPerProductType().get(product.getType());
			double importationTaxValue = product.isImported() ? priceHT * taxes.getImportationTax() : 0;
			double roundedTaxValue = roundUp(taxValue) + roundUp(importationTaxValue);

			double priceTTC = roundUp(priceHT + roundedTaxValue);

			product.setPriceTTC(priceTTC);

			totalPrice += product.getPriceTTC();
			totalTaxes += roundedTaxValue;

		}

		bill.setOrder(order);
		bill.setTotalPrice(roundUp(totalPrice));
		bill.setTotalTaxes(roundUp(totalTaxes));

		return bill;

	}

	private double roundUp(double value) {
		return Math.round(value * 20) / 20.0;

	}
}
