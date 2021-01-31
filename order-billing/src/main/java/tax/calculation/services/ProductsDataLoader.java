package tax.calculation.services;

import java.util.Map;

import tax.calculation.exceptions.DataLoadingException;
import tax.calculation.pojos.Product;

public interface ProductsDataLoader {

	Map<Integer, Product> loadProductsData() throws DataLoadingException;

}
