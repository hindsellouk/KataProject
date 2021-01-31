package tax.calculation.helpers.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tax.calculation.exceptions.FileReaderException;
import tax.calculation.helpers.FileReader;

@Service
@Slf4j
public class FileReaderImpl implements FileReader {

	private Workbook workbook;

	@Override
	public List<List<String>> readFile(String fileLocation) throws IOException, FileReaderException {

		log.info("Reading file: " + fileLocation);
		try {
			workbook = new XSSFWorkbook(fileLocation);
		} catch (InvalidOperationException | IllegalArgumentException e) {
			log.error("Can't Open file: " + fileLocation);
			e.printStackTrace();
			throw new FileReaderException("Can't Open file: " + fileLocation);
		}

		Sheet sheet = workbook.getSheetAt(0);

		List<List<String>> fileData = new ArrayList<>();
		for (Row row : sheet) {
			List<String> fileRowData = new ArrayList<String>();
			for (Cell cell : row) {
				fileRowData.add(cell.getStringCellValue());
			}
			fileData.add(fileRowData);
		}

		return fileData;
	}
}
