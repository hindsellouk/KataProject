package tax.calculation.helpers;

import java.io.IOException;
import java.util.List;

import tax.calculation.exceptions.FileReaderException;

public interface FileReader {

	List<List<String>> readFile(String fileLocation) throws IOException, FileReaderException;

}
