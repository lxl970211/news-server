package javabean;

public class Comment {
	 private String token;
	    private String name;
	    private String commentTime;
	    private String content;
	    private int zan;
	    private String newsId;//以新闻url为id
	    private int contra;

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
}
