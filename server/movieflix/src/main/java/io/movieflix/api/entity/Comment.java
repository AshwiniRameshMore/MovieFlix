package io.movieflix.api.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This is the Comment class defining it's attributes and getter/setters
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Comment.findAllComments",query = "SELECT c from Comment c INNER JOIN c.video v WHERE v.videoId = :cid ORDER BY c.date DESC")
})
public class Comment {
	
	@Id
	private String commentId;
	private String comment;
	@ManyToOne
    private Video video;
	private String date;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Comment(){
		this.commentId = UUID.randomUUID().toString();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		this.date = df.format(dateobj);
	}
}
