package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class homepage extends basePage{

    private ChromeOptions options;

    public homepage(WebDriver driver)
    {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
    }

    @FindBy(xpath="//i[contains(@class,'fi-close')]")
    List<WebElement> closeButtons;
    @FindBy(id="destinationInput")
    WebElement destinationInput;
    @FindBy(id="checkInInput")
    WebElement checkInInput;
    @FindBy(id="checkOutInput")
    WebElement checkOutInput;
    @FindBy(css = "div.c-calendar-month__title")
    WebElement calendarTitle;
    @FindBy(xpath="//div[@class='hBD91TxW6Uv2IEd2ZK_X']/i")
    WebElement guestSelector;
    @FindBy(xpath="(//i[@class='smarticon u-icon u-icon-ic_bestir_plus u-icon_ic_bestir_plus K6h5Q4uKXnll47o5TCH7'])[2]")
    WebElement addAdultsButton;
    @FindBy(xpath="//div[@class='OVZS4orBSAXrR1ijARZy']")
    WebElement confirmGuestsButton;
    @FindBy(xpath="//span[@class='tripui-online-btn-content-children ' and text()='Search']")
    WebElement finalSearch;

    public void closePopup() {
        try {
            List<WebElement> closeIcons = driver.findElements((By)closeButtons);
            for (WebElement closeIcon : closeIcons) {
                if (closeIcon.isDisplayed() && closeIcon.isEnabled()) {
                    closeIcon.click();
                    break;
                }
            }
        } catch (Exception ignored) {
        }
    }

    public void enterDestination(String destination) {
        destinationInput.sendKeys(destination);
    }

    public void openCheckInDate() {
        wait.until(ExpectedConditions.visibilityOf(checkInInput));
        wait.until(ExpectedConditions.elementToBeClickable(checkInInput)).click();
    }

    public void openCheckOutDate() {
        wait.until(ExpectedConditions.visibilityOf(checkOutInput));
        wait.until(ExpectedConditions.elementToBeClickable(checkOutInput)).click();
    }

//    public void selectDate(int day) {
//        wait.until(ExpectedConditions.visibilityOf(calendarTitle));
//        By daySelector = By.xpath("//li[@role='button' and not(contains(@class,'is-disable'))]//span[@class='day' and text()='" + day + "']");
//        wait.until(ExpectedConditions.elementToBeClickable(daySelector)).click();
//    }
    public void selectDate(int day) {
        wait.until(ExpectedConditions.visibilityOf(calendarTitle));
        By daySelector = By.xpath("//li[@role='button' and not(contains(@class,'is-disable'))]//span[@class='day' and text()='" + day + "']");
        WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(daySelector));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", dateElement);
    }


    public void openGuestSelector() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", guestSelector);
    }

    public void addAdults(int count){
        for (int i = 0; i < count; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", addAdultsButton);
        }
    }

    public void confirmGuests() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(confirmGuestsButton));
        assert btn != null;
        btn.click();
        wait.until(ExpectedConditions.invisibilityOf(confirmGuestsButton));
    }

    public void clickSearch() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(finalSearch));
            assert btn != null;
            btn.click();
    }
}