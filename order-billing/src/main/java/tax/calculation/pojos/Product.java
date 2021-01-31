package tax.calculation.pojos;

import lombok.Data;

@Data
public class Product {

	private String label;
	private int quantity;
	private double priceHT;
	private double priceTTC;
	private String type;
	private boolean isImported;

}
