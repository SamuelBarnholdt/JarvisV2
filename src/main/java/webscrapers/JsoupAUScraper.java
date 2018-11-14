package webscrapers;

import utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;

public class JsoupAUScraper {

    public static HashMap<String,Integer> allGoals() throws Exception{
        HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
        final Document document = Jsoup.connect("http://auportal.herokuapp.com/achievements").get();
        for (Element row : document.select("table")){
            for (Element rows : row.select(".achievement_item.even.grade_3")) {
                String grade = (StringUtils.getGoal(rows.text()));
                int level = 3;
                hashmap.put(grade, level);
            }
            for (Element rows : row.select(".achievement_item.odd.grade_3")){
                String grade =(StringUtils.getGoal(rows.text()));
                int level = 3;
                hashmap.put(grade,level);
                }
            for (Element rows : row.select(".achievement_item.even.grade_4")){
                String grade =(StringUtils.getGoal(rows.text()));
                int level = 4;
                hashmap.put(grade,level);

            }
            for (Element rows : row.select(".achievement_item.odd.grade_4")){
                String grade =(StringUtils.getGoal(rows.text()));
                int level = 4;
                hashmap.put(grade,level);

            }
            for (Element rows : row.select(".achievement_item.even.grade_5")){
                String grade =(StringUtils.getGoal(rows.text()));
                int level = 5;
                hashmap.put(grade,level);
                }
            for (Element rows : row.select(".achievement_item.odd.grade_5")){
                String grade =(StringUtils.getGoal(rows.text()));
                int level = 5;
                hashmap.put(grade,level);
            }
        }


        return hashmap;


    }


}
