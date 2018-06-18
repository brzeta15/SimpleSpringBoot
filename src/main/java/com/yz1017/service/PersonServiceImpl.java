package com.yz1017.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yz1017.model.Person;

@Service("personService")
public class PersonServiceImpl implements IPersonService{
	
	private Map<Long, Person> persons = new HashMap<>();
	

	/**
     * Delete the person with Id
     * If no record with such id, then do nothing
     * 
     * @param id            the id of the person to delete
     * 
     * @return              the person with the id which get eleted, or null if not exists
	 */
    public Person deleteById(long id) {
        return persons.remove(id);
    }
    
    /**
     * Retrieve the info of the person with the id specified
     * 
     * @param id            the id of the person to retrieve
     * 
     * @return              the person with the id, or null if not exists
     */
	public Person findById(long id) {
		return persons.get(id);
	}


    /**
     * Create a new instance of person with the Id auto incremented
     * 
     * @param person        the info of a person to save
     * 
     * @return          the copied version of person that saved
     */
	public Person save(Person person) {
	    Person personCopied = new Person.Builder()
	                                    .withAge(person.getAge())
	                                    .withName(person.getName())
	                                    .withLocale(person.getLocale())
	                                    .build();
		persons.put(personCopied.getId(), personCopied);
		
		return personCopied;
	}
}
