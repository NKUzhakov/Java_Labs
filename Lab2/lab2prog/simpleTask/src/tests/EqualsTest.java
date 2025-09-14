package tests;

import myPerson.Person;
import org.junit.jupiter.api.Test;
import nl.jqno.equalsverifier.*;

public class EqualsTest {
    @Test
    public void test() {
        EqualsVerifier.simple().forClass(Person.class).verify();
    }    
}