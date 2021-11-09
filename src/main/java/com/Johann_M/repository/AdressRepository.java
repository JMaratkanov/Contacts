package com.Johann_M.repository;

import com.Johann_M.entities.Adress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdressRepository extends CrudRepository<Adress, Integer> {
    List<Adress> findByContactID(Integer contactID);
}
