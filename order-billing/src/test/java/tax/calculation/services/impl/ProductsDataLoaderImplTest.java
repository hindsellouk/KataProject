package tax.calculation.services.impl;

import java.io.IOException;
import java.util.Collections;

import org.apache.commons.collections4.MapUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import tax.calculation.configuration.properties.ProductsDataSource;
import tax.calculation.exceptions.DataLoadingException;
import tax.calculation.exceptions.DataParsingException;
import tax.calculation.exceptions.FileReaderException;
import tax.calculation.helpers.FileReader;
import tax.calculation.helpers.ProductsDataParser;
import tax.calculation.pojos.Product;

@RunWith(SpringRunner.class)
public class ProductsDataLoaderImplTest {

	private ProductsDataSource productsDataSource;
	private FileReader fileReader;
	private ProductsDataParser productsDataParser;

	private ProductsDataLoaderImpl productsDataLoaderImpl;

	@Before
	public void setUp() {
		productsDataSource = Mockito.mock(ProductsDataSource.class);
		fileReader = Mockito.mock(FileReader.class);
		productsDataParser = Mockito.mock(ProductsDataParser.class);

		productsDataLoaderImpl = new ProductsDataLoaderImpl(productsDataSource, fileReader, productsDataParser);
	}

	@Test(expected = DataLoadingException.class)
	public void should_return_DataLoadingException_when_an_IOException_occur_while_reding_file()
			throws IOException, FileReaderException, DataLoadingException {
		Mockito.when(fileReader.readFile(Mockito.any())).thenThrow(IOException.class);
		productsDataLoaderImpl.loadProductsData();
	}

	@Test(expected = DataLoadingException.class)
	public void should_return_DataLoadingException_when_a_FileReaderException_occur_while_reding_file()
			throws IOException, FileReaderException, DataLoadingException {
		Mockito.when(fileReader.readFile(Mockito.any())).thenThrow(FileReaderException.class);
		productsDataLoaderImpl.loadProductsData();
	}

	@Test(expected = DataLoadingException.class)
	public void should_return_DataLoadingException_when_a_DataParsingException_occur_while_parsing_the_file_data()
			throws IOException, FileReaderException, DataParsingException, DataLoadingException {
		Mockito.when(fileReader.readFile(Mockito.any())).thenReturn(Collections.emptyList());
		Mockito.when(productsDataParser.parseData(Mockito.any())).thenThrow(DataParsingException.class);
		productsDataLoaderImpl.loadProductsData();
	}

	@Test
	public void should_return_parsed_data()
			throws IOException, FileReaderException, DataParsingException, DataLoadingException {
		Mockito.when(fileReader.readFile(Mockito.any())).thenReturn(Collections.emptyList());
		Mockito.when(productsDataParser.parseData(Mockito.any()))
				.thenReturn(Collections.singletonMap(1, new Product()));
		Assert.assertTrue(MapUtils.isNotEmpty(productsDataLoaderImpl.loadProductsData()));
	}
}
