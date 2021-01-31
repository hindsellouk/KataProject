package tax.calculation.helpers.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import tax.calculation.exceptions.FileReaderException;

@RunWith(SpringRunner.class)
public class FileReaderImplTest {

	private FileReaderImpl fileReaderImpl;

	@Before
	public void setUp() {
		fileReaderImpl = new FileReaderImpl();
	}

	@Test(expected = FileReaderException.class)
	public void should_throw_FileReaderException_when_fileLocation_is_null() throws IOException, FileReaderException {
		fileReaderImpl.readFile(null);
	}

	@Test(expected = FileReaderException.class)
	public void should_throw_FileReaderException_when_fileLocation_does_not_exist()
			throws IOException, FileReaderException {
		String fileLocation = "non_existing_file_location";
		fileReaderImpl.readFile(fileLocation);
	}

	@Test
	public void should_return_filled_list_of_products_data() throws IOException, FileReaderException {
		List<List<String>> result = fileReaderImpl.readFile(System.getProperty("user.dir") + "/products_data.xlsx");
		Assert.assertTrue(CollectionUtils.isNotEmpty(result));
	}

	@Test
	public void should_return_filled_list_of_products_where_each_list_contains_three_product_attributes()
			throws IOException, FileReaderException {
		List<List<String>> result = fileReaderImpl.readFile(System.getProperty("user.dir") + "/products_data.xlsx");
		Assert.assertTrue(result.get(0).size() == 3);
	}
}
