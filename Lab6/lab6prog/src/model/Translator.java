package model;

import java.util.HashMap;

public class Translator{
    private final HashMap<String, String> dictionary = new HashMap<>();

    public void addWordPair(String engWord, String ukrWord) throws IllegalArgumentException{
        if(engWord == null || engWord.isBlank()) 
            throw new IllegalArgumentException("The english word cannot be blank");
        if(ukrWord == null || ukrWord.isBlank()) 
            throw new IllegalArgumentException("The ukrainian word cannot be blank");
        dictionary.put(engWord, ukrWord);
    }

    public String translateEngToUkr(String engPhrase) throws IllegalArgumentException{
        String[] engWords = phraseToWords(engPhrase);
        StringBuilder ukrTranslation = new StringBuilder();
        String currWord = null;
        for(String engWord : engWords){
            currWord = dictionary.get(engWord);
            if(currWord == null) 
                throw new IllegalArgumentException("The phrase contains the words that are missing in the dictionary");
            ukrTranslation.append(currWord + " ");
        }
        return ukrTranslation.toString();
    }

    private String[] phraseToWords(String phrase){
        return phrase.split("([\\s*\\.,\\?!])\\s*");
    }

    public String toString(){
        String res = "";
        for(String key : dictionary.keySet()){
            res += key + " - " + dictionary.get(key) + "\n";
        }
        return res;
    }
}