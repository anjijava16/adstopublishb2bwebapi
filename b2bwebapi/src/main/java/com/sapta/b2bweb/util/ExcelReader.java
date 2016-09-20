package com.sapta.b2bweb.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sapta.b2bweb.dao.RadioDAO;
import com.sapta.b2bweb.domainobject.RadioDO;

public class ExcelReader {
    public static void Reader() {
        try {
            FileInputStream file = new FileInputStream(new File("C:/Users/SAPTALABS/Desktop/Excel.xlsx"));
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                RadioDO radioDo = new RadioDO();
                int count = 0;
                while (cellIterator.hasNext()) {
                	count++;
                	System.out.println(count+"------------------------");
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    String aaa = null;
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                             aaa = String.valueOf(cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                        	 aaa =  cell.getStringCellValue();
                            break;
                    }
                    switch(count){
	                    case 1: radioDo.setName(aaa);
	                    case 2: radioDo.setType(aaa);
	                    case 3: radioDo.setCopies(aaa);
	                    case 4: radioDo.setLang(aaa);
	                    case 5: radioDo.setTimetype(aaa);
	                    case 6: radioDo.setCost(aaa);
                    }
                }
                System.out.println("addddd=================");
                new RadioDAO().addRadio(radioDo);
               
            }
            file.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}