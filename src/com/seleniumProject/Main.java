package com.seleniumProject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.*;
import com.google.common.base.CharMatcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;


import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        SeleniumTest st = new SeleniumTest();
        //1
        st.tableHeaders();
        //2
        st.testHtmlHaveLangAttribute();
        //3
        st.pageHasTitle();
        //4
        st.setAndFindDeprecatedAttributes();
        //5
        st.imageHeightWidth();
        //6
        st.tabeDescription();
        //7
        st.checkLabel();
        //8
        st.sameTargetAndText();


       /*
        try(BufferedReader in = new BufferedReader(new FileReader("G:\\lesson\\Software Test\\Project\\SeleniumProject\\input.txt"))) {
            String str;
            SemanticRelatedness sr = new SemanticRelatedness();
            while ((str = in.readLine()) != null) {
                System.out.println(str);
                String[] tokens = str.split(",");
                String phrase1 = tokens[0];
                String phrase2 = tokens[1];
                if(sr.foundPage(phrase1,phrase2)) sr.searchString(phrase1,phrase2);
                System.out.println("#############################################");

            }
        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }

*/

    }





    }

