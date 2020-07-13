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

    //------------------------------------  1  ---------------------------------------
    public void tableHeaders(){
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        for(WebElement table :tables){
            if(table.findElements(By.xpath("//th")).size()==0){
                System.out.println("tables must have headers");
            }
        }
    }


    //------------------------------------  2  ---------------------------------------

    public void testHtmlHaveLangAttribute() {
        WebElement html = driver.findElement(By.tagName("html"));
        String langValue = html.getAttribute("lang");
        //System.out.println(langValue);
        boolean condition = langValue != null && !langValue.trim().isEmpty();
        if (!condition) {
            System.out.println("Html tag doesn't have language attribute");
        }
    }


    //------------------------------------  3  ---------------------------------------
    public void pageHasTitle(){
    boolean titleExists = driver.findElements( By.tagName("title") ).size() != 0;
    if(!titleExists){System.out.println("title not found");}
    }


    //------------------------------------  5  ----------------------------------------
    public void imageHeightWidth(){
        int imagesNum = driver.findElements(By.xpath("//img")).size();
        int imagesWidthNum = driver.findElements(By.xpath("//img[@width]")).size();
        int imagesHeightNum = driver.findElements(By.xpath("//img[@width]")).size();
            if(imagesNum>imagesHeightNum){
            System.out.println("All images must have height atrribute");
        }
            if(imagesNum>imagesWidthNum){
            System.out.println("All images must have width atrribute");
        }
    }


    //---------------------------------------  7  ------------------------------------
    public void checkLabel() {

        // get all "for" attributes of labales
        List<WebElement> labels = driver.findElements(By.tagName("label"));
        List<String> labelfors = new ArrayList<>();
        for (WebElement WE : labels) {
            labelfors.add(WE.getAttribute("for"));
        }
        // get all elements needing label: input, select, textarea
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        List<WebElement> selects = driver.findElements(By.tagName("selects"));
        List<WebElement> textareas = driver.findElements(By.tagName("textareas"));
        List<WebElement> list = new ArrayList<>();
        list.addAll(inputs);
        list.addAll(selects);
        list.addAll(textareas);


        // checks compliance
        for (WebElement i : list) {
            if (i.getAttribute("aria-label") != "" || i.getAttribute("aria-labelledby") != "") {
                continue;
            }
            if (i.getAttribute("id").equals("")) {
                System.out.println("element is missing required attribute id");
                continue;
            }
            String id = i.getAttribute("id");
            if (!labelfors.contains(id)) {
                System.out.println("element must have label");
            }
        }
    }
    //---------------------------------------  6  ------------------------------------
    /////////////////////////////////firt remove table that have figure Tag/////////////////////////////////
    public void tabelDescription() {
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


    //---------------------------------------  8  ------------------------------------
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
        List <WebElement> tags  = driver.findElements(By.tagName("script"));
        for(WebElement t: tags){
            String language = t.getAttribute("language");
            if(language!=null && !language.isEmpty()) {
                if (!language.toLowerCase().equals("javascript")) {
                    System.out.println("\"language\" is a deprecated attribute if the value:\"" + language + "\"");
                }
            }

        }

        tags.clear();
        tags  = driver.findElements(By.tagName("img"));
        for(WebElement t: tags){
            String border = t.getAttribute("border");
            //String name = t.getAttribute("name");
            if(border!=null && !border.isEmpty()) {
                if (!border.toLowerCase().equals("0")) {
                    System.out.println("\"border\" is a deprecated attribute if the value:\"" + border + "\"");
                }
            }

        }

        tags.clear();
        tags  = driver.findElements(By.tagName("a"));
        for(WebElement t: tags){
            String id = t.getAttribute("id");
            String name = t.getAttribute("name");
            //String name = t.getAttribute("name");
            if(name!=null && !name.isEmpty()) {
                if(id!=null && !id.isEmpty()) {
                    if (!name.equals(id)) {
                        System.out.println("\"name\" is a deprecated attribute if the value:\"" + name + "\"");
                    }
                }
            }

        }
        /*
        for(WebElement t: tags){
            if(!t.toString().equals("0")){
                System.out.println("\"border\" is a deprecated attribute if the value is: \""+t.toString()+"\"");
            }
        }
        tags.clear();
        tags  = driver.findElements(By.xpath("//script[@language]"));
        for(WebElement t: tags){
            if(!t.toString().toLowerCase().equals("javascript")){
                System.out.println("\"language\" is a deprecated attribute if the value is:\""+t.toString().toLowerCase()+"\"");
            }

        }

        tags.clear();
        tags  = driver.findElements(By.xpath("//a"));
        for(WebElement t: tags){
            String id = t.getAttribute("id");
            String name = t.getAttribute("name");
            if(!id.equals(name)){
                System.out.println("\"name\" is a deprecated attribute if the value:\""+name+"\"");
            }

        }

*/

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
    private void checkDeprecatedAttributesImage(List<String> attList) {
        for(String s : attList){
            String str = "//img[@"+s+"]";
            int num = driver.findElements(By.xpath(str)).size();
            if(num!=0){
                System.out.println("\""+s+"\""+" is a deprecated Attributes");
            }

        }
    }


    public void setAndFindDeprecatedAttributes(){
        checkExceptionCase();
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

        attList.addAll(Arrays.asList("align","name","datafld","datasrc","hspace","lowsrc"));
        checkDeprecatedAttributesImage(attList);
        attList.clear();

        ////////////////single attribute//////////////////////////////////////////////
        tagsList.addAll(Arrays.asList(	"caption","col","div","embed","h1","h2","h3","h4","h5","h6","hr","iframe","input","legend","p","tbody","thead","tfoot","td","th","tr"));
        checkDeprecatedAttributes2("align",tagsList);
        tagsList.clear();


        tagsList.addAll(Arrays.asList("thead","tbody","tfoot","tr","td","th"));
        checkDeprecatedAttributes2("background",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("embed","optiont"));
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

        tagsList.addAll(Arrays.asList("a","applet","button","div","fieldset","frame","iframe","input","label","legend","marquee","param","select",
                "span","textare"));
        checkDeprecatedAttributes2("datafld",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("button","div","input","label","legend","marquee","option","select","span"));
        checkDeprecatedAttributes2("dataformatas",tagsList);
        tagsList.clear();


        tagsList.addAll(Arrays.asList("a","applet","button","div","frame","iframe","input","label","legend","marquee","option","select","span","textarea"));
        checkDeprecatedAttributes2("datasrc",tagsList);
        tagsList.clear();

        tagsList.addAll(Arrays.asList("embed","iframe","input"));
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
        checkDeprecatedAttributes3("nohref","area");
        checkDeprecatedAttributes3("profile","head");
        checkDeprecatedAttributes3("scheme","meta");
        checkDeprecatedAttributes3("scope","td");
        checkDeprecatedAttributes3("target","link");
        checkDeprecatedAttributes3("valuetype","param");
        checkDeprecatedAttributes3("version","html");
    }



}




