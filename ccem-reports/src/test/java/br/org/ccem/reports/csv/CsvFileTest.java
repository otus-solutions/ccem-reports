package br.org.ccem.reports.csv;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

import java.util.List;

import org.junit.Test;

public class CsvFileTest {

	private static final String VALID_FILE_PATH = "src/test/resources/file_to_write.csv";
	private static final String HEADER1_VALUE = "field1";
	private static final String HEADER2_VALUE = "field2";
	private static final String HEADER3_VALUE = "null";
	private static final String FORMATTED_HEADER = HEADER1_VALUE + ";" + HEADER2_VALUE + ";" + HEADER3_VALUE + ";";

	@Test
	public void aNewCsvFileObjectShouldHaveAnEmptyHeaderColumnList() {
		CsvFile csvFile = new CsvFile(VALID_FILE_PATH);

		List<String> returnedList = csvFile.getHeaderColumns();

		assertThat(returnedList, empty());
	}

	@Test
	public void aNewCsvFileObjectShouldHaveAnEmptyCsvLineList() {
		CsvFile csvFile = new CsvFile(VALID_FILE_PATH);

		List<CsvLine> returnedList = csvFile.getCsvLines();

		assertThat(returnedList, empty());
	}

	@Test
	public void getHeaderColumnsMethodShouldReturnAllAddedHeaders() {
		CsvFile csvFile = new CsvFile(VALID_FILE_PATH);

		csvFile.addHeaderColumn(HEADER1_VALUE);
		csvFile.addHeaderColumn(HEADER2_VALUE);
		csvFile.addHeaderColumn(HEADER3_VALUE);

		List<String> returnedList = csvFile.getHeaderColumns();

		assertThat(returnedList, hasItems(HEADER1_VALUE, HEADER2_VALUE, HEADER3_VALUE));
	}

	@Test
	public void getFormattedHeaderMethodShouldReturnAStringWithHeadersSeparatedBySemicolon() {
		CsvFile csvFile = new CsvFile(VALID_FILE_PATH);

		csvFile.addHeaderColumn(HEADER1_VALUE);
		csvFile.addHeaderColumn(HEADER2_VALUE);
		csvFile.addHeaderColumn(HEADER3_VALUE);

		String returnedString = csvFile.getFormattedHeader();

		assertThat(returnedString, equalTo(FORMATTED_HEADER));
	}

	@Test
	public void getCsvLinesMethodShouldReturnANotEmptyListWhenLinesAreAddedToFile() {
		CsvFile csvFile = new CsvFile(VALID_FILE_PATH);

		csvFile.addLine(CsvLine.create(HEADER1_VALUE, HEADER2_VALUE, HEADER3_VALUE));
		csvFile.addLine(CsvLine.create(HEADER1_VALUE, HEADER3_VALUE, HEADER2_VALUE));
		csvFile.addLine(CsvLine.create(HEADER3_VALUE, HEADER1_VALUE, HEADER2_VALUE));

		List<CsvLine> returnedList = csvFile.getCsvLines();

		assertThat(returnedList, not(empty()));
	}

	@Test
	public void addHeaderColumnMethodShouldAddAStringToHeaderList() {
		CsvFile csvFile = new CsvFile(VALID_FILE_PATH);

		csvFile.addHeaderColumn(HEADER1_VALUE);
		csvFile.addHeaderColumn(HEADER2_VALUE);
		csvFile.addHeaderColumn(HEADER3_VALUE);

		List<String> returnedList = csvFile.getHeaderColumns();

		assertThat(returnedList, hasItems(HEADER1_VALUE, HEADER2_VALUE, HEADER3_VALUE));
		assertThat(returnedList, hasSize(3));
	}

	@Test
	public void addLineColumnMethodShouldAddACsvLineToLineList() {
		CsvLine line1 = CsvLine.create(HEADER1_VALUE, HEADER2_VALUE, HEADER3_VALUE);
		CsvLine line2 = CsvLine.create(HEADER3_VALUE, HEADER1_VALUE, HEADER2_VALUE);

		CsvFile csvFile = new CsvFile(VALID_FILE_PATH);
		csvFile.addLine(line1);
		csvFile.addLine(line2);

		List<CsvLine> returnedList = csvFile.getCsvLines();

		assertThat(returnedList, hasItems(line1, line2));
		assertThat(returnedList, hasSize(2));
	}

}
