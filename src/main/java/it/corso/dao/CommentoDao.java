package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Commento;

public interface CommentoDao extends CrudRepository<Commento, Integer>{

}
