package customDataStructures;

public class MyCache {
    private int sizeLimit;
    private MyLinkedHashSet linkedHashSet = new MyLinkedHashSet();

    public MyCache(int sizeLimit) throws IllegalArgumentException{
        if(sizeLimit < 0){
            throw new IllegalArgumentException("The size limit cannot be negative");
        }
        this.sizeLimit = sizeLimit;
    }

    public boolean add(Object key, Object value) throws NullPointerException{
        if(key == null)
            throw new NullPointerException("Key cannot be null");
        if(value == null)
            throw new NullPointerException("Value cannot be null");
        if(sizeLimit == 0)
            return false;

        KeyValuePair newPair = new KeyValuePair(key, value);
        if(linkedHashSet.contains(newPair)){
            return false;
        }else{
            if(linkedHashSet.size()+1 > sizeLimit){
                linkedHashSet.remove(0); // Remove the "oldest" pair            
            }
            linkedHashSet.add(newPair);
            return true;
        }        
    }
    public Object get(Object key){
        Object foundValue = null;
        KeyValuePair foundPair = getKeyValuePair(key);
        if(foundPair != null){
            foundValue = foundPair.getValue();
            // "Refreshing" the pair
            linkedHashSet.remove(foundPair);
            linkedHashSet.add(foundPair);
        }
        return foundValue;
    }
    public boolean set(Object key, Object value) throws NullPointerException{
        if(key == null)
            throw new NullPointerException("Key cannot be null");
        if(value == null)
            throw new NullPointerException("Value cannot be null");

        KeyValuePair pairToUpdate = getKeyValuePair(key);        
        if(pairToUpdate != null){
            pairToUpdate.setValue(value);
            // "Refreshing" the pair
            linkedHashSet.remove(pairToUpdate);
            linkedHashSet.add(pairToUpdate);
            return true;
        }else{
            return false;
        }   
    }
    public boolean remove(Object key){
        boolean removed = false;
        KeyValuePair pairToRemove = getKeyValuePair(key);
        if(pairToRemove != null){
            linkedHashSet.remove(pairToRemove);
            removed = true;
        }
        return removed;
    }

    public int size(){
        return linkedHashSet.size();
    }
    public Object[] toArray(){
        return linkedHashSet.toArray();
    }

    private KeyValuePair getKeyValuePair(Object key){
        KeyValuePair foundPair = null;
        for(int i = 0; i < linkedHashSet.size(); i++){
            Object element = linkedHashSet.get(i);
            KeyValuePair pair = (KeyValuePair)element;
            if(pair.getKey().equals(key)){
                foundPair = pair;
                break;
            }
        }
        return foundPair;
    }

    public static class KeyValuePair {
        private Object key;
        private Object value;

        public KeyValuePair(Object key, Object value) throws NullPointerException{
            if(key == null){
                throw new NullPointerException("Key cannot be null");
            }
            if(value == null){
                throw new NullPointerException("Value cannot be null");
            }

            this.key = key;
            this.value = value;
        }
        
        public Object getKey(){
            return key;
        }
        public void setKey(Object key){
            if(key == null){
                throw new NullPointerException("Key cannot be null");
            }
            this.key = key;
        }
        
        public Object getValue(){
            return value;
        }
        public void setValue(Object value){
            if(value == null){
                throw new NullPointerException("Value cannot be null");
            }
            this.value = value;
        }
    
        @Override
        public boolean equals(Object otherObject){
            if((otherObject == null) || !(otherObject instanceof KeyValuePair)){
                return false;
            }
            if(otherObject == this){
                return true;
            }
            KeyValuePair otherKeyValuePair = (KeyValuePair) otherObject;
            return (otherKeyValuePair.getKey()).equals(this.getKey());
        }
        @Override
        public int hashCode(){
            return key.hashCode();
        }    
    }
}
