package model;

import java.lang.reflect.Modifier;
import java.lang.reflect.Field;

public class StringModifier {
    public static final String DEFAULT_INIT_STR_VAL = "Default initial string value";
    public static final String DEFAULT_NEW_STR_VAL = "Default new string value";
    private final String strToModify;

    public StringModifier(){
        this.strToModify = new String(DEFAULT_INIT_STR_VAL);
    }
    public StringModifier(String strToModify){
        this.strToModify = new String(strToModify);
    }

    public void changeValue(String newValue){
        try{
            Field field = String.class.getDeclaredField("value");
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            
            field.set(strToModify, newValue.toCharArray());
        }catch(NoSuchFieldException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }
    public String getModifiedString(){ return strToModify;}
}
