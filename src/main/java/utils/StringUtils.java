package utils;

import java.util.*;

public class StringUtils {

    public static String getGoal(String parsed){
        String sub =  parsed.substring(0,4);
        return cut(parsed);
    }
    public static boolean beginsWith(String str,String str2){
        if (str.matches("^" + str2 + "?:")) return true;
        else return false;
    }

    public static String cut(String s){
        try{
        int index = 0;
        while(!s.substring(0,index).endsWith(" ")){
            index++;
            if (index >= s.length() - 2) break;
        }
        index -= 1;
        return s.substring(0,index);
        }
        catch (Exception e){
            cut(s);
            return null;
        }

    }
    public static String getTag(String str,String achievement){
        if (str.substring(0,20).contains(achievement)) return cut(str);
        else return null;
    }

     public static LinkedList<String> formatDescription(String description){
        int finalindex = description.length() - 1;
        int wordsperrow = 7;
        int wordsrecieved = 0;
        int currentindex = 0;
        String sub;
        LinkedList<String> list = new LinkedList<String>();

        do{
            wordsrecieved = 0;
            sub = "";
            while (wordsrecieved != wordsperrow){
                String word = cut(description.substring(currentindex,finalindex));
                currentindex += word.length() + 1;
                sub = sub + word + " ";
                wordsrecieved++;
                if (!(currentindex < finalindex)) break;
            }
            list.add(sub);
        }while(currentindex < finalindex);
        return list;

    }
    public static void printDescription(LinkedList<String> list){
        for (String str : list){
            System.out.println(str);
        }

    }
    public static void printGoals(HashMap<String,Integer> map){
        Collection<String> keymap = map.keySet();
        String[] keys = new String[keymap.size()];
        keymap.toArray(keys);
        Arrays.sort(keys);
        int total = 0;
        for(String key : keys){
            System.out.println("Goal: " + key + " Grade: " +  map.get(key));
            total += 1;
        }
        System.out.println("Total of " + total + " goals.");


    }
}
