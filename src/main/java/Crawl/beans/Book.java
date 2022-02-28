package Crawl.beans;

public class Book {
	private String name =null;
	private String title = null;
	private String content = null;
	private int chapter = 0;
	private String url = null;
	
	public Book () {};
	public Book (String name,String title, String content, int chapter, String url) {
		this.name=name;
		this.title=title;
		this.content=content;
		this.chapter=chapter;
		this.url=url;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	
	
}
