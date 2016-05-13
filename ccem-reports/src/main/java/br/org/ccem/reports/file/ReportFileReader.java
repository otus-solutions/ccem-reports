package br.org.ccem.reports.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ReportFileReader {

	private static final String LINE_SEPARATOR_PROPERTY = "line.separator";
	private static final String UTF8_BOM = "\uFEFF";

	private List<String> lines;
	private File file;
	private BufferedReader in;

	public ReportFileReader(String filepath) throws FileNotFoundException {
		this(new File(filepath));
	}

	public ReportFileReader(File file) throws FileNotFoundException {
		this.file = file;
		FileReader fileReader = new FileReader(file);
		in = new BufferedReader(fileReader);
	}

	public File getFile() {
		return file;
	}

	public List<String> getLines() throws IOException {
		readAllContent();
		return lines;
	}

	public String readFirstLine() throws IOException {
		return readLineAt(1);
	}

	public String readLineAt(Integer lineNumber) throws IOException {
		readAllContent();
		return lines.get(lineNumber - 1);
	}

	public List<String> readFromLine(Integer lineNumber) throws IOException {
		readAllContent();
		return lines.subList(lineNumber - 1, lines.size());
	}

	public String readAllContent() throws IOException {
		StringBuilder contentBuilder = new StringBuilder();

		if (lines == null) {
			readFromFile(contentBuilder);
		} else {
			readFromMemory(contentBuilder);
		}

		clearLastLineSeparator(contentBuilder);

		return contentBuilder.toString();
	}

	private void readFromMemory(StringBuilder contentBuilder) {
		for (String line : lines) {
			contentBuilder.append(line);
			contentBuilder.append(System.getProperty(LINE_SEPARATOR_PROPERTY));
		}
	}
	
	public void close() throws IOException {
		in.close();
	}

	private void readFromFile(StringBuilder contentBuilder) throws IOException {
		lines = new LinkedList<String>();
		String line;
		while ((line = in.readLine()) != null) {
			 
			if(line.startsWith(UTF8_BOM)) {
				line = line.substring(1);
			}
			lines.add(line);
			contentBuilder.append(line);
			contentBuilder.append(System.getProperty(LINE_SEPARATOR_PROPERTY));
		}
	}

	private void clearLastLineSeparator(StringBuilder contentBuilder) {
		Integer lastLineSeparator = contentBuilder.lastIndexOf(System.getProperty(LINE_SEPARATOR_PROPERTY));
		contentBuilder.delete(lastLineSeparator, lastLineSeparator);
	}
}
