package javabean;

import java.util.Set;

public class Comment {
	 private String token;
	    private String name;
	    private String commentTime;
	    private String content;
	    private int zan;
	    private String newsId;//������urlΪid
	    private int contra;
	    private Integer lou;
	    
	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getCommentTime() {
	        return commentTime;
	    }

	    public void setCommentTime(String commentTime) {
	        this.commentTime = commentTime;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

	    public int getZan() {
	        return zan;
	    }

	    public void setZan(int zan) {
	        this.zan = zan;
	    }

	    public void setNewsId(String newsId) {
	        this.newsId = newsId;
	    }

	    public String getNewsId() {
	        return newsId;
	    }
	    public void setContra(int contra) {
			this.contra = contra;
		}
	    public int getContra() {
			return contra;
		}
	    
	    
	    public Integer getLou() {
			return lou;
		}
	    public void setLou(Integer lou) {
			this.lou = lou;
		}
}
