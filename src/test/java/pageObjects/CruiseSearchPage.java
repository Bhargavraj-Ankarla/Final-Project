package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CruiseSearchPage extends basePage{
    public CruiseSearchPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(id="header_action_nav_cruises")
    WebElement cruiseTab;
    @FindBy(xpath="//div[contains(text(),'Departure city')]/following-sibling::div//div[contains(@class,'cru-form-select-text')]")
    WebElement departureCityBox;
    @FindBy(xpath="//div[contains(@class,'c-dropdown') and contains(@class,'show')]")
    WebElement dropdown;
    @FindBy(xpath="//i[contains(@class,'fi-close')]")
    List<WebElement> closeButtons;
    @FindBy(xpath="//button[contains(@class,'cru-btn') and .//span[normalize-space()='Search']]")
    WebElement searchButton;

    public void goToCruiseMenu() {
        WebElement cruiseMenu = wait.until(ExpectedConditions.elementToBeClickable(cruiseTab));
        assert cruiseMenu != null;
        cruiseMenu.click();
        wait.until(ExpectedConditions.urlContains("/cruises"));
    }

    public void selectDepartureCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(departureCityBox));
        wait.until(ExpectedConditions.elementToBeClickable(departureCityBox)).click();
        By cityOption = By.xpath("//div[contains(@class,'c-dropdown') and contains(@class,'show')]//span[normalize-space()='" + city + "']");
        WebElement cityElem = wait.until(ExpectedConditions.elementToBeClickable(cityOption));
        assert cityElem != null;
        cityElem.click();
        wait.until(ExpectedConditions.invisibilityOf(dropdown));
    }

    public void closeAllPopups() throws InterruptedException {
        for (WebElement btn : closeButtons) {
            if (btn.isDisplayed() && btn.isEnabled()) {
                btn.click();
                Thread.sleep(500);
            }
        }
    }
    public void clickSearchButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        assert btn != null;
        btn.click();
    }
}
