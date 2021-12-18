package com.itms.training.java.selenium.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    public void window() {
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
    public void windows() {
        webDriver.get("https://demoqa.com/browser-windows");

        // open new child window within the main window
        webDriver.findElement(By.id("windowButton")).click();

        // open new child window within the main window
        webDriver.findElement(By.id("messageWindowButton")).click();

        // Get MainWindow
        String mainWindow = webDriver.getWindowHandle();

        // Get All Windows
        Set<String> s = webDriver.getWindowHandles();
        Iterator<String> iS = s.iterator();

        // loop through each Window
        while (iS.hasNext()) {
            String childWindow = iS.next();
            // check if the window is not the main one
            if (!mainWindow.equalsIgnoreCase(childWindow)){
                webDriver.switchTo().window(childWindow);
                System.out.println(webDriver.findElement(By.cssSelector("body")));
                webDriver.close();
            }
        }

        //  Switch back to the main window which is the parent window.
        webDriver.switchTo().window(mainWindow);

        // verify parent tab info
        Assert.assertEquals(webDriver.getTitle(), "ToolsQA");
    }

    @Test
    public void alertAccept() {
        webDriver.get("https://demoqa.com/alerts");

        // open an alert
        webDriver.findElement(By.id("alertButton")).click();

        Alert alert = webDriver.switchTo().alert();

        // verify alert message
        Assert.assertEquals(alert.getText(), "You clicked a button");
        alert.accept();
    }

    @Test
    public void alertAcceptWithMessage() {
        webDriver.get("https://demoqa.com/alerts");

        // open an alert
        webDriver.findElement(By.id("promtButton")).click();

        webDriverWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();

        // verify alert message
        Assert.assertEquals(alert.getText(), "Please enter your name");
        alert.sendKeys("ITMS Coaching");
        alert.accept();
    }

    @Test
    public void alertDismissed() {
        webDriver.get("https://demoqa.com/alerts");

        // open an alert
        webDriver.findElement(By.id("promtButton")).click();

        webDriverWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();

        // verify alert message
        Assert.assertEquals(alert.getText(), "Please enter your name");
        alert.dismiss();
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

        // switch to default content
        webDriver.switchTo().defaultContent();

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
    public void table() {
        webDriver.get("https://demoqa.com/webtables");

        WebElement table = webDriver.findElement(By.cssSelector("div.ReactTable"));
        WebElement header = table.findElement(By.cssSelector("div.rt-thead"));
        WebElement body = table.findElement(By.cssSelector("div.rt-tbody"));

        List<WebElement> headers = header.findElements(By.cssSelector("div.rt-th"));
        List<WebElement> bodyRows = body.findElements(By.cssSelector("div.rt-tr-group"));

        JSONArray data = new JSONArray();

        for (int i = 0; i < bodyRows.size(); i++) {
            WebElement row = bodyRows.get(i);
            JSONObject rowData = new JSONObject();
            List<WebElement> cells = row.findElements(By.cssSelector("div.rt-td"));
            for (int j = 0; j < cells.size(); j ++) {
                WebElement cell = cells.get(j);
                if (cell.getText().equals("")) {
                    continue;
                }
                rowData.put(headers.get(j).getText(), cell.getText().trim());
            }
            if (!rowData.getString("Email").equals("")) {
                data.put(rowData);
            }
        }

        System.out.println(data);
    }
}
