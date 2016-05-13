package br.org.ccem.reports.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ReportFileWriter {

	private static final String UTF_8_CHARSET = "UTF-8";
	private static final String EMPTY_STRING = "";
	private static final String SEMICOLON = ";";
	private BufferedWriter out;

	public static ReportFileWriter createWriter(ReportFile reportFile) throws IOException {
		FileWriter fileWriter = new FileWriter(reportFile.getFile());
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		return new ReportFileWriter(bufferedWriter);
	}

	private ReportFileWriter(BufferedWriter out) throws IOException {
		this.out = out;
		out.write('\ufeff');
	}

	public void writeData(String data) throws IOException {
		if ((data == null) || (data.isEmpty())) {
			data = EMPTY_STRING;
		}

		out.write(forceUtf8Encoding(data));
	}

	public void close() throws IOException {
		out.close();
	}

	public void writeEndLine() throws IOException {
		out.newLine();
	}

	public void writeSemiColon() throws IOException {
		out.write(SEMICOLON);
	}

	private String forceUtf8Encoding(String data) throws UnsupportedEncodingException {
		return new String(data.trim().getBytes(), UTF_8_CHARSET);
	}

}