package br.org.ccem.reports.csv;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.junit.Test;

public class CsvLineTest {

	private static final String EMPTY_STRING = "";
	private static final String FIELD1_VALUE = "field1";
	private static final String FIELD2_VALUE = "field2";
	private static final String FIELD3_VALUE = "null";
	private static final String FIELD_WITH_LINEBREAK = "texto\r\ntexto";
	private static final String FIELD_WITH_WHITESPACE = "texto texto;";
	private static final String FORMATTED_LINE = FIELD1_VALUE + ";" + FIELD2_VALUE + ";" + FIELD3_VALUE + ";";

	@Test
	public void newCSVLineObjectsShouldNotHaveNullFieldList() {
		CsvLine csvLine = new CsvLine();

		List<String> returnedFieldList = csvLine.getFields();

		assertThat(returnedFieldList, notNullValue());
	}

	@Test
	public void newCSVLineObjectShouldHaveAnEmptyFieldListWhenNoParameterIsPassedInConstructor() {
		CsvLine csvLine = new CsvLine();

		List<String> returnedFieldList = csvLine.getFields();

		assertThat(returnedFieldList, empty());
	}

	@Test
	public void newCSVLineObjectShouldHaveANotEmptyFieldListWhenArgumentsArePassedInConstructor() {
		CsvLine csvLine = new CsvLine(FIELD1_VALUE, FIELD2_VALUE, FIELD3_VALUE);

		List<String> returnedFieldList = csvLine.getFields();

		assertThat(returnedFieldList, not(empty()));
	}

	@Test
	public void newCSVLineObjectShouldHaveAllArgumentValuesPassedInConstructor() {
		CsvLine csvLine = new CsvLine(FIELD1_VALUE, FIELD2_VALUE, FIELD3_VALUE);

		List<String> returnedFieldList = csvLine.getFields();

		assertThat(returnedFieldList, hasItems(FIELD1_VALUE, FIELD2_VALUE, FIELD3_VALUE));
	}

	@Test
	public void addFieldMethodShouldAddValueToCSVLineFields() {
		CsvLine csvLine = new CsvLine();

		csvLine.addField(FIELD1_VALUE);
		csvLine.addField(FIELD2_VALUE);
		csvLine.addField(FIELD3_VALUE);

		List<String> returnedFieldList = csvLine.getFields();

		assertThat(returnedFieldList, hasItems(FIELD1_VALUE, FIELD2_VALUE, FIELD3_VALUE));
	}

	@Test
	public void getFormattedLineMethodShouldReturnAStringWithFieldsSeparatedBySemicolon() {
		CsvLine csvLine = new CsvLine(FIELD1_VALUE, FIELD2_VALUE, FIELD3_VALUE);

		String returnedString = csvLine.getFormattedLine();

		assertThat(returnedString, equalTo(FORMATTED_LINE));
	}

	@Test
	public void getFormattedLineMethodShouldReturnAEmtpyStringWhenNoFieldsAreAdded() {
		CsvLine csvLine = new CsvLine();

		String returnedString = csvLine.getFormattedLine();

		assertThat(returnedString, equalTo(EMPTY_STRING));
	}
	
	@Test
	public void getFormattedReturnLineWithASpaceWhite() {
		CsvLine csvLine = new CsvLine(FIELD_WITH_LINEBREAK);
		
		String returnedString = csvLine.getFormattedLine();

		assertThat(returnedString, equalTo(FIELD_WITH_WHITESPACE));
		
	}

}
