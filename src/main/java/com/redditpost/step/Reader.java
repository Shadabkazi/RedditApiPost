package com.redditpost.step;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Reader implements ItemReader<String> {

	private int count=0;
	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {

		/*
		 * The Verge RSS url : https://www.theverge.com/apple/rss/index.xml
		 * Forbes India RSS url : https://www.forbesindia.com/rssfeeds/rss_mostread.xml
		 */
		
		URL feedSource = new URL("https://www.theverge.com/rss/google/index.xml");
		SyndFeedInput input = new SyndFeedInput();
		//reads rss feed
		SyndFeed feed = input.build(new XmlReader(feedSource));
		List<SyndEntry> links = feed.getEntries();
		
		while(count<links.size()) {
			String postData=links.get(count).getTitle()+"!"+links.get(count).getUri();
			count++;
			return postData;
		}
		return null;
	}

}