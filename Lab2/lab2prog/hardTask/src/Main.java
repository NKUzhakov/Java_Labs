import customDataStructures.*;
public class Main {
    public static void main(String[] args){
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.add("first");
        linkedList.add("second");
        String[] strs = {"third", "fourth", "fifth"};
        linkedList.addAll(strs);
        
        // for(Object object : total)
        //     System.out.println(object);
        System.out.println(linkedList.size());
        linkedList.remove(0);
        System.out.println(linkedList.size());
        linkedList.add(2, "NEW!");
        System.out.println(linkedList.size());
        Object[] total = linkedList.toArray();
        for(Object object : total)
            System.out.println(object);
    }
}




// compile: javac -cp "../lib/junit-platform-console-standalone-1.10.0.jar;src tests" -d ../out *.java tests/*.java customDataStructures/*.java  
// test: java -jar ../lib/junit-platform-console-standalone-1.10.0.jar -cp ../out --scan-classpath
