package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.MessageDaoInterface;
import com.aste.lsme.domain.Message;

@Repository
public class MessageDaoImpl implements MessageDaoInterface {

	private EntityManager em;

	public EntityManager getEntitymanager() {
		return em;
	}

	@PersistenceContext
	public void setEntitymanager(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public List<Message> getAllMessages(String username) {
		TypedQuery<Message> query =em.
				  createQuery("Select m from Message m where m.createdDate in(select max(m.createdDate) from Message m group by m.type) and m.userDetail.username = :username  Order By m.createdDate Desc",Message.class)
	  				.setParameter("username", username);
    		
		return  query.getResultList();
		
		
		
	}

	@Override
	public void persist(Message msg) {
		System.out.println("in persist");
		em.persist(msg);
		
	}

	@Override
	public List<Message> getAllMessagesOnType(String username, String type) {
	
		Query query = em.
				  createQuery("Select m from Message m where m.userDetail.username = :username and m.type=:type Order By m.createdDate Desc ")
				  				.setParameter("username", username).setParameter("type", type);
		return  query.getResultList();
		
	}

}
