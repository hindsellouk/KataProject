package tax.calculation.helpers;

import java.util.List;
import java.util.Map;

import tax.calculation.exceptions.DataParsingException;
import tax.calculation.pojos.Product;

public interface ProductsDataParser {

	Map<Integer, Product> parseData(List<List<String>> fileData) throws DataParsingException;

}
