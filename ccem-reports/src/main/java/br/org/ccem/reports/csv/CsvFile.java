package br.org.ccem.reports.csv;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import br.org.ccem.reports.file.ReportFile;

public class CsvFile implements ReportFile {

	public static final String SEMICOLON = ";";
	public static final String CSV_EXTENSION = ".csv";

	private List<String> headerColumns;
	private List<CsvLine> csvLines;
	private File file;

	public CsvFile(String filepath) {
		this(new File(filepath));
	}

	public CsvFile(File file) {
		this.file = file;

		headerColumns = new LinkedList<String>();
		csvLines = new LinkedList<CsvLine>();
	}

	public List<String> getHeaderColumns() {
		return headerColumns;
	}

	public String getFormattedHeader() {
		StringBuilder headerBuilder = new StringBuilder();

		for (String header : headerColumns) {
			headerBuilder.append(header);
			headerBuilder.append(SEMICOLON);
		}

		return headerBuilder.toString();
	}

	public List<CsvLine> getCsvLines() {
		return csvLines;
	}

	public void addHeaderColumn(String columnName) {
		headerColumns.add(columnName);
	}

	public void addLine(CsvLine csvLine) {
		csvLines.add(csvLine);
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((csvLines == null) ? 0 : csvLines.hashCode());
		result = prime * result + ((headerColumns == null) ? 0 : headerColumns.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CsvFile)) {
			return false;
		}
		CsvFile other = (CsvFile) obj;
		if (csvLines == null) {
			if (other.csvLines != null) {
				return false;
			}
		} else if (!csvLines.equals(other.csvLines)) {
			return false;
		}
		if (headerColumns == null) {
			if (other.headerColumns != null) {
				return false;
			}
		} else if (!headerColumns.equals(other.headerColumns)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(headerColumns.toString() + "\n");
		for (CsvLine line : csvLines) {
			sb.append(line.toString() + "\n");
		}
		return sb.toString();
	}
}
