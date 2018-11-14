package WebScrapers;

import Utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.lang.String;

public class WrigstadScraper {


    public WrigstadScraper(){

    }

    public static String getDescription(String achievement) throws Exception{
        final Document document = Jsoup.connect("http://wrigstad.com/ioopm18/achievements.html#org2a2fa10").get();
        boolean gotTag = false;
        String tag = "";
        String description = "";
        for (Element rows : document.getElementById("content").select("div[id^= outline-container]")) {
            if (gotTag){
                if (StringUtils.beginsWith(rows.text(),tag)) description = description + rows.text();
                else return description;
            }
            else if (StringUtils.getTag(rows.text(),achievement) != null) {
                description = rows.text();
                tag = StringUtils.getTag(description,achievement);
                gotTag = true;
            }



        }
        return "";
    }
    public static void main(String[] args) throws Exception {

}}
