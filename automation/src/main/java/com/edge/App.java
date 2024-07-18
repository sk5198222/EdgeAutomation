package com.edge;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class App 
{
    private static WebDriver dr;
    private static App app;
    public static void main( String[] args ) throws InterruptedException
    {
        app=new App();
        app.init();
        app.repeatSearch(33);
        app.tearDown();
    }

    private void init(){
        dr=new EdgeDriver();
        dr.navigate().to("https://www.bing.com");
    }

    private void repeatSearch(int reps) throws InterruptedException{
        for(int i=1; i<=reps; i++){
            search();
        }
    }

    private void search() throws InterruptedException{
        WebElement ele = dr.findElement(By.id("sb_form_q"));
        Thread.sleep(1000);
        ele.clear();
        ele.sendKeys(app.ranStr());
        Thread.sleep(4000);
        ele.submit();
    }

    private final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final Random rd = new Random();

    private String ranStr(){
        StringBuilder sb = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            if(i==10){
                sb.append(' ');
                continue;
            }
            sb.append(chars.charAt(rd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private void tearDown(){
        dr.close();
        dr.quit();
    }
}
