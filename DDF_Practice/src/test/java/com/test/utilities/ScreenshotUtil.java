package com.test.utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) {

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("./screenshots/" + testName + ".png");

            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}