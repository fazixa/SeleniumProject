package com.seleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 7/12/2020.
 */
public class SemanticRelatedness {
    private static WebDriver driver;
    public SemanticRelatedness() {
        System.setProperty("webdriver.chrome.driver", "chromeDriver/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get("https://www.wikipedia.org/");
    }

    public boolean foundPage(String str1,String str2){
        boolean found=true;
        WebElement field = driver.findElement(By.id("searchInput"));
        field.sendKeys(str1);
        field.submit();
        String currentURL = this.driver.getCurrentUrl();
        String notFoundURL = "wikipedia.org/wiki/Special";
        if(currentURL.contains(notFoundURL)){
            found = false;
            System.out.println("NOt found!!!!");
            return found;
        }
        driver.navigate().back();
        field=driver.findElement(By.id("searchInput"));
        field.sendKeys(str2);
        field.submit();
        currentURL = this.driver.getCurrentUrl();
        notFoundURL = "wikipedia.org/wiki/Special";
        if(currentURL.contains(notFoundURL)){
            found = false;
            System.out.println("NOt found!!!!");
            return found;
        }
        return found;

    }

    private int numOfOccurranceAandB(List<String> links, String link){
        int counter = 0;
        for (String l: links){
            if(l.equals(link)){
                counter ++;
            }
        }
        return counter;
    }

    public int numOfCommonRefrencesC(List<String> links1, List<String> links2){

        List<String> commonRefrences = new ArrayList<String>(links1);
        commonRefrences.retainAll(links2);
        return  commonRefrences.size();

    }

    public void searchString(String firstStr,String secondStr) {
        this.driver.get("https://www.wikipedia.org/");
            WebElement field = driver.findElement(By.id("searchInput"));
            field.sendKeys(firstStr);
            field.submit();
            String currentURLP1 = this.driver.getCurrentUrl();
            List<WebElement> links = driver.findElements(By.tagName("a"));
            List<String> firstHrefs = new ArrayList<>();
            List<String> secondHrefs = new ArrayList<>();
            for (WebElement aTag : links) {
                    if(aTag.getAttribute("href") != null)
                    firstHrefs.add(aTag.getAttribute("href"));
            }

            driver.navigate().back();
            field=driver.findElement(By.id("searchInput"));
            field.sendKeys(secondStr);
            field.submit();
            List<WebElement> links2 = driver.findElements(By.tagName("a"));
            String currentURLP2 = this.driver.getCurrentUrl();
                for (WebElement bTag : links2) {
                    if(bTag.getAttribute("href") != null)
                        secondHrefs.add(bTag.getAttribute("href"));
                }

            int A = numOfOccurranceAandB(firstHrefs,currentURLP2);
            int B = numOfOccurranceAandB(secondHrefs,currentURLP1);
            int C = numOfCommonRefrencesC(firstHrefs,secondHrefs);
            int D = firstHrefs.size();
            int E = secondHrefs.size();
            System.out.println("A:"+A+" B:"+B+" C:"+C+" D:"+D+" E:"+E);
            double semanticRelatedness= (double)(A + B + C)/(double) (D + E);
            System.out.println("First String: "+firstStr+"\nSecond String: "+secondStr+"\nSemantic Relatedness: "+semanticRelatedness+"\n");
        }


}
