package com.yz1017.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

import com.yz1017.model.Person;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonServiceImplTest {
    
    private PersonServiceImpl personServiceImpl;
   
    private void setup() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
        final Field field = Person.Builder.class.getDeclaredField( "counter" );
        field.setAccessible( true );

        field.set(null, 1);
        
        personServiceImpl = new PersonServiceImpl();

        Person person1 = new Person();
        person1.setAge(10);
        person1.setLocale("locale1");
        person1.setName("name1");
        //will be saved as id 1
        personServiceImpl.save(person1);
    }


    @Test
    public void deletePersonTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
        setup();
        personServiceImpl.deleteById(1);
        Person savedPerson = personServiceImpl.findById(1);
        
        assertNull(savedPerson);
    }

    @Test
    public void findByIdTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
        setup();
        Person person = personServiceImpl.findById(1);
        
        assertEquals(10, person.getAge());
        assertEquals("name1", person.getName());
        assertEquals("locale1", person.getLocale());
        assertEquals(1, person.getId());
    }
    
    @Test
    public void savePersonTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
        setup();
        Person person2 = new Person();
        person2.setAge(20);
        person2.setLocale("locale2");
        person2.setName("name2");
        //will be saved as id 2
        personServiceImpl.save(person2);

        Person savedPerson1 = personServiceImpl.findById(1);
        
        assertEquals(10, savedPerson1.getAge());
        assertEquals("name1", savedPerson1.getName());
        assertEquals("locale1", savedPerson1.getLocale());
        assertEquals(1, savedPerson1.getId());

        Person savedPerson2 = personServiceImpl.findById(2);
        assertEquals(person2.getAge(), savedPerson2.getAge());
        assertEquals(person2.getName(), savedPerson2.getName());
        assertEquals(person2.getLocale(), savedPerson2.getLocale());
        assertNotEquals(person2.getId(), savedPerson2.getId());
        assertEquals(2, savedPerson2.getId());
        
    }
}
