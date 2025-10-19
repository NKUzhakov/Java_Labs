package model.fileProcessor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

public class HTMLTagsStatsProcessor {
    private static Pattern tagPattern = Pattern.compile("<\\w+");
    private final String urlString;
    private final String html;
    
    public HTMLTagsStatsProcessor(String urlString) throws IOException{
        this.urlString = urlString;
        this.html = getHTML();
    }

    public HashMap<String, Integer> getTagsStats(){        
        Matcher tagsMatcher = tagPattern.matcher(html);
        HashMap<String, Integer> tagStats = new HashMap<>();
        String currentTag;
        while(tagsMatcher.find()){
            currentTag = tagsMatcher.group() + ">";
            tagStats.put(currentTag, ((tagStats.containsKey(currentTag))? tagStats.get(currentTag) : 0) + 1);
        }
        return tagStats;
    }
    public static ArrayList<String> getSortedByFrequencyTags(HashMap<String, Integer> tagStats){
        ArrayList<String> sortedByFrequencyTags = new ArrayList<>(tagStats.keySet());
        sortedByFrequencyTags.sort((String first, String second) -> Integer.compare(tagStats.get(first), tagStats.get(second)));
        return sortedByFrequencyTags;
    }
    public static ArrayList<String> getSortedByAlphabetTags(HashMap<String, Integer> tagStats){ 
        ArrayList<String> sortedByFrequencyTags = new ArrayList<>(tagStats.keySet());
        sortedByFrequencyTags.sort(String::compareTo);  //(String first, String second) -> first.compareTo(second)
        return sortedByFrequencyTags;
    }


    
    private String getHTML() throws MalformedURLException, IOException{        
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder html = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    html.append(inputLine).append("\n");
                }

                return html.toString();
            }
        } else {
            throw new IOException("Could not connect to " + urlString);
        }        
    }
        
}
