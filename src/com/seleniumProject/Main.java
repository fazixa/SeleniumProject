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
        System.setProperty("webdriver.chrome.driver", "chromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("F:\\Current Sem\\Software Testing\\project\\test.html");


        //------------------------------------  1  ---------------------------------------
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        for(WebElement table :tables){
            if(table.findElements(By.xpath("//th")).size()==0){
                System.out.println("tables must have headers");
            }
        }

        //------------------------------------  3  ---------------------------------------
//        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        boolean titleExists = driver.findElements( By.tagName("title") ).size() != 0;
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if(!titleExists){System.out.println("title not found");}


        //------------------------------------  5  ----------------------------------------

        int imagesNum = driver.findElements(By.xpath("//img")).size();
        int imagesWidthNum = driver.findElements(By.xpath("//img[@width]")).size();
        int imagesHeightNum = driver.findElements(By.xpath("//img[@width]")).size();
        if(imagesNum>imagesHeightNum){
            System.out.println("All images must have height atrribute");
        }
        if(imagesNum>imagesWidthNum){
            System.out.println("All images must have width atrribute");
        }

        //---------------------------------------  7  ------------------------------------

        // get all "for" attributes of labales
        List<WebElement> labels = driver.findElements(By.tagName("label"));
        List<String> labelfors =new ArrayList<>();
        System.out.println(labels.size());
        for (WebElement WE: labels){
            labelfors.add(WE.getAttribute("for"));
        }
        // get all elements needing label: input, select, textarea
        List<WebElement> inputs  = driver.findElements(By.tagName("input"));
        List<WebElement> selects  = driver.findElements(By.tagName("selects"));
        List<WebElement> textareas  = driver.findElements(By.tagName("textareas"));
        List<WebElement> list = new ArrayList<>();
        list.addAll(inputs);
        list.addAll(selects);
        list.addAll(textareas);


        // checks compliance
        for( WebElement i: list){
            if (i.getAttribute("aria-label")!="" || i.getAttribute("aria-labelledby")!=""){
                continue;
            }
            if(i.getAttribute("id").equals("")){
                System.out.println("element is missing required attribute id");
                continue;
            }
            String id = i.getAttribute("id");
            if(!labelfors.contains(id)) {
                System.out.println("element must have label");
            }
        }



        SeleniumTest st = new SeleniumTest();
        st.testHtmlHaveLangAttribute();
        st.tabeDescription();
        st.sameTargetAndText();
        st.setAndFindDeprecatedAttributes();
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

