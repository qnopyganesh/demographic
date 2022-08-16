package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.print.Account;
import com.example.demo.print.ExportInvoice;

@SpringBootApplication
public class InvoiceFossgenApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceFossgenApplication.class, args);
	}
	
	public static Account getFossgen() {
		Account account =new Account();
		account.setInputCSV("fossen_invoice.csv");
		account.setMonth("JULLY-2022");
		account.setStanderdHr(160);
		account.setStartInvoiceNo(40);
		account.setTemplate("Fossgen");
		 
		return account;
	}
	
	public static Account getMd4ks() {
		Account account =new Account();
		account.setInputCSV("md4ks_invoice.csv");
		account.setMonth("JULLY-2022");
		account.setStanderdHr(187);
		account.setStartInvoiceNo(40);
		account.setTemplate("Md4ks");
		
		return account;
	}
	@Override
    public void run(String... args) {
		new ExportInvoice().exportInvocie(getFossgen());
    }

}
