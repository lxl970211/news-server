package javabean;

import java.util.Set;

public class Comment {
	 private String token;
	    private String name;
	    private String commentTime;
	    private String content;
	    private int like;
	    private String newsId;//������urlΪid
	    private Integer lou;
	    private String title;
	    private String head;
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

	    
	    public Integer getLou() {
			return lou;
		}
	    public void setLou(Integer lou) {
			this.lou = lou;
		}
	    
	    
	    
	    public void setTitle(String title) {
			this.title = title;
		}
	    
	    public String getTitle() {
			return title;
		}
	    
	    
	    public void setHead(String head) {
			this.head = head;
		}
	    
	    public String getHead() {
			return head;
		}
}
