package Crawl.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import Crawl.beans.Book;

public class SiteUtil {

	public static String getContentBasic(String urlChapter) throws IOException {
		Response response = Jsoup.connect(urlChapter).execute();
		
		String content = null;
		String check = response.url().toString();
		if (check.contains("chuong") == false) {
			content = "0";
		} else {

			Response resp = Jsoup.connect(urlChapter).ignoreContentType(true).timeout(1000).execute();
			String respStr = new String(resp.bodyAsBytes(), StandardCharsets.UTF_8);
			Document doc1 = Jsoup.parse(respStr);
			Elements ele = doc1.select("div#chapter-c");

			Map<String, String> arr = new HashMap<>();
			arr.put("&Agrave;", "À");
			arr.put("&Aacute;", "Á");
			arr.put("&Acirc;", "Â");
			arr.put("&Atilde;", "Ã");
			arr.put("&agrave;", "à");
			arr.put("&aacute;", "á");
			arr.put("&acirc;", "â");
			arr.put("&atilde;", "ã");
			arr.put("&Egrave;", "È");
			arr.put("&Eacute;", "É");
			arr.put("&Ecirc;", "Ê");
			arr.put("&egrave;", "è");
			arr.put("&eacute;", "é");
			arr.put("&ecirc;", "ê");
			arr.put("&Igrave;", "Ì");
			arr.put("&Iacute;", "Í");
			arr.put("&igrave;", "ì");
			arr.put("&iacute;", "í");
			arr.put("&Ograve;", "Ò");
			arr.put("&ograve;", "ò");
			arr.put("&Oacute;", "Ó");
			arr.put("&Ocirc;", "Ô");
			arr.put("&oacute;", "ó");
			arr.put("&Otilde;", "Õ");
			arr.put("&ocirc;", "ô");
			arr.put("&otilde;", "õ");
			arr.put("&Ugrave;", "Ù");
			arr.put("&Uacute;", "Ú");
			arr.put("&ugrave;", "ù");
			arr.put("&uacute;", "ú");
			arr.put("&Yacute;", "Ý");
			arr.put("&yacute;", "ý");
			arr.put("<br />", "");
			arr.put("<div class=\"visible-md visible-lg ads-responsive incontent-ad\" id=\"ads-chapter-pc-top\" align=\"center\" style=\"height:90px\"></div>",
					"");
			arr.put("<span>", "");

			content = ele.select("div#chapter-c").html();
			Iterator<Map.Entry<String, String>> iterator = arr.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				content = content.replace(entry.getKey(), entry.getValue());

			}
		}

		return content;

	}

	public static List<Book> getInfo(String url) throws IOException {
		String name = getName(url);

		// Take the exactly number
		int numOfChap = Integer.parseInt(getNumOfChap(url));
		List<Book> list = new ArrayList<Book>();
		Book book = new Book();
		book.setUrl(url);
		book.setName(name);
		book.setChapter(numOfChap);
		list.add(book);

		return list;
	}

	public static String getNumOfChap(String url) throws IOException {
		Document doc = Jsoup.connect(url).timeout(1000).get();
		// Find element class ('l-chapter') where have info aboout sum of the page
		// numbers of chapter
		Elements ele = doc.getElementsByClass("l-chapters");

		// Take the exactly number of totalchapter
		String a = ele.text();
		String parts[] = a.split(":");
		String b = parts[0].substring(7);

		return b;
	}

	public static String getName(String url) throws IOException {
		Response resp = Jsoup.connect(url).ignoreContentType(true).timeout(1000).execute();
// convert data from response to bytes
		String respStr = new String(resp.bodyAsBytes(), StandardCharsets.UTF_8);
		Document doc1 = Jsoup.parse(respStr);

//get the name of the book
		String name = doc1.title();
		return name;
	}

	public static String getChapterTitle(String urlChapter) throws IOException {
		Document doc = Jsoup.connect(urlChapter).timeout(1000).get();
		Elements ele = doc.getElementsByClass("chapter-title");
		String titleOfChap = ele.text();
		return titleOfChap;
	}
}
