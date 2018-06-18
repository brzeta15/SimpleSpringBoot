package com.yz1017.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yz1017.controller.PersonController;
import com.yz1017.model.Person;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonController service;

    @Test
    public void getPersonService() throws Exception {
        Person person = new Person();
        person.setName("name1");
        ResponseEntity<?> personEntity = new ResponseEntity<Person>(person, HttpStatus.OK);
        doReturn(personEntity).when(service).getPerson(1);
        this.mockMvc.perform(get("/resources/data/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("name1")));
    }
    
    @Test
    public void deletePersonServiceOK() throws Exception {
        ResponseEntity<?> noEntity = new ResponseEntity<Person>(HttpStatus.OK);
        doReturn(noEntity).when(service).deletePerson(1);
        this.mockMvc.perform(delete("/resources/data/1"))
                    .andDo(print())
                    .andExpect(status().isOk());
    }
    
    @Test
    public void addPersonService() throws Exception {
        Person person = new Person();
        person.setName("name1");
        ResponseEntity<?> personEntity = new ResponseEntity<Person>(person, HttpStatus.OK);
        doReturn(personEntity).when(service).addPerson(person);
        this.mockMvc.perform(put("/resources/data/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(person)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("name1")));
    }
}
