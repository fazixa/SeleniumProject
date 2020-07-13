package com.seleniumProject;

import java.util.ArrayList;
import java.util.List;

public class Methods {

    public int numOfOccurrance(List<String> links, String link){
        int counter = 0;
        for (String l: links){
            if(l.equals(link)){
                counter ++;
            }
        }
        return counter;
    }


    public int numOfCommonRefrences(List<String> links1, List<String> links2){

        List<String> commonRefrences = new ArrayList<String>(links1);
        commonRefrences.retainAll(links2);
        return  commonRefrences.size();

    }




}
