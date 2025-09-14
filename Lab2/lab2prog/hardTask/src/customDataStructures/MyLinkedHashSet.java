package customDataStructures;

public class MyLinkedHashSet {
    MyHashTable hashTable = new MyHashTable();
    MyLinkedList linkedList = new MyLinkedList();

    public boolean add(Object o){
        boolean added = hashTable.add(o);
        if(added){
            linkedList.add(o);
        }
        return added;
    }
    public boolean addAll(Object[] c){
        boolean addedAll = true;
        for(Object o : c){
            addedAll &= add(o);
        }
        return addedAll;
    }
    public boolean remove(Object o){
        boolean removed = hashTable.remove(o);
        if(removed){
            int indexOfRemoved = linkedList.indexOf(o);
            linkedList.remove(indexOfRemoved);
        }
        return removed;
    }
    public boolean remove(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Cannot remove the element beyond of set");
        }

        Object objectToRemove = get(index);
        return remove(objectToRemove);
    }
    public Object get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Cannot get the element beyond of set");
        }
        return linkedList.get(index);
    }
    public boolean removeAll(Object[] c){
        boolean removedAll = true;
        for(Object o : c){
            removedAll &= remove(o);
        }
        return removedAll;
    }
    public boolean contains(Object o){
        return hashTable.contains(o);
    }
    public int size(){
        return hashTable.size();
    }
    public Object[] toArray(){
        return linkedList.toArray();
    }
    
}
