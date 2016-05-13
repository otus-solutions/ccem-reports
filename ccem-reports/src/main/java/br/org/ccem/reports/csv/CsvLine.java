package br.org.ccem.reports.csv;

import java.util.LinkedList;
import java.util.List;

public class CsvLine {

	private List<String> fields;

	public static CsvLine create(String... data) {
		return new CsvLine(data);
	}

	public CsvLine(String... data) {
		fields = new LinkedList<String>();

		if (data != null && data.length > 0) {
			initializeFields(data);
		}
	}

	private void initializeFields(String... data) {
		for (int field = 0; field < data.length; field++) {
			fields.add(data[field]);
		}
	}

	public List<String> getFields() {
		return fields;
	}

	public String getFormattedLine() {
		StringBuilder lineBuilder = new StringBuilder();

		for (String field : fields) {
			lineBuilder.append(field.replaceAll("\\r?\\n", " "));
			lineBuilder.append(CsvFile.SEMICOLON);
		}

		return lineBuilder.toString();
	}

	public void addField(Object data) {
		fields.add(data == null ? "" : String.valueOf(data));
		
	}

	public void addEmptyField() {
		fields.add("");
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
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
		if (!(obj instanceof CsvLine)) {
			return false;
		}
		CsvLine other = (CsvLine) obj;
		if (fields == null) {
			if (other.fields != null) {
				return false;
			}
		} else if (!fields.equals(other.fields)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return fields.toString();
	}
}
