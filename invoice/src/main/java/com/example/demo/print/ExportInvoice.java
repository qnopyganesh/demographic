package com.example.demo.print;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;

public class ExportInvoice {

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public void exportPdf(List<AccountDtl> dtl, Account account) {

	 	try {
			//Map<String, byte[]> fontMap=new HashMap<>();
			
			//fontMap.put("Times New Roman",FileUtils.readFileToByteArray(new File("font/times.ttf")));
		 
	 		FontProvider fontProvider = new FontProvider();
			fontProvider.addStandardPdfFonts();
			// The noto-nashk font file (.ttf extension) is placed in the resources
			// fontProvider.addDirectory(SRC);

			ConverterProperties props = new ConverterProperties();
			props.setFontProvider(fontProvider);
			
			 
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
			String output = "output-" + account.getTemplate();
			FileUtils.deleteQuietly(new File(output));
			try {
				FileUtils.forceMkdir(new File(output));
				System.out.println(new File(output).getAbsolutePath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String dateStr = formatter.format(LocalDate.now());
			String nextDateStr = formatter.format(LocalDate.now().plusDays(5));

			System.out.println(new File("template/" + account.getTemplate() + "/index.html").getAbsolutePath());
			String html = FileUtils.readFileToString(new File("template/" + account.getTemplate() + "/index.html"));
			int i = 0;
			for (AccountDtl accountDtl : dtl) {
				String data = new String(html);
				data=data.replaceAll("#S#TODAY_DATE#E#", dateStr);
				data=	data.replaceAll("#S#DUE_DATE#E#", nextDateStr);
				data=data.replaceAll("#S#INVICE_NO#E#", "F0" + (account.getStartInvoiceNo() + i));
				data=data.replaceAll("#S#ACTIVITY#E#", accountDtl.getName() + "<br/>" + accountDtl.getDeignation() + "<br/>"
						+ accountDtl.getProject());
				data=data.replaceAll("#S#TOTAL#E#", accountDtl.getTotal() + "");
				data=data.replaceAll("#S#SUBTOTAL#E#", accountDtl.getTotal() + "");
				data=data.replaceAll("#S#CGST#E#", accountDtl.getCgst() + "");
				data=data.replaceAll("#S#SGST#E#", accountDtl.getSgst() + "");
				data=data.replaceAll("#S#GST#E#", accountDtl.getGst() + "");
				data=data.replaceAll("#S#TOTALPLUSGST#E#", accountDtl.getTotalPlusGst() + "");
				data=data.replaceAll("#S#WORD#E#", accountDtl.getTotalInStr() + "");
				String name = output + "/Invoice-F0" +( i+account.getStartInvoiceNo()) + "-" + account.getTemplate() + "-"
						+ accountDtl.getName().replaceAll(" ", "_") + "-" + account.getMonth();
				FileUtils.write(new File(name + ".html"), data);
				System.out.println(name);
				
				HtmlConverter.convertToPdf(data, new FileOutputStream(name + ".pdf"), props);
				i++;
			}

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	public void exportInvocie(Account account) {
		try {

			String fileData = FileUtils.readFileToString(new File(account.getInputCSV()));
			String rows[] = fileData.split("\n");
			List<AccountDtl> list = new ArrayList();
			int i = 0;
			for (String row : rows) {
				i++;
				System.out.println("processing row " + i);
				String cols[] = row.split(",");
				if (cols.length != 5) {
					System.out.println("For row : Coloumn Size is :" + cols.length);
					return;
				}

				AccountDtl dtl = new AccountDtl();
				if (cols[0] == null || "".equals(cols[0])) {
					System.out.println("For row : " + i + "  Provide Name");
					return;
				}
				dtl.setName(cols[0]);
				if (cols[1] == null || "".equals(cols[1])) {
					System.out.println("For row : " + i + "  Provide Designation ");
					return;
				}
				dtl.setDeignation(cols[1]);

				if (cols[2] == null || "".equals(cols[2])) {
					System.out.println("For row : " + i + "  Provide project name ");
					return;
				}
				dtl.setProject(cols[2]);

				if (cols[3] == null || "".equals(cols[3])) {
					System.out.println("For row : " + i + "  Provide Hours ");
					return;
				}
				dtl.setHr(Integer.parseInt(cols[3]));

				if (cols[4] == null || "".equals(cols[4])) {
					System.out.println("For row : " + i + "  Provide Rate ");
					return;
				}
				dtl.setCost(Double.parseDouble(cols[4]));

				dtl.setTotal(round(dtl.getHr() * (dtl.getCost() / account.getStanderdHr()), 2));

				dtl.setCgst(round(dtl.getTotal() * 0.09, 2));
				dtl.setSgst(round(dtl.getTotal() * 0.09, 2));
				dtl.setGst(round(dtl.getCgst() * 2, 2));
				dtl.setTotalPlusGst(round(dtl.getGst() + dtl.getTotal(), 0));
				dtl.setTotalInStr(convertNumberToWord.convertNumber(dtl.getTotalPlusGst().longValue()));
				list.add(dtl);
				System.out.println("Completed row " + i);
			}

			exportPdf(list, account);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
