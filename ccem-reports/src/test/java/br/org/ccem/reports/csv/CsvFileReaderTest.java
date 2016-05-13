package br.org.ccem.reports.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CsvFileReaderTest {

	private static final String VALID_FILE_PATH = "file_to_write.csv";

	@Test
	public void test() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(VALID_FILE_PATH).getFile());

		CsvFileReader csvFileReader = new CsvFileReader(file.getAbsolutePath());
		csvFileReader.getCsvFile();
	}
	
	@Test
	public void testEquals() {
		List<String> list1 = new LinkedList<String>();
		list1.add("item1");
		list1.add("item2");
		
		List<String> list2 = new LinkedList<String>();
		list2.add("item1");
		list2.add("item2");
		
		Assert.assertTrue(list1.equals(list2));
	}
	
	@Test
	public void testCsvFileEquals() throws IOException {
		CsvFile file1 = new CsvFile(File.createTempFile("file1", null));
		file1.addHeaderColumn("item1");
		file1.addHeaderColumn("item2");
		
		file1.addLine(CsvLine.create("item1", "item2"));
		
		CsvFile file2 = new CsvFile(File.createTempFile("file2", null));
		file2.addHeaderColumn("item1");
		file2.addHeaderColumn("item2");
		
		file2.addLine(CsvLine.create("item1", "item2"));
		
		Assert.assertTrue(file1.equals(file2));
	}

}
