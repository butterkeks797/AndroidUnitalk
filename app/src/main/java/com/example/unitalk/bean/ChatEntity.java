package com.example.unitalk.bean;

import java.io.Serializable;

public class ChatEntity implements Serializable{
	public static final int  RECEIVE = 0;
	public static final int SEND = 1;

	//private int id;
	private int senderId;
	private int receiverId;
	private int messageType; //0为接收，1为发送
	private String sendDate;
	private String content;

	public ChatEntity() {
	}

	public ChatEntity(int senderId, int receiverId, int messageType) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.messageType = messageType;
	}

	public ChatEntity(int senderId, int receiverId, int messageType, String sendDate, String content) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.messageType = messageType;
		this.sendDate = sendDate;
		this.content = content;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
