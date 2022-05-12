package com.merchandise.services;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.PdfWriter;

import com.merchandise.entities.Customer;
import com.merchandise.entities.Supplier;

public abstract class SaveToPdfFile {
	public static final String CUSTOMER_CREATED_PDF = "D:\\customer.pdf";
	public static final String SUPPLIER_CREATED_PDF = "D:\\supplier.pdf";
	
	public static void writeCustomer(List<Customer> customers) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(CUSTOMER_CREATED_PDF));
			document.open();
			Table table = new Table(7); 
		      table.setPadding(5);
		      table.setSpacing(1);
			//Setting header
		      Cell cell = new Cell("Customer Information");
		      cell.setHeader(true);
		      cell.setVerticalAlignment(VerticalAlignment.CENTER);
		      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
		      cell.setColspan(7);
		      cell.setBackgroundColor(Color.LIGHT_GRAY);
		      table.addCell(cell);
		      
		      table.addCell(new Phrase("Partner ID"));
		      table.addCell(new Phrase("Partner Name"));          
		      table.addCell(new Phrase("City"));
		      table.addCell(new Phrase("State"));
		      table.addCell(new Phrase("Credit Limit"));
		      table.addCell(new Phrase("Phone Number"));
		      table.addCell(new Phrase("Email Id"));
		      table.endHeaders();
		      
		      for (Customer customer : customers) {
				table.addCell(Integer.toString(customer.getPartnerId()));
				table.addCell(customer.getPartnerName());
				table.addCell(customer.getCity());
				table.addCell(customer.getState());
				table.addCell(Double.toString(customer.getCreditLimit()));
				table.addCell(customer.getPhoneNo());
				table.addCell(customer.getEmail());
			}
		      document.add(table);
		} catch (DocumentException | IOException de) {
			System.err.println(de.getMessage());
		}
		document.close();
	}
	
	public static void writeSupplier(List<Supplier> suppliers) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(SUPPLIER_CREATED_PDF));
			document.open();
			Table table = new Table(6); 
		      table.setPadding(5);
		      table.setSpacing(1);
		      table.setWidth(100);
			//Setting header
		      Cell cell = new Cell("Supplier Information");
		      cell.setHeader(true);
		      cell.setVerticalAlignment(VerticalAlignment.CENTER);
		      cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
		      cell.setColspan(6);
		      cell.setBackgroundColor(Color.LIGHT_GRAY);
		      table.addCell(cell);
		      
		      table.addCell(new Phrase("Partner ID"));
		      table.addCell(new Phrase("Partner Name"));          
		      table.addCell(new Phrase("City"));
		      table.addCell(new Phrase("State"));
		      table.addCell(new Phrase("Credit Balance"));
		      table.addCell(new Phrase("Driving License Number"));
		      table.endHeaders();
		      
		      for (Supplier supplier: suppliers) {
				table.addCell(Integer.toString(supplier.getPartnerId()));
				table.addCell(supplier.getPartnerName());
				table.addCell(supplier.getCity());
				table.addCell(supplier.getState());
				table.addCell(Double.toString(supplier.getCreditBalance()));
				table.addCell(supplier.getDrivingLicenseNumber());
			}
		      document.add(table);
		} catch (DocumentException | IOException de) {
			System.err.println(de.getMessage());
		}
		document.close();
	}
	
	

}
