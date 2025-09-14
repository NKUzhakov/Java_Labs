package customDataStructures;


public class MyHashTable {    
    private int tableCellsCount = 16;
    private int hashTableSize = 0;
    private double loadFactor = 0.75;
    private Bucket[] hashTable = new Bucket[tableCellsCount];
    
    public boolean add(Object element){
        if(contains(element)){
            return false;
        }else{
            int index = index(element, tableCellsCount);
            Bucket tableCell = hashTable[index];
            Bucket newBucket = new Bucket(element);

            if(tableCell == null){
                hashTable[index] = newBucket;                
            }else{
                newBucket.nextBucket = tableCell;
                hashTable[index] = newBucket;
            }

            if(hashTableSize >= tableCellsCount * loadFactor){
                resize();
            }

            hashTableSize++;
            return true;
        }
    }
    public boolean remove(Object element){
        int index = index(element, tableCellsCount);
        Bucket currentBucket = hashTable[index];

        boolean removed = false;
        Bucket previousBucket = null;
        while(currentBucket != null){
            if(element.equals(currentBucket.getData())){
                if(previousBucket == null){
                    hashTable[index] = currentBucket.nextBucket;
                }else{
                    previousBucket.nextBucket = currentBucket.nextBucket;
                }
                removed = true;
                hashTableSize--;
                break;
            }

            previousBucket = currentBucket;
            currentBucket = currentBucket.nextBucket;
        }
        return removed;
    }
    public boolean contains(Object element){
        int index = index(element, tableCellsCount);
        Bucket currentBucket = hashTable[index];

        boolean contains = false;
        while(currentBucket != null){
            if(element.equals(currentBucket.getData())){
                contains = true;
                break;
            }

            currentBucket = currentBucket.nextBucket;
        }
        return contains;
    }
    public int size(){
        return hashTableSize;
    }

    private void resize(){
        int newTableCellsCount = tableCellsCount * 2;
        Bucket[] resizedHashTable = new Bucket[newTableCellsCount];
        for(int currentCellIndex = 0; currentCellIndex < tableCellsCount; currentCellIndex++){
            Bucket currentBucket = hashTable[currentCellIndex];
            while(currentBucket != null){
                Object rehashedData = currentBucket.getData();
                int newIndex = index(rehashedData, newTableCellsCount);
                Bucket newTableCell = resizedHashTable[newIndex];
                Bucket newBucket = new Bucket(rehashedData);
                if(newTableCell != null){                    
                    newBucket.nextBucket = newTableCell;
                }
                resizedHashTable[newIndex] = newBucket;

                currentBucket = currentBucket.nextBucket;
            }
        }

        tableCellsCount = newTableCellsCount;
        hashTable = resizedHashTable;
    }
    private int index(Object element, int tableCellsCount) {
        return Math.abs((element == null ? 0 : element.hashCode()) % tableCellsCount);
    }
    private static class Bucket {
        private Object data;        
        public Bucket nextBucket;

        public Bucket(){}
        public Bucket(Object data) {
            this.data = data;
        }
        
        public Object getData(){
            return data;
        }
        public void setData(Object data){
            this.data = data;
        }
        public void clear(){
            data = null;
        }

        @Override
        public String toString(){
            return "Bucket(" + data.toString() + ")";
        }
    } 
}




