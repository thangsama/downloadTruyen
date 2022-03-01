package Crawl.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tomcat.util.codec.binary.StringUtils;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import Crawl.utils.SiteUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/downloadPart")
public class DownloadSpecificPartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DownloadSpecificPartServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/pdf; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/pdf; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String breakline = "---------------------****---------------------";
		String url = request.getParameter("url");
		// Create style, font size
		File fontFile = new File(this.getClass().getClassLoader().getResource("vuArial.ttf").getPath());
		String content = null;
		String titleOfChap = null;

		// Making a file name (with name + number of chapter
		String name = SiteUtil.getName(url);
		byte[] nameAsBytes = StringUtils.getBytesUtf8(name);
		String utf8EncodedName = StringUtils.newStringUtf8(nameAsBytes);

		String start = request.getParameter("start");
		String end = request.getParameter("end");

		ServletOutputStream os = response.getOutputStream();

		// create a new document
		Document doc = new Document(PageSize.A4);
		try {
			BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

			Font font = new Font(bf, 13);
			Font fontTitleChap = new Font(bf, 18);

			if (start == "") {
				if (end != "") {
					/// Check condition about number of StartChapter and EndChapter
					int conEnd = Integer.parseInt(end);
					doc.addCreator("Thang Sama");
					String urlChapter = url + "chuong-" + conEnd + "/";

					// open new window with file converted on server
					PdfWriter.getInstance(doc, os);

					// save file to Desktop
					PdfWriter.getInstance(doc, new FileOutputStream(utf8EncodedName + "chuong" + conEnd + ".pdf"));
					doc.open();
					/// print pdf
					content = SiteUtil.getContentBasic(urlChapter);
					titleOfChap = SiteUtil.getChapterTitle(urlChapter);
					doc.add(new Paragraph(titleOfChap, fontTitleChap));
					doc.add(new Paragraph(content, font));
					doc.add(new Paragraph(breakline, font));
					doc.add(Chunk.NEWLINE);
				} else {
					response.sendRedirect(request.getServletPath() + "/infoBook");
				}
			} else if (start != "") {
				if (end != "") {
					int conStart = Integer.parseInt(start);
					int conEnd = Integer.parseInt(end);
					if (conStart > conEnd) {
						doc.addCreator("Thang Sama");

						// open new window with file converted on server
						PdfWriter.getInstance(doc, os);

						// save file to Desktop
						PdfWriter.getInstance(doc, new FileOutputStream(utf8EncodedName + "-chuong" + conEnd +"---"+conStart+".pdf"));
						for (int i = conEnd; i < conStart + 1; ++i) {

							String urlChapter = url + "chuong-" + i + "/";
							if (SiteUtil.getContentBasic(urlChapter) == "0") {
								i = i++;

							} else {
								
								doc.open();
								/// print pdf
								content = SiteUtil.getContentBasic(urlChapter);
								titleOfChap = SiteUtil.getChapterTitle(urlChapter);
								doc.add(new Paragraph(titleOfChap, fontTitleChap));
								doc.add(new Paragraph(content, font));
								doc.add(new Paragraph(breakline, font));
								doc.add(Chunk.NEWLINE);
							}
						}
					} else {
						doc.addCreator("Thang Sama");
						PdfWriter.getInstance(doc, os);
						// open new window with file converted on server
						

						// save file to Desktop
						PdfWriter.getInstance(doc, new FileOutputStream(utf8EncodedName + "-chuong" + conStart+"---"+conEnd + ".pdf"));
						doc.open();
						for (int i = conStart; i < conEnd + 1; ++i) {

							String urlChapter = url + "chuong-" + i + "/";
							if (SiteUtil.getContentBasic(urlChapter) == "0") {
								i = i++;

							} else {
								
								/// print pdf
								content = SiteUtil.getContentBasic(urlChapter);
								titleOfChap = SiteUtil.getChapterTitle(urlChapter);
								doc.add(new Paragraph(titleOfChap, fontTitleChap));
								doc.add(new Paragraph(content, font));
								doc.add(new Paragraph(breakline, font));
								doc.add(Chunk.NEWLINE);
							}
						}
					}
				} else {
					int conStart = Integer.parseInt(start);
					doc.addCreator("Thang Sama");
					String urlChapter = url + "chuong-" + conStart + "/";

					// open new window with file converted on server
					PdfWriter.getInstance(doc, os);

					// save file to Desktop
					PdfWriter.getInstance(doc, new FileOutputStream(utf8EncodedName + "-chuong-" + conStart + ".pdf"));
					doc.open();
					/// print pdf
					content = SiteUtil.getContentBasic(urlChapter);
					titleOfChap = SiteUtil.getChapterTitle(urlChapter);
					doc.add(new Paragraph(titleOfChap, fontTitleChap));
					doc.add(new Paragraph(content, font));
					doc.add(new Paragraph(breakline, font));
					doc.add(Chunk.NEWLINE);
				}

			}

			doc.close();
		} catch (Exception a) {
			a.printStackTrace();

		}
	}
}
