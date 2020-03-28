package com.redditpost.model;

import javax.validation.constraints.NotNull;

public class PostData {
	@NotNull
    private String title;
 
    @NotNull
    private String url;
 
    @NotNull
    private String sr;
 
    private String kind;
    
    private String resubmit;
    
    private String send_replies;
    
    private String api_type;

	public String getResubmit() {
		return resubmit;
	}

	public void setResubmit(String resubmit) {
		this.resubmit = resubmit;
	}

	public String getSend_replies() {
		return send_replies;
	}

	public void setSend_replies(String send_replies) {
		this.send_replies = send_replies;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSr() {
		return sr;
	}

	public void setSr(String sr) {
		this.sr = sr;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	

	public String getApi_type() {
		return api_type;
	}

	public void setApi_type(String api_type) {
		this.api_type = api_type;
	}

	@Override
	public String toString() {
		return "PostData [title=" + title + ", url=" + url + ", sr=" + sr + ", kind=" + kind + ", resubmit=" + resubmit
				+ ", send_replies=" + send_replies + ", api_type=" + api_type + "]";
	}
	
    
}
