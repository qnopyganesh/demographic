package com.demographicwebapi.helper;

import org.springframework.web.multipart.MultipartFile;

public class Excelhelper {
    public static boolean checkExcelFormat(MultipartFile file) {
        // method to check whether the uploaded file is csv or not
        String contentType = file.getContentType();
        if (contentType.equals("text/csv")) {
            return true;
        } else {
            return false;
        }
    }
}
