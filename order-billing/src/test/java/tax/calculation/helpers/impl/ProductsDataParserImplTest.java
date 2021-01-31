package tax.calculation.helpers.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import tax.calculation.exceptions.DataParsingException;
import tax.calculation.pojos.Product;

@RunWith(SpringRunner.class)
public class ProductsDataParserImplTest {

	private ProductsDataParserImpl productsDataParserImpl;

	@Before
	public void setUp() {
		this.productsDataParserImpl = new ProductsDataParserImpl();
	}

	@Test(expected = DataParsingException.class)
	public void should_throw_DataParsingException_when_file_data_is_empty() throws DataParsingException {
		productsDataParserImpl.parseData(Collections.emptyList());
	}

	@Test(expected = DataParsingException.class)
	public void should_throw_DataParsingException_when_file_data_products_attributes_are_empty()
			throws DataParsingException {
		productsDataParserImpl.parseData(Collections.singletonList(Collections.emptyList()));
	}

	@Test(expected = DataParsingException.class)
	public void should_throw_DataParsingException_when_file_data_products_attributes_size_is_less_that_what_is_needed()
			throws DataParsingException {
		productsDataParserImpl.parseData(Collections.singletonList(Collections.singletonList("")));
	}

	@Test
	public void should_parse_file_data_to_product_data() throws DataParsingException {
		String productIdStr = "1";
		int productIdInt = 1;
		String productLabel = "livre";
		String productType = "BOOKS";
		List<List<String>> fileData = new ArrayList<List<String>>();
		fileData.add(Arrays.asList(productIdStr, productLabel, productType));

		Map<Integer, Product> result = productsDataParserImpl.parseData(fileData);
		Assert.assertEquals(productLabel, result.get(productIdInt).getLabel());
		Assert.assertEquals(productType, result.get(productIdInt).getType());
	}
}
