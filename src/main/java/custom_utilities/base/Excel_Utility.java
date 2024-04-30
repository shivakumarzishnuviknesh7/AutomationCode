package custom_utilities.base;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class Excel_Utility {
	FileInputStream fis;
	XSSFWorkbook workbook;

	public ArrayList<String> getData(String testcasename, String sheet_name) throws IOException {
		ArrayList<String> a = new ArrayList<String>();

		File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "resources" + File.separator + "Id_Password.properties");

		FileInputStream fis1 = null;
		try {
			fis1 = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fis1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String Run_Environment = prop.getProperty("Run_environment");

		if (Run_Environment.equals("Featureuat")) {

			fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "java" + File.separator + "resources" + File.separator + "Bank_And_NDrive_uat.xlsx");

		} else if (Run_Environment.equals("Bugfix")) {

			fis = new FileInputStream(
					System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
							+ "java" + File.separator + "resources" + File.separator + "Bank_And_NDrive_Bugfix.xlsx");

		} else {

			System.out.println("provide proper instance name");
			Assert.assertTrue(false, "didn't provide proper instance name");
		}

		workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheet_name)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identify Testcases coloum by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("TestCase")) {
						coloumn = k;
					}
					k++;
				}
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcasename)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							a.add(cv.next().getStringCellValue());
						}
					}
				}

			}
		}

		for (Object d : a) {
			System.out.println("Excel values    -  "+(String) (d));
		}

		return a;
	}
}
