package tax.calculation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tax.calculation.pojos.Bill;
import tax.calculation.pojos.Order;
import tax.calculation.pojos.Product;
import tax.calculation.services.ProductsDataLoader;
import tax.calculation.services.impl.OrderBillingImpl;

@SpringBootApplication
public class OrderBillingApplication implements CommandLineRunner {

	private ProductsDataLoader productsDataLoader;
	private OrderBillingImpl orderBillingImpl;

	public OrderBillingApplication(ProductsDataLoader productsDataLoader, OrderBillingImpl orderBillingImpl) {
		super();
		this.productsDataLoader = productsDataLoader;
		this.orderBillingImpl = orderBillingImpl;
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderBillingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Map<Integer, Product> productsData = productsDataLoader.loadProductsData();
		System.out.println("\n\nProducts Data:");
		for (Entry<Integer, Product> entry : productsData.entrySet()) {
			System.out.println("Product id:" + entry.getKey() + " Product Label:" + entry.getValue().getLabel());
		}

		while (true) {

			System.out.println("\n\n");
			Scanner c = new Scanner(System.in);
			System.out.println("Enter the number of products");
			int numberOfProducts = Integer.parseInt(c.nextLine());

			List<Product> products = new ArrayList<Product>();
			for (int i = 0; i < numberOfProducts; i++) {
				System.out.println("Enter input in the following format (Product_id:Price:Quantity:Isimported)");
				String str[] = c.nextLine().split(":");
				Product product = new Product();
				product.setPriceHT(Double.parseDouble(str[1]));
				product.setQuantity(Integer.parseInt(str[2]));
				int productId = Integer.parseInt(str[0]);
				product.setLabel(productsData.get(productId).getLabel());
				product.setType(productsData.get(productId).getType());
				product.setImported(Boolean.parseBoolean(str[3]) ? true : false);
				products.add(product);
			}

			Order order = new Order();
			order.setProducts(products);

			Bill bill = orderBillingImpl.pricesCalculation(order);
			for (Product product : bill.getOrder().getProducts()) {
				System.out.println(product.getQuantity() + " " + product.getLabel() + " "
						+ (product.isImported() ? "importé" : " ") + " " + product.getPriceHT() + ": "
						+ product.getPriceTTC());
			}
			System.out.println("Montant des taxes: " + bill.getTotalTaxes());
			System.out.println("Total: " + bill.getTotalPrice());

		}

	}

}
