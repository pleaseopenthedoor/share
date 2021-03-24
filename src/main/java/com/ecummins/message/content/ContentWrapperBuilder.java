package com.ecummins.message.content;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.ecummins.message.type.SendType;

	
public class ContentWrapperBuilder{
		
	private Set<SendType> sendTypes;
	
	private Set<String> contents;
	
	
	private ContentWrapperBuilder() {
		this.sendTypes = new LinkedHashSet<>();
		this.contents = new LinkedHashSet<>();
	}
	
	public ContentWrapperBuilder setSendTypes(SendType... sendTypes) {
		this.sendTypes.addAll(Arrays.asList(sendTypes));
		return this;
	}
	
	public ContentWrapperBuilder setContents(String... contents) {
		this.contents.addAll(Arrays.asList(contents));
		return this;
	}
	
	
	public static ContentWrapperBuilder initBuilder() {
		return new ContentWrapperBuilder();
	}
	
	public ContentWrapper build() {
		SendType[] sendTypes = this.sendTypes.stream().toArray(i -> new SendType[this.sendTypes.size()]);
		String[] contents = this.contents.stream().toArray(i -> new String[this.contents.size()]);
		return new ContentWrapper(contents, sendTypes);
	}
	
}
