package com.yz1017.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yz1017.model.Person;
import com.yz1017.service.IPersonService;
import com.yz1017.util.ErrorDTO;


@Controller
public class PersonController {
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private IPersonService personService;
    
    /**
     * Create a new person with Id auto incrementing
     * 
     * @param person        the person info to save
     * @return              the new person saved
     */
    @RequestMapping(value = "/resources/data/", 
            method = RequestMethod.PUT)
    public ResponseEntity<?> addPerson(@RequestBody Person person) {
        Person personCopied = personService.save(person);
        LOG.info("Saved person with id {}", personCopied.getId());
        return new ResponseEntity<Person>(personCopied, HttpStatus.OK);
    }
    
    
    /**
     * Get the info of a person with Id
     * 
     * @param id            the id of the person to retrieve
     * @return              the info of the person with id
     *                      or 404 error with message
     */
    @RequestMapping(value = "/resources/data/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getPerson(@PathVariable("id") long id) {
        LOG.info("Finding person with id {}", id);
 
        Person currentPerson = personService.findById(id);
 
        if (currentPerson == null) {
            LOG.error("Unable to find person with id {}.", id);
            return new ResponseEntity(new ErrorDTO("Unable to find person with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        return new ResponseEntity<Person>(currentPerson, HttpStatus.OK);
    }

    /**
     * To delete the person with Id
     * 
     * @param id            the id of the person to delete
     * @return              empty if the person deleted
     *                      or 404 error with message
     */
    @RequestMapping(value = "/resources/data/{id}", 
            method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
        LOG.info("Deleting person with id {}", id);

        Person person = personService.deleteById(id);
        if (person == null) {
            LOG.error("Unable to delete. Person with id {} not found.", id);
            return new ResponseEntity(new ErrorDTO("Unable to delete. Person with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(HttpStatus.OK);
    }
    
    
    @RequestMapping("/person")
    public String person(Model model) {
        return "person";
    }
}
