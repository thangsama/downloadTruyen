package Crawl.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
		
		
		String start = request.getParameter("start");
		String end = request.getParameter("end");
				
		
		ServletOutputStream os = response.getOutputStream();
		
		// create a new document
		Document doc = new Document(PageSize.A4);
		try {
			BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

			Font font = new Font(bf, 13);
			Font fontTitleChap = new Font(bf, 18);

			doc.addCreator("Thang Sama");

			PdfWriter.getInstance(doc, os);
			PdfWriter.getInstance(doc, new FileOutputStream("yourfilename.pdf"));
			doc.open();
			/// print pdf
			/// Check condition about number of StartChapter and EndChapter
			
			
			if(start=="") {
				if(end!="") {
				int conEnd= Integer.parseInt(end);
				String urlChapter = url + "chuong-" + conEnd + "/";
				content = SiteUtil.getContentBasic(urlChapter);
				titleOfChap = SiteUtil.getChapterTitle(urlChapter);

				doc.add(new Paragraph(titleOfChap, fontTitleChap));
				doc.add(new Paragraph(content, font));
				doc.add(new Paragraph(breakline, font));
				doc.add(Chunk.NEWLINE);
			} else {
				response.sendRedirect(request.getServletPath()+"/infoBook");
				}
			}else if(start!="") {
				if(end!="") {
					int conStart=Integer.parseInt(start);
					int conEnd= Integer.parseInt(end);
					if (conStart > conEnd) {

						for (int i = conEnd; i < conStart + 1; ++i) {

							String urlChapter = url + "chuong-" + i + "/";
							if (SiteUtil.getContentBasic(urlChapter) == "0") {
								i = i++;

							} else {
								content = SiteUtil.getContentBasic(urlChapter);
								titleOfChap = SiteUtil.getChapterTitle(urlChapter);

								doc.add(new Paragraph(titleOfChap, fontTitleChap));
								doc.add(new Paragraph(content, font));
								doc.add(new Paragraph(breakline, font));
								doc.add(Chunk.NEWLINE);
							}
						}
					} else {

						for (int i = conStart; i < conEnd + 1; ++i) {

							String urlChapter = url + "chuong-" + i + "/";
							if (SiteUtil.getContentBasic(urlChapter) == "0") {
								i = i++;

							} else {
								content = SiteUtil.getContentBasic(urlChapter);
								titleOfChap = SiteUtil.getChapterTitle(urlChapter);
								doc.add(new Paragraph(titleOfChap, fontTitleChap));
								doc.add(new Paragraph(content, font));
								doc.add(new Paragraph(breakline, font));
								doc.add(Chunk.NEWLINE);
							}
						}
					}
				}else {
					int conStart=Integer.parseInt(start);
					String urlChapter = url + "chuong-" + conStart + "/";
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
