package tax.calculation.helpers.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tax.calculation.exceptions.DataParsingException;
import tax.calculation.helpers.ProductsDataParser;
import tax.calculation.pojos.Product;

@Service
@Slf4j
public class ProductsDataParserImpl implements ProductsDataParser {

	@Override
	public Map<Integer, Product> parseData(List<List<String>> fileData) throws DataParsingException {

		log.info("Parsing file products data");

		if (CollectionUtils.isEmpty(fileData)) {
			log.error("No data was loaded from file");
			throw new DataParsingException("No data was loaded from file");
		}
		if (CollectionUtils.isEmpty(fileData.get(0)) || fileData.get(0).size() < 3) {
			log.error("Data loaded from file is corrupted");
			throw new DataParsingException("Data loaded from file is corrupted");
		}

		Map<Integer, Product> products = new HashMap<Integer, Product>();

		for (List<String> productData : fileData) {
			Product product = new Product();
			product.setLabel(productData.get(1));
			product.setType(productData.get(2));

			products.put(Integer.parseInt(productData.get(0)), product);
		}

		return products;
	}

}
