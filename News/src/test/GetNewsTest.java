package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.GetNews;

public class GetNewsTest {

	private GetNews getNews;
	@Before
	public void setUp() throws Exception {
		getNews = new GetNews();
	}

	@Test
	public void testGetJson() {
		System.out.println(getNews.getJson(getNews.newsUrl("top")));
	}

}
