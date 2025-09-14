package myPerson;

import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(){}
    public Person(String firstName, String lastName, int age) throws IllegalArgumentException{
        if(firstName == null || firstName.equals(""))
            throw new IllegalArgumentException("The first name cannot be null or empty");
        if(lastName == null || lastName.equals(""))
            throw new IllegalArgumentException("The last name cannot be null or empty");
        if(age < 0)
            throw new IllegalArgumentException("The age cannot be negative");
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName) throws IllegalArgumentException{
        if(firstName == null || firstName.equals("")) 
            throw new IllegalArgumentException("The first name cannot be null or empty");
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName) throws IllegalArgumentException{
        if(lastName == null || lastName.equals("")) 
            throw new IllegalArgumentException("The last name cannot be null or empty");
        this.lastName = lastName;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age) throws IllegalArgumentException{
        if(age < 0)
            throw new IllegalArgumentException("The age cannot be negative");
        this.age = age;
    }

    @Override
    public boolean equals(Object other){
        if(other == null)
            return false;
        if(other == this)
            return true;
        if(other instanceof Person){
            Person otherPerson = (Person)other;
            return Objects.equals(otherPerson.firstName, this.firstName) &&
                    Objects.equals(otherPerson.lastName, this.lastName) &&
                    otherPerson.age == this.age;
        }
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(firstName, lastName, age);
    }
}
