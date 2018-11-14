package jarvis;
import java.io.*;

import webscrapers.SeleniumAuScraper;
import utils.StringUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import webscrapers.JsoupAUScraper;
import webscrapers.WrigstadScraper;


public class Jarvis {
    protected File trackedGoals;

    public Jarvis () throws Exception{
        setFile();
    }

    private void setFile(){
        String home = System.getProperty("user.home");
        File f = new File(home + File.separator + "JarvisV2" + File.separator + "achievements.txt");
        this.trackedGoals = f;
    }


    public void writeFile(HashMap<String,Integer> hashmap) throws Exception{
        FileOutputStream fos = new FileOutputStream(trackedGoals);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(hashmap);
        oos.close();
        fos.close();
    }

    public HashMap<String, Integer> readMap() throws Exception{
        FileInputStream fis = new FileInputStream(trackedGoals);
        ObjectInputStream ois = new ObjectInputStream(fis);
        HashMap<String,Integer> trackedGoals = (HashMap<String,Integer>) ois.readObject();

        fis.close();
        ois.close();

        return trackedGoals;
    }
    public void updateGoals(String username, String password)throws Exception{
        HashMap<String, Integer> map = readMap();
        LinkedList<String> completed = SeleniumAuScraper.getFinishedGoals(username,password);
        for (String goal : completed){
            if(map.containsKey(goal)) map.remove(goal);
        }
        writeFile(map);
    }

    public int numberOfCompletedGoals() throws Exception{

        return 69 - readMap().size();
    }

    public void availableAtGrade(int grade) throws Exception{
        HashMap<String, Integer> map = readMap();
        int goalsleft = 0;

        Collection<String> keycollection = map.keySet();
        List<String> keys = new ArrayList<String>(keycollection);
        Collections.sort(keys);


        for (String key : keys){
            if(map.get(key) == grade) {
                System.out.println(key);
                goalsleft++;
            }
        }
        System.out.println("Total of: " + goalsleft + " goals.");
    }
    public void getDescription(String tag) throws Exception{
        StringUtils.printDescription(StringUtils.formatDescription(WrigstadScraper.getDescription(tag)));
    }

    public void resetGoals() throws Exception{
        writeFile(JsoupAUScraper.allGoals());
    }


}
