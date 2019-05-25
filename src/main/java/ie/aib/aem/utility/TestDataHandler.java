package ie.aib.aem.utility;

import ie.aib.aem.base.BaseClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Test Data handler class.
 */
public class TestDataHandler extends BaseClass {
  /**
* Description: Setting up variables to store required data.
*/
  private static String strDataFileName;
  /**
   *  Setting up variables for sheet name to store required data.
   */
  private static String strDataSheetName;

  /**
 * Description: Member Functions, Function to read the excel and convert to Hash Table.
 * @param strFileName file name.
 * @param strSheetName sheet name.
 * @param primaryKey key value.
 * @return dataList Hash table.
 * @throws FileNotFoundException  file not found exception.

 */

  public static List<Hashtable<String, String>> readFile(String strFileName, String strSheetName,
      String primaryKey) throws FileNotFoundException {
    String colName;
    String colValue;
    strDataFileName = strFileName;
    strDataSheetName = strSheetName;
    List<Hashtable<String, String>> dataList = new ArrayList<Hashtable<String, String>>();
    XSSFSheet sheet = getSheet(strFileName, strSheetName);
    List<Integer> rownum = getRow(primaryKey, sheet);
    Iterator<Integer> rowIterator = rownum.iterator();
    while (rowIterator.hasNext()) {
      int rowToLoad = rowIterator.next();
      Hashtable<String, String> codes = new Hashtable<String, String>();
      for (int i = 0; i <= getColumnCount(sheet); i++) {
        colName = sheet.getRow(0).getCell(i).getStringCellValue().trim();
        if (sheet.getRow(rowToLoad).getCell(i) == null) {
          colValue = " ";
        } else {
          colValue = sheet.getRow(rowToLoad).getCell(i).getStringCellValue().trim();
          if (colValue.split(" ")[0].equalsIgnoreCase("Using")) {
            colValue = sheet.getRow(getRow(colValue.split(" ")[1], sheet).get(0))
                .getCell(getColumn(colValue.split(" ")[2], sheet)).getStringCellValue().trim();
          }
        }
        codes.put(colName, colValue);
      }
      dataList.add(codes);
    }
    return dataList;
  }

  /**
 * Description: Function to set Data in Excel sheet from hashtable for specific column.
 * @param hashData Input hash table.
 * @param columnName column name.
 */

  public void setDataForReview(Hashtable<String, String> hashData, String columnName) {
    try {
      FileInputStream file = new FileInputStream(new File("src\\main\\resources\\"
          + strDataFileName + ".xlsx"));
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      Cell cell = null;
      XSSFSheet writeSheet = workbook.getSheet(strDataSheetName);
      List<Integer> rownum = getRow(testData.get("PrimaryKey"), writeSheet);
      int cellNum = getColumn(columnName, writeSheet);
      if (null == writeSheet.getRow(rownum.get(0)).getCell(cellNum)) {
        cell = writeSheet.getRow(rownum.get(0)).createCell(cellNum);
      } else {
        cell = writeSheet.getRow(rownum.get(0)).getCell(cellNum);
      }
      cell.setCellValue(hashData.get(columnName));
      file.close();
      FileOutputStream outFile = new FileOutputStream(new File("src\\test\\resources\\"
          + strDataFileName + ".xlsx"));
      workbook.write(outFile);
      outFile.close();
      // workbook.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
 * Description: Function to fetch number of columns in sheet.
 * @param sheet name.
 * @return int value of coulumn count number.
 */

  private static int getColumnCount(XSSFSheet sheet) {
    int col = sheet.getRow(0).getLastCellNum();
    return (col - 1);
  }

  /**
 * Description: Method to locate the row of primary key.
 * @param primaryKey primary key value.
 * @param sheet sheet name.
 * @return rowNum row number count in integer.
 */

  private static List<Integer> getRow(String primaryKey, XSSFSheet sheet) {
    int i = 1;
    int k = 0;
    List<Integer> rowNum = new ArrayList<Integer>();
    for (i = 1; i < getRowCount(sheet); i++) {
      if (sheet.getRow(i).getCell(0).getStringCellValue().trim().toLowerCase()
          .contains(primaryKey.toLowerCase())) {
        rowNum.add(i);
        k = 1;
      }
    }
    if (k == 0) {
      rowNum = null;
    }
    return rowNum;
  }

  /**
 * Description: Function to fetch number of rows in sheet.
 * @param sheet sheetname.
 * @return sheet row number in Integer value.
 */

  private static int getRowCount(XSSFSheet sheet) {
    return sheet.getLastRowNum() + 1;
  }

  /**
 * Description: Function to get required sheet as object.
 * @param strFileName filename.
 * @param strSheetName sheet name.
 * @return sheet sheetname
 * @throws FileNotFoundException file not found exception.
 */

  private static XSSFSheet getSheet(String strFileName, String strSheetName)
      throws FileNotFoundException {
    XSSFSheet sheet = null;
    try {
      FileInputStream fis = new FileInputStream("src/main/resources/" + strFileName + ".xlsx");
      XSSFWorkbook wb = new XSSFWorkbook(fis);
      sheet = wb.getSheet(strSheetName);
      fis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sheet;
  }

  /**
 * Description: Function to get column number based on column name.
 * @param columnName column name in sheet
 * @param writeSheet sheet
 * @return i column number.
 */

  private static int getColumn(String columnName, XSSFSheet writeSheet) {
    int i = -1;
    for (i = 2; i < writeSheet.getRow(0).getLastCellNum(); i++) {
      if (writeSheet.getRow(0).getCell(i).getStringCellValue().trim()
          .equalsIgnoreCase(columnName)) {
        break;
      }
    }
    return i;
  }
}
