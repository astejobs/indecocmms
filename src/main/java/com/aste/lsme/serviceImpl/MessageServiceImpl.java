package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.MessageDaoInterface;
import com.aste.lsme.domain.Message;
import com.aste.lsme.service.MessageServiceInterface;

@Service
@Transactional
@Component
public class MessageServiceImpl implements MessageServiceInterface {

	@Autowired
	MessageDaoInterface dao;
	
	@Override
	public List<Message> getAllMessages(String username) {
		
		return dao.getAllMessages(username) ;
	}

	@Override
	public void persist(Message msg) {
	dao.persist(msg);
		
	}

	@Override
	public List<Message> getAllMessagesOnType(String username, String type) {
		return dao.getAllMessagesOnType(username,type);
	}

}
