package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import misc.GeneratePdf;
import model.User;

@WebServlet("/userDataPdf")
public class PdfServlet extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(PdfServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked GET operation [PdfServlet.class]");

		response.setContentType("application/pdf;charset=UTF-8");
		response.addHeader("Content-Disposition", "inline; filename=" + "user.pdf");
		ServletOutputStream out = response.getOutputStream();

		HttpSession session = request.getSession();

		ByteArrayOutputStream baos = GeneratePdf.getPdfFile((User) session.getAttribute("user"));
		baos.writeTo(out);

	}

}
