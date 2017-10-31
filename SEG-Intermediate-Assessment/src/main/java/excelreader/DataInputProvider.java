 package excelreader;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utility.ResourceHandler;

public class DataInputProvider{

	public static Object[][] getAllSheetData(String dataFileName, String sheetName) {

		Object[][] data = null;
		XSSFWorkbook workbook = null;
		InputStream fis = null;
		try {
			//fis = new FileInputStream(new File("./data/"+dataSheetName+".xlsx"));
			fis = ResourceHandler.getResourcePathInputStream("\\src\\test\\resources\\testdata\\" +dataFileName+ ".xlsx");
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);	

			// get the number of rows
			int rowCount = sheet.getLastRowNum();

			// get the number of columns
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][columnCount];

			// loop through the rows
			for(int i=1; i <rowCount+1; i++){
				try {
					XSSFRow row = sheet.getRow(i);
					for(int j=0; j <columnCount; j++){ // loop through the columns
						try {
							String cellValue = "";
							try{
								cellValue = cellData(row.getCell(j));
							} catch(NullPointerException npe){
								npe.printStackTrace();
							}
							data[i-1][j]  = cellValue; // add to the data array
						} catch (Exception e) {
							e.printStackTrace();
						}				
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public static String cellData(XSSFCell cell){
		String cellValue = null;
		if (cell.getCellTypeEnum() == CellType.STRING) {
			cellValue = cell.getStringCellValue();
		} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
			cellValue = String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
			cellValue = String.valueOf(cell.getBooleanCellValue());
		} 
		return cellValue;
	}
}