package br.org.ccem.reports.csv;

import java.io.IOException;

import br.org.ccem.reports.file.ReportFileWriter;

public class CsvFileWriter {

	private CsvFile file;
	private ReportFileWriter fileWriter;

	public CsvFileWriter(CsvFile file) throws IOException {
		this.file = file;
	}

	public void write() throws IOException {
		fileWriter = ReportFileWriter.createWriter(file);
		writeHeaders();
		writeLines();
		fileWriter.close();
	}

	private void writeHeaders() throws IOException {
		fileWriter.writeData(file.getFormattedHeader());
		fileWriter.writeEndLine();
	}

	private void writeLines() throws IOException {
		for (CsvLine line : file.getCsvLines()) {
			fileWriter.writeData(line.getFormattedLine());
			fileWriter.writeEndLine();
		}
	}

}
