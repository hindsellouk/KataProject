package tax.calculation.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tax.calculation.configuration.properties.ProductsDataSource;
import tax.calculation.exceptions.DataLoadingException;
import tax.calculation.exceptions.DataParsingException;
import tax.calculation.exceptions.FileReaderException;
import tax.calculation.helpers.FileReader;
import tax.calculation.helpers.ProductsDataParser;
import tax.calculation.pojos.Product;
import tax.calculation.services.ProductsDataLoader;

@Service
@Slf4j
public class ProductsDataLoaderImpl implements ProductsDataLoader {

	private ProductsDataSource productsDataSource;
	private FileReader fileReader;
	private ProductsDataParser productsDataParser;

	public ProductsDataLoaderImpl(ProductsDataSource productsDataSource, FileReader fileReader,
			ProductsDataParser productsDataParser) {
		super();
		this.productsDataSource = productsDataSource;
		this.fileReader = fileReader;
		this.productsDataParser = productsDataParser;
	}

	@Override
	public Map<Integer, Product> loadProductsData() throws DataLoadingException {

		log.info("Loading Products Data");

		Map<Integer, Product> productsData = new HashMap<Integer, Product>();

		List<List<String>> fileData;
		try {
			fileData = fileReader.readFile(System.getProperty("user.dir") + "/" + productsDataSource.getDataFileName());
		} catch (IOException | FileReaderException e) {
			log.error("Error occured while loading products data from file");
			e.printStackTrace();
			throw new DataLoadingException("Error occured while loading products data from file", e);
		}

		try {
			productsData = productsDataParser.parseData(fileData);
		} catch (DataParsingException e) {
			log.error("Error Occured while parsing products file data");
			e.printStackTrace();
			throw new DataLoadingException("Error Occured while parsing products file data", e);
		}

		return productsData;
	}

}
