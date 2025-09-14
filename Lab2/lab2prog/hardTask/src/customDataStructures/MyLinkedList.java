package customDataStructures;

public class MyLinkedList implements IMyList{
    private Node startNode;
    private Node endNode;
    private int size = 0;

    public MyLinkedList(){}
    public MyLinkedList(Object... objects){
        addAll(objects);
    }

    public void add(Object element){
        Node nodeToAdd = new Node(element);
        if(startNode == null){ // In this case the list is empty
            startNode = nodeToAdd;
            endNode = nodeToAdd;
        }else{
            endNode.nextNode = nodeToAdd;
            nodeToAdd.previousNode = endNode;
            endNode = nodeToAdd;
        }
        size++;
    } 
    public void add(int index, Object element) throws IndexOutOfBoundsException{
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Cannot add the element beyond or not in the end of list");
        }
        
        Node nodeToAdd = new Node(element);
        if(index == 0){ // In the case of the beginning of the list
            if(size == 0){ // The list is empty
                startNode = nodeToAdd;
                endNode = nodeToAdd;
            }else{                
                nodeToAdd.nextNode = startNode;
                startNode = nodeToAdd;
            }
        }else if(index == size){ // Adding the node to the end of the list
            nodeToAdd.previousNode = endNode;
            endNode = nodeToAdd;           
        }else{ // Adding the node in the middle of the list
            Node firstNodeToMowe = getNode(index); 
            Node lastNodeNotToMowe = firstNodeToMowe.previousNode;

            nodeToAdd.previousNode = lastNodeNotToMowe;
            nodeToAdd.nextNode = firstNodeToMowe;
            lastNodeNotToMowe.nextNode = nodeToAdd;
            firstNodeToMowe.previousNode = nodeToAdd;
        }

        size++;
    }
    public void addAll(Object[] c){
        int arrLength = c.length;
        if(arrLength > 0){
            Node previousNode = endNode;
            Node currentNodeToAdd;
            
            for(int index = 0; index < arrLength; index++){
                currentNodeToAdd = new Node(c[index]);
                if(previousNode == null){
                    startNode = currentNodeToAdd;                  
                }else{
                    previousNode.nextNode = currentNodeToAdd;
                }
                
                currentNodeToAdd.previousNode = previousNode;
                
                previousNode = currentNodeToAdd;
                if(index == arrLength-1){
                    endNode = currentNodeToAdd;
                }                              
            }            

            size += arrLength;
        }        
    } 
    public void addAll(int index, Object[] c) throws IndexOutOfBoundsException{
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Cannot add the elements beyond or not in the end of list");
        }
        int arrLength = c.length;
        if(arrLength > 0){            
            Node firstNodeToMowe = (index == 0)? startNode : ((index == size)? null : getNode(index));
            Node lastNodeNotToMowe = (index == 0)? null : ((index == size)? endNode : firstNodeToMowe.previousNode);
            
            Node previousNode = null;
            for(int i = 0; i < arrLength; i++){
                Node currentNodeToAdd = new Node(c[i]);

                if(i == 0){
                    if(index == 0){
                        startNode = currentNodeToAdd;
                    }else{
                        lastNodeNotToMowe.nextNode = currentNodeToAdd;
                        currentNodeToAdd.previousNode = lastNodeNotToMowe;
                    }
                }else if(i == arrLength-1){
                    previousNode.nextNode = currentNodeToAdd;
                    currentNodeToAdd.previousNode = previousNode;

                    if(size == 0 || index == size){
                        endNode = currentNodeToAdd;
                    }else{
                        currentNodeToAdd.nextNode = firstNodeToMowe;
                        firstNodeToMowe.previousNode = currentNodeToAdd;
                    }                    
                }else{
                    previousNode.nextNode = currentNodeToAdd;
                    currentNodeToAdd.previousNode = previousNode;
                }         

                previousNode = currentNodeToAdd;                
            }

            size += arrLength;
        }
    } 
    public Object get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Cannot get the elements beyond of list");
        }
        Node foundNode = getNode(index);
        return foundNode.data;

    } 
    public Object remove(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Cannot delete the elements beyond of list");
        }
        Object result = null;
        if(size != 0){
            if(index == 0){
                Node nextNode = startNode.nextNode;
                result = startNode.data;
                startNode = nextNode;
                nextNode.previousNode = null;
            }else if(index == size-1){
                Node previousNode = endNode.previousNode;
                result = endNode.data;
                endNode = previousNode;
                previousNode.nextNode = null;
            }else{                
                Node nodeToDelete = getNode(index);
                Node previousNode = nodeToDelete.previousNode;
                Node nextNode = nodeToDelete.nextNode;
                result = nodeToDelete.data;
                previousNode.nextNode = nextNode;
                nextNode.previousNode = previousNode;                
            }
            size--;
        }
        return result;
    } 
    public void set(int index, Object element) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Cannot set the elements beyond of list");
        }
        Node updatedNode = getNode(index);
        updatedNode.data = element;
    }
    public int indexOf(Object o){
        int indexOfFound = -1;
        Node currentNode = startNode; // Represents the beginning of the list
        int currentNodeIndex = 0;
        while(currentNodeIndex < size){            
            if(o.equals(currentNode.data)){
                indexOfFound = currentNodeIndex;
                break;
            }
            currentNode = currentNode.nextNode;
            currentNodeIndex++;
        }
        return indexOfFound;
    } 
    public int size(){
        return size;
    } 
    public Object[] toArray(){
        Object[] objectArray = new Object[size];

        Node currentNode = startNode; // Represents the beginning of the list
        for(int currentNodeIndex = 0; currentNodeIndex < size; currentNodeIndex++){
            objectArray[currentNodeIndex] = currentNode.data;
            currentNode = currentNode.nextNode;
        }
        return objectArray;        
    } 



    private Node getNode(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Cannot get the element beyond the list");
        }
        Node currentNode = startNode; // Represents the beginning of the list
        int currentNodeIndex = 0;

        while(currentNodeIndex < index){            
            currentNode = currentNode.nextNode;
            currentNodeIndex++;
        }
        return currentNode;
    }

    private static class Node{
        Object data;
        Node previousNode;
        Node nextNode;

        public Node(){}
        public Node(Object data){            
            this.data = data;         
        }
    }
}


