package com.dynamicsfromflorida.com.repository;

import com.dynamicsfromflorida.com.entity.Textfile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class TextfileRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;

	public List<Textfile> findAll() {
		
		TypedQuery<Textfile> nameQuery =  em.createNamedQuery("find_all_textfiles", Textfile.class);
		List<Textfile> result = nameQuery.getResultList();
		return result;
	}
	
	public Textfile findById(Long id) {
		return em.find(Textfile.class, id);
	}

	public Textfile save(Textfile textfile) {

		if (textfile.getId() == null) {
			em.persist(textfile);
		} else {
			em.merge(textfile);
		}

		return textfile;
	}

	public void deleteById(Long id) {
		Textfile textfile = findById(id);
		em.remove(textfile);
	}

	public void saveOneTextfile() {
		Textfile textfile = new Textfile("ArchivoZZZ");

		
		em.persist(textfile);	
	}
	
	/*
	public void saveTextfileWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);

		Textfile Textfile = new Textfile("Mike");

		Textfile.setPassport(passport);
		em.persist(Textfile);	
	}
	
	public void someOperationToUnderstandPersistenceContext() {
		//Database Operation 1 - Retrieve Textfile
		Textfile Textfile = em.find(Textfile.class, 20001L);
		//Persistence Context (Textfile)
		
		
		//Database Operation 2 - Retrieve passport
		Passport passport = Textfile.getPassport();
		//Persistence Context (Textfile, passport)

		//Database Operation 3 - update passport
		passport.setNumber("E123457");
		//Persistence Context (Textfile, passport++)
		
		//Database Operation 4 - update Textfile
		Textfile.setName("Ranga - updated");
		//Persistence Context (Textfile++ , passport++)
	} */

}
