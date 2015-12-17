package fr.cmm.tags;


import static org.apache.commons.lang3.StringEscapeUtils.escapeXml10;

/**
 * Created by christian on 17/12/15.
 */
public class Functions {
    public static String textFormating(String s){
        String temp=s.replace("\n","<br>");
        String result=escapeXml10(temp);
        return result;
    }
}
