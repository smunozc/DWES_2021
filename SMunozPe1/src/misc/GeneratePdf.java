package misc;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.User;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdf {
	
	//private static UserDAOI userDAO = new UserDAOImpl();

    public static ByteArrayOutputStream getPdfFile(User user) {

        Document document = new Document();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try {
        	
        	//Check how many columns needs the table to fit the information
        	int colnum;
        	int[] colwidths;
        	
    		colnum = 5;
    		colwidths = new int[] {1,1,1,1,1};
        	
        	//Create table
            PdfPTable table = new PdfPTable(colnum);
            table.setWidthPercentage(95);
            table.setWidths(colwidths);

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Username", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Fullname", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Email", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Role", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("State", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(user.getUsername()));
            cell.setPadding(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(user.getFullname()));
            cell.setPadding(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(user.getEmail()));
            cell.setPadding(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(user.getRole()));
            cell.setPadding(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(user.getUserState()));
            cell.setPadding(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

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
