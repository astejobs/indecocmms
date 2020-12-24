package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.Message;

public interface MessageDaoInterface {

	List<Message> getAllMessages(String username);

	void persist(Message msg);

	List<Message> getAllMessagesOnType(String username, String type);
}
