package com.aste.lsme.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Message")
public class Message {

	 
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	Long id;
	
	@Column(name="message_title")
	String title;
	
	@Column(name="message_text")
	String text;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(name="created_date")
	LocalDateTime createdDate;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	UserDetail userDetail;
	
	@Column(name="type")
	String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getCreatedDate() {
		return createdDate.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli();
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Message() {
		super();	
	}
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Message(Long id, String title, String text, LocalDateTime createdDate, UserDetail userDetail,String type) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.createdDate = createdDate;
		this.userDetail = userDetail;
		this.type=type;
	}

	
	
}
