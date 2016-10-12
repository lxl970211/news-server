package javabean;

import java.util.Set;

public class Comment {
	 private String token;
	    private String name;
	    private String commentTime;
	    private String content;
	    private int like;
	    private String newsId;//以新闻url为id
	    private int contra;
	    private Integer lou;
	    private String type;
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

		public void setLike(int like) {
			this.like = like;
		}
		public int getLike() {
			return like;
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
	    
	    
	    public void setType(String type) {
			this.type = type;
		}
	    
	    public String getType() {
			return type;
		}
}
