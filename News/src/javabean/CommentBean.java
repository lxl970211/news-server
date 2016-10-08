package javabean;

import java.util.List;

public class CommentBean {
	
	private int commentCount;
	private List<Comment> list;
	public void setList(List<Comment> list) {
		this.list = list;
	}
	public List<Comment> getList() {
		return list;
	}
	
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
}
