package com.demographicwebapi.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.demographicwebapi.demographicwebapi.models.NameIndex;

public class Excelhelper {
    public static boolean checkExcelFormat(MultipartFile file) {

        // method to check whether the uploaded file is csv or not
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }

    public static List<NameIndex> convertExcelToListOfNameIndex(InputStream is) {
        List<NameIndex> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("data");
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                String[] names = new String[11];
                int cid = 0;
                NameIndex n = new NameIndex();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    if(rowNumber%2==1)
                    {
                        switch (cid) {
                            case 0:
                                break;
    
                            case 1:
                                n.setId((long) cell.getNumericCellValue());
                                break;
                            case 2:
                                names[0] = cell.getStringCellValue();
                                break;
                            case 3:
                                names[1] = cell.getStringCellValue();
                                break;
                            case 4:
                                names[2] = cell.getStringCellValue();
                                break;
                            case 5:
                                names[3] = cell.getStringCellValue();
                                break;
    
                            case 6:
                                names[4] = cell.getStringCellValue();
                                break;
    
                            case 7:
                                names[5] = cell.getStringCellValue();
                                break;
    
                            case 8:
                                names[6] = cell.getStringCellValue();
                                break;
    
                            case 9:
                                names[7] = cell.getStringCellValue();
                                break;
    
                            case 10:
                                names[8] = cell.getStringCellValue();
                                break;
    
                            case 11:
                                names[9] = cell.getStringCellValue();
                                break;
    
                            default:
                                break;
                        }
                        String str = Arrays.toString(names);
                        n.setJson(str);
                    }
                    else{
                        switch(cid){
                            case 0:
                                break;
                        }

                    }
                    rowNumber++;
                    
                }

            }

        } catch (Exception e) {

        }
        return list;
    }
}
