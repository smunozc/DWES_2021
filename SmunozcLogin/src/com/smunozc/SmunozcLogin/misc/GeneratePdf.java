package com.smunozc.SmunozcLogin.misc;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.smunozc.SmunozcLogin.dao.UserDAOI;
import com.smunozc.SmunozcLogin.dao.UserDAOImpl;
import com.smunozc.SmunozcLogin.model.User;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdf {
	
	private static UserDAOI userDAO = new UserDAOImpl();

    public static ByteArrayOutputStream getPdfFile(User user) {

        Document document = new Document();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try {
        	
        	//Check how many columns needs the table to fit the information
        	int colnum;
        	int[] colwidths;
        	if(userDAO.hasDetailedData(user)) {
        		colnum = 4;
        		colwidths = new int[] {1,1,1,1};
        	} else {
        		colnum = 3;
        		colwidths = new int[] {1,1,1};
        	}
        	
        	//Create table
            PdfPTable table = new PdfPTable(colnum);
            table.setWidthPercentage(95);
            table.setWidths(colwidths);

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Username", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Surname", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            if(userDAO.hasDetailedData(user)) {
            	hcell = new PdfPCell(new Phrase("NIF", headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);
            }

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(user.getUsername()));
            cell.setPadding(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(user.getName()));
            cell.setPadding(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(user.getSurname()));
            cell.setPadding(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            if(userDAO.hasDetailedData(user)) {
            	
            	cell = new PdfPCell(new Phrase(user.getNif()));
                cell.setPadding(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                hcell = new PdfPCell(new Phrase("Weight", headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);
                
                hcell = new PdfPCell(new Phrase("Height", headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);
                
                hcell = new PdfPCell(new Phrase("Academic Formation", headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);
                
                hcell = new PdfPCell(new Phrase("Hobbies", headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);
                
                cell = new PdfPCell(new Phrase(user.getWeight()));
                cell.setPadding(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(user.getHeight()));
                cell.setPadding(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(user.getAcademicFormation()));
                cell.setPadding(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(user.getHobbies()));
                cell.setPadding(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            	
            }

            PdfWriter.getInstance(document, bout);
            document.open();
            document.add(table);
            
            document.close();
            
        } catch (DocumentException ex) {
        
            Logger.getLogger(GeneratePdf.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bout;
    }
}
