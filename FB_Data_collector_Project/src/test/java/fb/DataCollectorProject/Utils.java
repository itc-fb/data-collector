package fb.DataCollectorProject;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Utils {
    public static void  maximizeWindow(WebDriver dr){
        dr.manage().window().maximize();
    }
    public static void doTimeOuts(WebDriver dr, int seconds ){
        dr.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static void getUrl(WebDriver dr, String baseUrl){
        dr.get(baseUrl);
    }

}
