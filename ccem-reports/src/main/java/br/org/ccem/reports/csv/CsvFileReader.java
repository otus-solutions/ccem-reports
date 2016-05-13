package br.org.ccem.reports.csv;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.org.ccem.reports.file.ReportFileReader;

public class CsvFileReader {

	private ReportFileReader fileReader;

	public CsvFileReader(String filepath) throws FileNotFoundException {
		fileReader = new ReportFileReader(filepath);
	}

	public String getContent() throws IOException {
		return fileReader.readAllContent();
	}

	public CsvFile getCsvFile() {
		try {
			return buildCsvFile();
		} catch (IOException e) {
			return null;
		}
	}
	
	public void close() throws IOException {
		fileReader.close();
	}

	private CsvFile buildCsvFile() throws IOException {
		CsvFile csvFile = new CsvFile(fileReader.getFile());

		String[] headers = fileReader.readFirstLine().split(";");

		for (int column = 0; column < headers.length; column++) {
			csvFile.addHeaderColumn(headers[column]);
		}

		for (String line : fileReader.readFromLine(2)) {
			csvFile.addLine(CsvLine.create(line.split(";")));
		}

		return csvFile;
	}

}
