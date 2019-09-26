package fb.DataCollectorProject;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

class Utils {
    static void  maximizeWindow(WebDriver dr){
                dr.manage().window().maximize();
    }
    static void doTimeOuts(WebDriver dr){
        dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    static void getUrl(WebDriver dr){
        dr.get(Constants.BASE_URL);
    }
}
