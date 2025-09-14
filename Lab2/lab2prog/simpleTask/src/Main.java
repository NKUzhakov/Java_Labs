import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;

import myPerson.*;  
// import tests.*;

public class Main {
    public static void main(String[] args){
        Person person = new Person("Ivan", "Ivanov", 20);

        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting(); 
        Gson gson = builder.create();

        String serializedPerson = gson.toJson(person);
        System.out.println("person JSON:\n" + serializedPerson);
        Person deserializedPerson = gson.fromJson(serializedPerson, Person.class);
        System.out.println("person.equals(deserializedPerson): " + person.equals(deserializedPerson));
        
        // EqualsTest et = new EqualsTest();
        // et.test();

    }
    
}

//compile: javac -cp "./lib/*;src" -d ./out src/*.java src/myPerson/*.java src/tests/*.java 
//start: java -cp "./lib/gson-2.13.1.jar;./lib/equalsverifier-3.16.1.jar;out" Main
//test: java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path "out;lib/equalsverifier-nodep-4.1.jar;lib/gson-2.13.1.jar" --scan-classpath
