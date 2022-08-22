package com.demographicwebapi.demographicwebapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.demographicwebapi.demographicwebapi.models.NameIndex;
import com.demographicwebapi.helper.Excelhelper;

import ch.qos.logback.classic.Logger;

@SpringBootTest
class DemographicWebApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testfile(){
		// File file = new File("src/main/resources/Nysiis_name.csv.result.csv");
		// try {
		// 	InputStream is  = new FileInputStream(file);
		// 	List<NameIndex> ls = new ArrayList<>();
		// 	ls = Excelhelper.convertExcelToListOfNameIndex(is, 5L);
		// 	for(NameIndex ne : ls){
		// 		System.out.println(ne.getJson());
		// 	}
		// } catch (FileNotFoundException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
	}

}
