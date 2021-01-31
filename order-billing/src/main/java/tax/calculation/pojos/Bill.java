package tax.calculation.pojos;

import lombok.Data;

@Data
public class Bill {

	private Order order;
	private double totalTaxes;
	private double totalPrice;

}
