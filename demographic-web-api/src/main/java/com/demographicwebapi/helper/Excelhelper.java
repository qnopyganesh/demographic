package com.demographicwebapi.helper;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.demographicwebapi.demographicwebapi.models.NameIndex;
import com.google.gson.Gson;

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

    public static List<NameIndex> convertExcelToListOfNameIndex(InputStream is, Long algoId, boolean isSurname,
            String fileName) {
        List<NameIndex> list = new ArrayList<>();
        try (
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withAllowMissingColumnNames());) {
            List<CSVRecord> csvRecords = csvParser.getRecords();
            int totalNumberofRow = csvRecords.size();
            for (int i = 1; i < totalNumberofRow; i += 2) {
                NameIndex n = new NameIndex();
                CSVRecord curRecord = csvRecords.get(i);
                CSVRecord encodedRecord = csvRecords.get(i + 1);
                n.setName(curRecord.get(2));
                n.setEncode(encodedRecord.get(2));
                ArrayList<String> name_jsonArray = new ArrayList<>();
                ArrayList<String> encode_jsonArray = new ArrayList<>();
                for(int j = 3; j < curRecord.size() ; j ++ ){
                    name_jsonArray.add(curRecord.get(j));
                    encode_jsonArray.add(encodedRecord.get(j));
                }
                Gson gson = new Gson();
                n.setName_json(gson.toJson(name_jsonArray));
                n.setEncode_json(gson.toJson(encode_jsonArray));
                n.setAlgo(algoId);
                n.setType(isSurname?'s':'f');
                list.add(n);
                
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
}
