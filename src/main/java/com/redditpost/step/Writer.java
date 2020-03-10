package com.redditpost.step;

import java.util.List;
import java.util.Scanner;

import org.springframework.batch.item.ItemWriter;

public class Writer implements ItemWriter<String> {

	private Scanner sc;
	@Override
	public void write(List<? extends String> messages) throws Exception {
		sc=new Scanner(messages.get(0));
		sc.useDelimiter("!");
		while(sc.hasNext())
			//TODO: Post content to Reddit using API
		System.out.println("hello- "+sc.next());
	}

}