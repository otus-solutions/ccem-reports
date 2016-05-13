package br.org.ccem.reports.file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import br.org.ccem.reports.csv.CsvFile;
import br.org.ccem.reports.csv.CsvFileWriter;
import br.org.ccem.reports.csv.CsvLine;

public class ReportFileReaderTest {

	private static final String UTF8_BOM﻿ = "\uFEFF";

	private static final String VALID_FILE_PATH = "file_to_write.csv";
	private static final String INVALID_FILE_PATH = "";

	private static final String HEADER1_VALUE = "header1";
	private static final String HEADER2_VALUE = "header2";
	private static final String FIELD1_VALUE = "field1";
	private static final String FIELD2_VALUE = "field2";

	private static final String FIRST_LINE = HEADER1_VALUE + ";" + HEADER2_VALUE + ";";
	private static final String SECOND_LINE = FIELD1_VALUE + ";" + FIELD2_VALUE + ";";

	@Test
	public void writeMethodShouldCreateAndAddContentToFile() throws IOException {
		CsvFileWriter fileWriter = new CsvFileWriter(createCsvFile());
		fileWriter.write();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(VALID_FILE_PATH).getFile());

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String line1 = removeUtf8Bom(bufferedReader.readLine());
		String line2 = bufferedReader.readLine();
		assertThat(line1, equalTo(FIRST_LINE));
		assertThat(line2, equalTo(SECOND_LINE));

		bufferedReader.close();
	}

	@Test(expected = IOException.class)
	public void writeMethodMethodShouldThrowAnIOExceptionWhenParameterIsInvalid() throws IOException {
		CsvFileWriter fileWriter = new CsvFileWriter(createInvalidCsvFile());
		fileWriter.write();
	}

	private String removeUtf8Bom(String text) {
		return text.replace(UTF8_BOM﻿, "");
	}

	private CsvFile createInvalidCsvFile() throws IOException {
		return new CsvFile(INVALID_FILE_PATH);
	}

	private CsvFile createCsvFile() throws IOException {
		CsvFile csvFile = new CsvFile(VALID_FILE_PATH);

		csvFile.addHeaderColumn(HEADER1_VALUE);
		csvFile.addHeaderColumn(HEADER2_VALUE);
		csvFile.addLine(CsvLine.create(FIELD1_VALUE, FIELD2_VALUE));

		return csvFile;
	}

}
