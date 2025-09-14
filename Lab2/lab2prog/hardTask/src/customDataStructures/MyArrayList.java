package customDataStructures;

import java.util.RandomAccess;

public class MyArrayList implements IMyList, RandomAccess{ 
    private Object[] elementData = new Object[0]; 
    private int size;

    public MyArrayList(){}
    public MyArrayList(Object... objects){
        addAll(objects);
    }

    public void add(Object e){
        extendElementDataArrayIfNeeded(1);
        elementData[size] = e;
        size++;
    }
    public void add(int index, Object element) throws IndexOutOfBoundsException{
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Cannot add the element beyond or not in the end of list");
        }
        if((size + 1) > elementData.length){
            Object[] extendedArray = getEmptyExtendedArray(elementData.length, size + 1);
            int offset = 0;
            for(int i = 0; i < size+1; i ++){
                if(i == index){
                    extendedArray[i] = element;
                    offset++;
                }else{
                    extendedArray[i] = elementData[i - offset];
                }                
            }
            elementData = extendedArray;
        }else{
            for(int i = size; i > index; i--){                
                elementData[i] = elementData[i-1];
            }
            elementData[index] = element;
        }
        size++;
    }
    public void addAll(Object[] c){
        int arraySize = c.length;
        if(arraySize > 0){
            extendElementDataArrayIfNeeded(arraySize);
            for(int i = 0; i < arraySize; i++){
                elementData[i + size] = c[i];
            }                       
        }

        size += arraySize;
    }
    public void addAll(int index, Object[] c) throws IndexOutOfBoundsException{
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Cannot add the elements beyond or not in the end of list");
        }

        int arraySize = c.length;
        if((size + arraySize) > elementData.length){
            Object[] extendedArray = getEmptyExtendedArray(elementData.length, size + arraySize);
            int offset = 0;
            for(int i = 0; i < size + arraySize; i++){
                if((i >= index) && (i < index+arraySize)){
                    extendedArray[i] = c[i-index];
                    offset++;
                }else{
                    extendedArray[i] = elementData[i - offset];
                }                
            }
            elementData = extendedArray;
        }else{
            for(int i = size+arraySize-1; i > index; i--){                
                elementData[i] = elementData[i-1];
            }
            for(int i = 0; i < arraySize; i++){
                elementData[i + index] = c[i];
            }            
        }
        
        size += arraySize;
    }
    public Object get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Cannot get the element beyond of list");
        }
        return elementData[index];
    }
    public Object remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Cannot get the element beyond of list");
        }

        Object result = elementData[index];        
        for(int i = index; i < size-1; i++){
            elementData[i] = elementData[i+1];
        }
        elementData[size-1] = null;
        size--;         

        return result;
    } 
    public void set(int index, Object element){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Cannot set the element beyond of list");
        }
        elementData[index] = element;
    } 
    public int indexOf(Object o){
        int foundIndex = -1;

        for(int currentElementIndex = 0; currentElementIndex < size; currentElementIndex++){
            if(o.equals(elementData[currentElementIndex])){
                foundIndex = currentElementIndex;
                break;
            }
        }
        return foundIndex;
    }
    public int size(){
        return size;
    } 
    public Object[] toArray(){
        Object[] objectArray = new Object[size];
        for(int i = 0; i < size; i++){
            objectArray[i] = elementData[i];
        }
        return objectArray;
    } 
    private void extendElementDataArrayIfNeeded(int newElementsCount) throws IllegalArgumentException{
        if(newElementsCount < 0){
            throw new IllegalArgumentException("The elements count which you intend to insert cannot be negative");
        }
        if((size + newElementsCount) > elementData.length){
            Object[] extendedArray = getEmptyExtendedArray(elementData.length, size + newElementsCount);
            for(int index = 0; index < elementData.length; index ++){
                extendedArray[index] = elementData[index];
            }
            elementData = extendedArray;
        }
    }
    private Object[] getEmptyExtendedArray(int oldArraySize, int minimalNeededSize) throws IllegalArgumentException{
        if(oldArraySize < 0){
            throw new IllegalArgumentException("The old size cannot be negative");
        }
        if(minimalNeededSize < 0){
            throw new IllegalArgumentException("The minimal needed size cannot be negative");
        }
        int newArraySize = Math.max((int)(oldArraySize * 1.5 + 1), minimalNeededSize) ;
        return new Object[newArraySize];
    }
}
