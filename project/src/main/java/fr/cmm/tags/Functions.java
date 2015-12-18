package fr.cmm.tags;


import static org.apache.commons.lang3.StringEscapeUtils.escapeXml10;

/**
 * Created by christian on 17/12/15.
 */
public class Functions {
    public static String textFormating(String s){
        String temp=escapeXml10(s);
        String result=temp.replace("\n","<br>");
        return result;
    }
}
