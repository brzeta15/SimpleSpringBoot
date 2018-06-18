package com.yz1017.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yz1017.Application;
import com.yz1017.model.Person;
import com.yz1017.service.IPersonService;
import com.yz1017.util.ErrorDTO;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonControllerUnitTest {
    
    @InjectMocks
    private PersonController personController = new PersonController();

    @Mock
    private IPersonService personService;
 
    @Test
    public void getPersonTest() {
        Person person1 = new Person.Builder().withAge(10).withLocale("locale1").withName("name1").build();
        when(personService.findById(1)).thenReturn(person1);
        ResponseEntity<?> result = personController.getPerson(1);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(person1, result.getBody());
        
        verify(personService, times(1)).findById(1);
    }

    @Test
    public void getPersonFailureTest() {
        when(personService.findById(1)).thenReturn(null);
        ResponseEntity<?> result = personController.getPerson(1);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        
        verify(personService, times(1)).findById(1);
    }
    
    @Test
    public void addPersonTest() {
        Person person1 = new Person();
        Person person2 = new Person();
        when(personService.save(person1)).thenReturn(person2);
        ResponseEntity<?> result = personController.addPerson(person2);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(person2, result.getBody());
        
        verify(personService, times(1)).save(person1);
    }
    

    @Test
    public void deletePersonTest() {
        when(personService.deleteById(1)).thenReturn(new Person());
        ResponseEntity<?> result = personController.deletePerson(1);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(personService, times(1)).deleteById(1);
    }
    


    @Test
    public void deletePersonFailureTest() {
        when(personService.deleteById(1)).thenReturn(null);
        ResponseEntity<?> result = personController.deletePerson(1);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        verify(personService, times(1)).deleteById(1);
    }
}