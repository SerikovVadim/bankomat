package ru.sbrf.lesson.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.sbrf.lesson.messages.BalanceRequestData;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.Iterator;

public class FileXLSXConverter implements Converter {
    @Override
    public String convert(BalanceRequestData data) throws IOException {
        Workbook workbookOut = new XSSFWorkbook();
        Sheet sheet = workbookOut.createSheet("Test");
        Row row = sheet.createRow(0);

        Cell cell0 = row.createCell(0);
        cell0.setCellValue(""+data.getClientId());

        Cell cell1 = row.createCell(1);
        cell1.setCellValue(""+data.getAccountId());

        Cell cell2 = row.createCell(2);
        cell2.setCellValue(""+data.getPIN());

        sheet.autoSizeColumn(0); // auto size all

        String filename = "Test.xlsx";
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbookOut.write(fileOut);
        fileOut.close();
        return (filename);

    }


    @Override
    public BalanceRequestData convertToBalanceRequestData(String data) throws IOException, JAXBException {
        File excelFile = new File(data);
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbookIn = new XSSFWorkbook(fis);
        System.out.println(workbookIn.getNumberOfSheets());

        XSSFSheet sheetIn = workbookIn.getSheetAt(0);

        for(Row rowT : sheetIn){
            System.out.println(rowT.getRowNum());
        }

//        Iterator<Row> rowIt = sheetIn.iterator();
//
//        while (rowIt.hasNext()) {
//            Row rowIn = rowIt.next();
//            Iterator<Cell> cellIterator = rowIn.cellIterator();
//
//            while (cellIterator.hasNext()) {
//                Cell cellIn = cellIterator.next();
//                System.out.print(cellIn.toString() + ";");
//            }
//
//            System.out.println();
//        }
        Row row = sheetIn.getRow(0);

        BalanceRequestData requestData = new BalanceRequestData(
                Integer.parseInt(row.getCell(0).getStringCellValue()),
                Integer.parseInt(row.getCell(1).getStringCellValue()),
                Integer.parseInt(row.getCell(2).getStringCellValue()));

        workbookIn.close();
        fis.close();
        return requestData;
    }
}
