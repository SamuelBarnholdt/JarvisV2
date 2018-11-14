package Jarvis;
import java.io.*;

import WebScrapers.SeleniumDriver;
import Utils.StringUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import WebScrapers.AuWebScraper;
import WebScrapers.WrigstadScraper;
public class Jarvis {
    protected File trackedGoals;
    protected String surname;

    public Jarvis (String surname) throws Exception{
        this.surname = surname;
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
    }

    public HashMap<String, Integer> readMap() throws Exception{
        FileInputStream files = new FileInputStream(trackedGoals);
        ObjectInputStream obs = new ObjectInputStream(files);
        HashMap<String,Integer> trackedGoals = (HashMap<String,Integer>) obs.readObject();
        return trackedGoals;
    }
    public void updateGoals(String username, String password)throws Exception{
        SeleniumDriver driver = new SeleniumDriver();
        HashMap<String, Integer> map = readMap();
        LinkedList<String> completed = driver.getFinishedGoals(username,password);
        for (String goal : completed){
            if(map.containsKey(goal)) map.remove(goal);
        }
        writeFile(map);
    }
    public void completeGoal(String goal) throws Exception{
        HashMap<String, Integer> map = readMap();
        if (map.containsKey(goal)) map.remove(goal);
        writeFile(map);
    }
    public void numberOfCompletedGoals() throws Exception{

        System.out.println(68 - readMap().size());
    }
    public void availableAtGrade(int grade) throws Exception{
        HashMap<String, Integer> map = readMap();
        Collection<String> keycollection = map.keySet();
        List<String> keys = new ArrayList<String>(keycollection);
        Collections.sort(keys);
        for (String key : keys){
            if(map.get(key) == grade) System.out.println(key);
        }
    }
    public void getDescription(String tag) throws Exception{
        StringUtils.printDescription(StringUtils.formatDescription(WrigstadScraper.getDescription(tag)));
    }

    public void resetGoals() throws Exception{
        writeFile(AuWebScraper.allGoals());
    }

    public static void main(String[] args) throws Exception{
        Jarvis jarvis = new Jarvis("Barnholdt");
        jarvis.resetGoals();
        System.out.println(jarvis.readMap());
        jarvis.completeGoal("H21");
        System.out.println(jarvis.readMap());
        jarvis.numberOfCompletedGoals();
        jarvis.availableAtGrade(3);
        StringUtils.printDescription(StringUtils.formatDescription(WrigstadScraper.getDescription("Z100")));
    }

}
