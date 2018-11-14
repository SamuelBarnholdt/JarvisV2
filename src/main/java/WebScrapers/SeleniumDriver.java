package WebScrapers;
import Utils.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SeleniumDriver {
    public SeleniumDriver(){

    }
    public static LinkedList<String> getFinishedGoals(String username, String passwords) throws BadUserCredentialsException{
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String baseUrl = "http://auportal.herokuapp.com/";
        driver.get(baseUrl);
        driver.findElement(By.linkText("Sign in")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user_email")));
        WebElement name = ((ChromeDriver) driver).findElementById("user_email");

        name.sendKeys(username);
        WebElement password = ((ChromeDriver) driver).findElementById("user_password");
        password.sendKeys(passwords);
        driver.findElement(By.name("commit")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Achievements")));
        driver.findElement(By.linkText("Achievements")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("heading")));
        List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr"));
        LinkedList<String> list = new LinkedList<String>();

        for (WebElement element : elements){
            if (element.getAttribute("innerHTML").contains("background-color: green; border"))
                list.add(StringUtils.cut(element.getText()));
            }
            driver.quit();
        if (list.isEmpty()) throw new BadUserCredentialsException("Wrong username or password.");
        return list;

        }
    private static class BadUserCredentialsException extends IOException{
        public BadUserCredentialsException(String errormessage){
            super(errormessage);
        }
    }
    public static void main(String[] args) throws BadUserCredentialsException{
        SeleniumDriver driver = new SeleniumDriver();
        driver.getFinishedGoals("","");
    }
}
