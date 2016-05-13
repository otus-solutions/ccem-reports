package br.org.ccem.reports.csv;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.junit.Test;

public class CsvFileWriterTest {

	private static final String VALID_FILE_PATH = "src/test/resources/file_to_write.csv";

	private static final String HEADER1_VALUE = "header1";
	private static final String HEADER2_VALUE = "header2";
	private static final String FIELD1_VALUE = "field1";
	private static final String FIELD2_VALUE = "field2";

	@Test
	public void createWriterMethodShouldReturnANotNullCSVFileWriterObjectWhenParameterIsValid() throws IOException {
		Object returnedObject = new CsvFileWriter(createCsvFile());

		assertThat(returnedObject, notNullValue());
	}

	@Test
	public void createWriterMethodShouldReturnACSVFileWriterObjectWhenParameterIsValid() throws IOException {
		Object returnedObject = new CsvFileWriter(createCsvFile());

		assertThat(returnedObject, instanceOf(CsvFileWriter.class));
	}

	private CsvFile createCsvFile() throws IOException {
		CsvFile csvFile = new CsvFile(VALID_FILE_PATH);

		csvFile.addHeaderColumn(HEADER1_VALUE);
		csvFile.addHeaderColumn(HEADER2_VALUE);
		csvFile.addLine(CsvLine.create(FIELD1_VALUE, FIELD2_VALUE));

		return csvFile;
	}

}
