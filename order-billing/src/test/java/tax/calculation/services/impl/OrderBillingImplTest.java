package tax.calculation.services.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import tax.calculation.configuration.properties.Taxes;
import tax.calculation.exceptions.TaxCalculationException;
import tax.calculation.pojos.Bill;
import tax.calculation.pojos.Order;
import tax.calculation.pojos.Product;

@RunWith(SpringRunner.class)
public class OrderBillingImplTest {

	private Taxes taxes;

	private OrderBillingImpl orderBillingImpl;

	@Before
	public void setUp() {
		taxes = Mockito.mock(Taxes.class);

		orderBillingImpl = new OrderBillingImpl(taxes);
	}

	@Test(expected = TaxCalculationException.class)
	public void should_return_TaxCalculationException_when_order_is_null() throws TaxCalculationException {
		orderBillingImpl.pricesCalculation(null);
	}

	@Test(expected = TaxCalculationException.class)
	public void should_return_TaxCalculationException_when_order_products_are_null() throws TaxCalculationException {
		orderBillingImpl.pricesCalculation(new Order());
	}

	@Test(expected = TaxCalculationException.class)
	public void should_return_TaxCalculationException_when_order_does_not_contain_any_products()
			throws TaxCalculationException {
		Order order = new Order();
		order.setProducts(Collections.EMPTY_LIST);
		orderBillingImpl.pricesCalculation(order);
	}

	@Test
	public void should_do_calcutalion_as_expected_for_first_order() throws TaxCalculationException {
		Product product1 = new Product();
		product1.setLabel("livre");
		product1.setPriceHT(12.49);
		product1.setType("BOOKS");
		product1.setQuantity(2);

		Product product2 = new Product();
		product2.setLabel("CD musical");
		product2.setPriceHT(14.99);
		product2.setType("OTHERS");
		product2.setQuantity(1);

		Product product3 = new Product();
		product3.setLabel("livre");
		product3.setPriceHT(0.85);
		product3.setType("ESSENTIALS");
		product3.setQuantity(3);

		Order order = new Order();
		order.setProducts(Arrays.asList(product1, product2, product3));

		Mockito.when(taxes.getImportationTax()).thenReturn(0.05f);
		Mockito.when(taxes.getTaxPerProductType()).thenReturn(new HashMap<String, Float>() {
			{
				put("ESSENTIALS", 0.00f);
				put("BOOKS", 0.10f);
				put("OTHERS", 0.20f);

			}
		});

		Bill bill = orderBillingImpl.pricesCalculation(order);

		Assert.assertTrue(bill.getTotalPrice() == 48.05);
		Assert.assertTrue(bill.getTotalTaxes() == 5.5);
		Assert.assertTrue(bill.getOrder().getProducts().get(0).getPriceTTC() == 27.5);
		Assert.assertTrue(bill.getOrder().getProducts().get(1).getPriceTTC() == 18);
		Assert.assertTrue(bill.getOrder().getProducts().get(2).getPriceTTC() == 2.55);

	}

	@Test
	public void should_do_calcutalion_as_expected_for_second_order() throws TaxCalculationException {
		Product product1 = new Product();
		product1.setLabel("boite de chocolat");
		product1.setPriceHT(10);
		product1.setType("ESSENTIALS");
		product1.setQuantity(2);
		product1.setImported(true);

		Product product2 = new Product();
		product2.setLabel("flacons de parfum");
		product2.setPriceHT(47.50);
		product2.setType("OTHERS");
		product2.setQuantity(3);
		product2.setImported(true);

		Order order = new Order();
		order.setProducts(Arrays.asList(product1, product2));

		Mockito.when(taxes.getImportationTax()).thenReturn(0.05f);
		Mockito.when(taxes.getTaxPerProductType()).thenReturn(new HashMap<String, Float>() {
			{
				put("ESSENTIALS", 0.00f);
				put("BOOKS", 0.10f);
				put("OTHERS", 0.20f);

			}
		});

		Bill bill = orderBillingImpl.pricesCalculation(order);

		Assert.assertTrue(bill.getTotalPrice() == 199.15);
		Assert.assertTrue(bill.getTotalTaxes() == 36.65);
		Assert.assertTrue(bill.getOrder().getProducts().get(0).getPriceTTC() == 21);
		Assert.assertTrue(bill.getOrder().getProducts().get(1).getPriceTTC() == 178.15);

	}

	@Test
	public void should_do_calcutalion_as_expected_for_third_order() throws TaxCalculationException {
		Product product1 = new Product();
		product1.setLabel("flacons de parfun");
		product1.setPriceHT(27.99);
		product1.setType("OTHERS");
		product1.setQuantity(2);
		product1.setImported(true);

		Product product2 = new Product();
		product2.setLabel("flacon de parfum");
		product2.setPriceHT(18.99);
		product2.setType("OTHERS");
		product2.setQuantity(1);

		Product product3 = new Product();
		product3.setLabel("boites de pilules à migaine");
		product3.setPriceHT(9.75);
		product3.setType("ESSENTIALS");
		product3.setQuantity(3);

		Product product4 = new Product();
		product4.setLabel("boites de chocolat");
		product4.setPriceHT(11.25);
		product4.setType("ESSENTIALS");
		product4.setQuantity(2);
		product4.setImported(true);

		Order order = new Order();
		order.setProducts(Arrays.asList(product1, product2, product3, product4));

		Mockito.when(taxes.getImportationTax()).thenReturn(0.05f);
		Mockito.when(taxes.getTaxPerProductType()).thenReturn(new HashMap<String, Float>() {
			{
				put("ESSENTIALS", 0.00f);
				put("BOOKS", 0.10f);
				put("OTHERS", 0.20f);

			}
		});

		Bill bill = orderBillingImpl.pricesCalculation(order);

		Assert.assertTrue(bill.getTotalPrice() == 145.7);
		Assert.assertTrue(bill.getTotalTaxes() == 18.95);
		Assert.assertTrue(bill.getOrder().getProducts().get(0).getPriceTTC() == 70);
		Assert.assertTrue(bill.getOrder().getProducts().get(1).getPriceTTC() == 22.8);
		Assert.assertTrue(bill.getOrder().getProducts().get(2).getPriceTTC() == 29.25);
		Assert.assertTrue(bill.getOrder().getProducts().get(3).getPriceTTC() == 23.65);

	}

}
