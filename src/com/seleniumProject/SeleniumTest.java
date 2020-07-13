package com.seleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by asus on 7/11/2020.
 */
public class SeleniumTest {
    private static WebDriver driver;
    public SeleniumTest() {
        System.setProperty("webdriver.chrome.driver", "chromeDriver/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get("G:\\lesson\\Software Test\\Project\\SeleniumProject\\src\\com\\seleniumProject\\cv.html");
    }


    public void testHtmlHaveLangAttribute() {
        WebElement html = driver.findElement(By.tagName("html"));
        String langValue = html.getAttribute("lang");
        //System.out.println(langValue);
        boolean condition = langValue != null && !langValue.trim().isEmpty();
        if (!condition) {
            System.out.println("Html tag doesn't have language attribute");
        }
    }



    /////////////////////////////////firt remove table that have figure Tag/////////////////////////////////
    public void tabeDescription() {
        int tableCount=0;
        List<WebElement> tabels = driver.findElements(By.tagName("table"));
        List<WebElement> figures = driver.findElements(By.tagName("figure"));
        for (WebElement figure : figures) {
            if(!figure.findElements(By.tagName("figcaption")).isEmpty()){
                if (!figure.findElements(By.tagName("table")).isEmpty()){
                    WebElement we = figure.findElement(By.tagName("table"));
                    tabels.remove(we);
                }
            }
        }
        ////////////////aria and summary attribute and caption element//////////////////
        for (WebElement table : tabels) {
            boolean hasDes=false;
            tableCount++;
            String ariaValue = table.getAttribute("aria-describedby");
            String summaryValue = table.getAttribute("summary");
            if ((ariaValue != null && !ariaValue.trim().isEmpty()) || (summaryValue != null && !summaryValue.trim().isEmpty()))
                hasDes =true;
            if(!table.findElements(By.tagName("caption")).isEmpty() && !table.findElement(By.tagName("caption")).getText().trim().isEmpty()) {
                hasDes = true;
            }
            if(hasDes==false){
                System.out.println("<table> tags number:" +tableCount+" should  have a description");
            }

        }
    }

    public void sameTargetAndText(){
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for(int i=0 ; i<links.size() ; i++){
            for(int j=i+1 ; j<links.size() ; j++){
                String text1 = links.get(i).getText();
                String text2 = links.get(j).getText();
                if (text1!=null && !text1.trim().isEmpty()){
                    if (text1.equals(text2)) {
                        if (!links.get(i).getAttribute("href").equals(links.get(j).getAttribute("href")))
                            System.out.println("Links with \""+links.get(i).getText()+"\" texts should have identical targets");

                    }
                }

            }

        }

    }

    private void checkDeprecatedAttributes(List<String> attributes,String tagName){
        List<WebElement> tagNames = driver.findElements(By.tagName(tagName));
        for (WebElement tag : tagNames){
            for (String attribute : attributes){
                String deprecatedAttr = tag.getAttribute(attribute);
                if(deprecatedAttr!=null && !deprecatedAttr.trim().isEmpty()){
                    System.out.println("\""+attribute+"\""+" is a deprecated Attributes");
                }

            }

        }

    }

    private void checkDeprecatedAttributes2(String attribute,List<String> tagNames){
        for (String tag : tagNames){
            List<WebElement> tags = driver.findElements(By.tagName(tag));
            for (WebElement t : tags){
                String deprecatedAttr = t.getAttribute(attribute);
                if(deprecatedAttr!=null && !deprecatedAttr.trim().isEmpty()){
                    System.out.println("\""+attribute+"\""+" is a deprecated Attributes");
                }

            }

        }

    }


    private void checkExceptionCase(){
        List<WebElement> tags = driver.findElements(By.tagName("img"));
        for (WebElement tag : tags){
            String deprecatedAttr = tag.getAttribute("border");
            //List<WebElement> tags  = driver.findElements(By.xpath("//img[@border]"));
            System.out.println(deprecatedAttr);
            }

        }



    private void checkDeprecatedAttributes3(String attribute,String tagName){
        List<WebElement> tags = driver.findElements(By.tagName(tagName));
        for (WebElement t : tags){
            String deprecatedAttr = t.getAttribute(attribute);
            if(deprecatedAttr!=null && !deprecatedAttr.trim().isEmpty()){
                System.out.println("\""+attribute+"\""+" is a deprecated Attributes");
            }

        }
    }



    public void setAndFindDeprecatedAttributes(){
        List<String> attList = new ArrayList<>();
        List<String> tagsList = new ArrayList<>();
        attList.addAll(Arrays.asList("alink","background","bgcolor","link","marginbottom","marginheight","marginleft","marginright",
                "margintop","marginwidth","text","vlink"));
        //////for body tag//////////////////
        checkDeprecatedAttributes(attList,"body");
        attList.clear();
        /////////for object tag/////////////
        attList.addAll(Arrays.asList("align","archive","border","classid","code","codebase","codetype","datafld",
                "dataformatas","datasrc","declare","hspace","standby","vspace"));
        checkDeprecatedAttributes(attList,"object");
        attList.clear();
        ///////////for table tag/////////
        attList.addAll(Arrays.asList("align","background","bgcolor","bordercolor","cellpadding","cellspacing","dataformatas","datapagesize",
                "datasrc","rules","summary","width"));
        checkDeprecatedAttributes(attList,"table");
        attList.clear();
        /////////////////for iframe tag/////////////////////
        attList.addAll(Arrays.asList("scrolling","marginwidth","marginheight","frameborder","allowtransparency"));
        checkDeprecatedAttributes(attList,"iframe");
        attList.clear();

        ///////////////for hr tag ////////////////
        attList.addAll(Arrays.asList("color","noshade","size"));
        checkDeprecatedAttributes(attList,"hr");
        attList.clear();
        ////////////////for script tag//////////////////////
        attList.addAll(Arrays.asList("event","for"));
        checkDeprecatedAttributes(attList,"script");
        attList.clear();
        ////////////////for input tag//////////////////////
        attList.addAll(Arrays.asList("ismap","usemap"));
        checkDeprecatedAttributes(attList,"input");
        attList.clear();
        ////////////////for a tag//////////////////////
        attList.addAll(Arrays.asList("coords","shape"));
        checkDeprecatedAttributes(attList,"a");
        attList.clear();

        ////////////////single attribute//////////////////////////////////////////////
        tagsList.addAll(Arrays.asList(	"caption","col","div","embed","h1","h2","h3","h4","h5","h6","hr","iframe","img","input","legend","p","tbody","thead","tfoot","td","th","tr"));
        checkDeprecatedAttributes2("align",tagsList);
        tagsList.clear();


        tagsList.addAll(Arrays.asList("thead","tbody","tfoot","tr","td","th"));
        checkDeprecatedAttributes2("background",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("embed","img","optiont"));
        checkDeprecatedAttributes2("name",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("td","th","tr"));
        checkDeprecatedAttributes2("bgcolor",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("col","tbody","thead","tfoot","td","th","tr"));
        checkDeprecatedAttributes2("char",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("col","tbody","thead","tfoot","td","th","tr"));
        checkDeprecatedAttributes2("charoff",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("a","link"));
        checkDeprecatedAttributes2("charset",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("td","th"));
        checkDeprecatedAttributes2("axis",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("a","applet","button","div","fieldset","frame","iframe","img","input","label","legend","marquee","param","select",
                "span","textare"));
        checkDeprecatedAttributes2("datafld",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("button","div","input","label","legend","marquee","option","select","span"));
        checkDeprecatedAttributes2("dataformatas",tagsList);
        tagsList.clear();


        tagsList.addAll(Arrays.asList("a","applet","button","div","frame","iframe","img","input","label","legend","marquee","option","select","span","textarea"));
        checkDeprecatedAttributes2("datasrc",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("embed","iframe","img","input"));
        checkDeprecatedAttributes2("hspace",tagsList);
        tagsList.clear();


        tagsList.addAll(Arrays.asList("dl","ol","ul"));
        checkDeprecatedAttributes2("compact",tagsList);
        tagsList.clear();


        tagsList.addAll(Arrays.asList("col","hr","pre","td","th"));
        checkDeprecatedAttributes2("width",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("col","tbody","thead","tfoot","td","th","tr"));
        checkDeprecatedAttributes2("valign",tagsList);
        tagsList.clear();


        tagsList.addAll(Arrays.asList("li","param","ul"));
        checkDeprecatedAttributes2("type",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("a","link"));
        checkDeprecatedAttributes2("urn",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("a","link"));
        checkDeprecatedAttributes2("methods",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("td","th"));
        checkDeprecatedAttributes2("nowrap",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("td","th"));
        checkDeprecatedAttributes2("height",tagsList);
        tagsList.clear();
        ///////////////////////single tag single attribute///////////
        checkDeprecatedAttributes3("accept","form");
        checkDeprecatedAttributes3("clear","br");
        checkDeprecatedAttributes3("lowsrc","img");
        checkDeprecatedAttributes3("nohref","area");
        checkDeprecatedAttributes3("profile","head");
        checkDeprecatedAttributes3("scheme","meta");
        checkDeprecatedAttributes3("scope","td");
        checkDeprecatedAttributes3("target","link");
        checkDeprecatedAttributes3("valuetype","param");
        checkDeprecatedAttributes3("version","html");
    }

}




