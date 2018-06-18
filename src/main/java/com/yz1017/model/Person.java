package com.yz1017.model;

import java.util.concurrent.atomic.AtomicLong;

public class Person {
    //{ resourceType : Person, id : string, name : string, age : integer, locale : string }
	private long id;
	
	private String name;
	
	private int age;
	
	private String locale;

	public Person() {
	    id = -1L;
	}
	private Person(long id, String name, int age, String locale){
		this.id = id;
		this.name = name;
		this.age = age;
		this.locale = locale;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", locale=" + locale + "]";
	}

	/**
	 * Builder to create person
	 *
	 */
	public static class Builder {
	    private static long counter = 1;
	    
	    private String name;
	    
	    private int age;
	    
	    private String locale;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public Builder withLocale(String locale) {
            this.locale = locale;
            return this;
        }

        public Person build(){
            return new Person(counter++, name, age, locale);
        }
    }

}
