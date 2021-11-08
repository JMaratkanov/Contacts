package com.Johann_M.repository;

import com.Johann_M.entities.Contact;
import org.springframework.data.repository.CrudRepository;


public interface ContactRepository extends CrudRepository<Contact,Integer> {

}
