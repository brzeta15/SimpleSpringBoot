package com.yz1017.service;


import com.yz1017.model.Person;

public interface IPersonService {

    /**
     * Retrieve the info of the person with the id specified
     * 
     * @param id            the id of the person to retrieve
     * 
     * @return              the person with the id, or null if not exists
     */
	Person findById(long id);
	
	 /**
     * Create a new instance of person with the Id auto incremented
     * 
     * @param person        the info of a person to save
     * 
     * @return          the copied version of person that saved
     */
	Person save(Person person);
	


    /**
     * Delete the person with Id
     * If no record with such id, then do nothing
     * 
     * @param id            the id of the person to delete
     * 
     * @return              the person with the id which get eleted, or null if not exists
     */
	Person deleteById(long id);
}
