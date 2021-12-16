package com.itms.training.java.selenium.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class TipsAndTricks extends BaseTestNGTest {

    @Test
    public void tabs() {
        webDriver.get("https://demoqa.com/browser-windows");

        // open new tab
        webDriver.findElement(By.id("tabButton")).click();

        // hold all window handles in array list
        ArrayList<String> newTb = new ArrayList<String>(webDriver.getWindowHandles());

        // switch to new tab
        webDriver.switchTo().window(newTb.get(1));

        // verify new tab info
        Assert.assertEquals(
                webDriver.findElement(By.id("sampleHeading")).getText(),
                "This is a sample page");

        // close the tab
        webDriver.close();

        // switch to parent tab
        webDriver.switchTo().window(newTb.get(0));

        // verify parent tab info
        Assert.assertEquals(webDriver.getTitle(), "ToolsQA");
    }

    @Test
    public void windows() {
        webDriver.get("https://demoqa.com/browser-windows");

        // open new child window within the main window
        webDriver.findElement(By.id("windowButton")).click();

        String mainWindow = webDriver.getWindowHandle();
        Set<String> s = webDriver.getWindowHandles();
        Iterator<String> iS = s.iterator();

        while (iS.hasNext()) {
            String childWindow = iS.next();
            if (!mainWindow.equalsIgnoreCase(childWindow)){
                webDriver.switchTo().window(childWindow);
                Assert.assertEquals(
                        webDriver.findElement(By.id("sampleHeading")).getText(),
                        "This is a sample page");
                webDriver.close();
            }
        }

        //  Switch back to the main window which is the parent window.
        webDriver.switchTo().window(mainWindow);

        // verify parent tab info
        Assert.assertEquals(webDriver.getTitle(), "ToolsQA");
    }

    @Test
    public void alerts() {
        webDriver.get("https://demoqa.com/alerts");

        // open an alert
        webDriver.findElement(By.id("alertButton")).click();

        Alert alert = webDriver.switchTo().alert();

        // verify alert message
        Assert.assertEquals(alert.getText(), "You clicked a button");
        alert.accept();
    }

    @Test
    public void iFrames() {
        webDriver.get("https://demoqa.com/frames");

        // locating frame1 using its id
        WebElement frame1 = webDriver.findElement(By.id("frame1"));

        // switching the WebDriver context to frame1
        webDriver.switchTo().frame(frame1);

        // identifying the frame heading in a WebElement
        WebElement frame1Heading= webDriver.findElement(By.id("sampleHeading"));

        // verify the text in an iframe
        Assert.assertEquals(frame1Heading.getText(), "This is a sample page");

    }

    @Test
    public void rightClick() {
        webDriver.get("https://demoqa.com/buttons");

        // Instantiate Action Class
        Actions actions = new Actions(webDriver);

        //Retrieve WebElement to perform right click
        WebElement btnElement = webDriver.findElement(By.id("rightClickBtn"));

        //Right Click the button to display Context Menu&nbsp;
        actions.contextClick(btnElement).perform();

        // Assert rightclick
        Assert.assertEquals(
                webDriver.findElement(By.id("rightClickMessage")).getText(),
                "You have done a right click");
    }

    @Test
    public void iframeTests() {
        webDriver.get("https://demoqa.com/frames");
        webDriver.switchTo().frame(webDriver.findElement(By.id("frame1")));
        webDriver.findElement(By.id("sampleHeading"));
    }
}
