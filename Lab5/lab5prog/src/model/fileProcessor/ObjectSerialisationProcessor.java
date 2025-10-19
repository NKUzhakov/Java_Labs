package model.fileProcessor;
import model.tourniquet.skiPass.*;

import java.io.*;
import java.util.ArrayList;

public class ObjectSerialisationProcessor {
    private final String filePath;

    public ObjectSerialisationProcessor(String filePath){
        this.filePath = filePath;
    }

    public void serializeSkiPasses(ArrayList<SkiPass> skiPassList) throws IOException, ClassNotFoundException{
        ArrayList<SkiPass> existingSkiPassList;
        try{
            existingSkiPassList = deserializeSkiPasses();
            existingSkiPassList.addAll(skiPassList);
        }catch(IOException e){
            existingSkiPassList = skiPassList;
        }        

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            for(SkiPass skiPass : existingSkiPassList){
                oos.writeObject(skiPass);
            }
        }
    }
    public ArrayList<SkiPass> deserializeSkiPasses() throws IOException, ClassNotFoundException{
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            ArrayList<SkiPass> skiPassList = new ArrayList<>();
            boolean hasNext = true;
            while(hasNext){
                try{
                    SkiPass current = (SkiPass)ois.readObject(); 
                    skiPassList.add(current);
                }catch(EOFException e){
                    hasNext = false;
                }
            }
            return skiPassList;
        }
    }
}
