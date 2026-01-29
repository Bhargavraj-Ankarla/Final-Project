package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class HotelResultsPage extends basePage{
    public HotelResultsPage(WebDriver driver)
    {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(css = "div.style_hotel-count-tip__TAqzw[role='heading']")
    WebElement resultsCount;
    @FindBy(xpath="//div[contains(@class,'style_radio-item__6Kt3T') and @role='checkbox' and @aria-label='Pool']")
    WebElement poolFilter;
    @FindBy(xpath="//div[contains(@class,'hotel-card')]")
    List<WebElement> hotelCards;
    @FindBy(xpath="//span[contains(@class,'style_dropdown-selector__iZHJ2') and .//span[contains(text(),'Trip.com recommended')]]")//Recommended for Families
    WebElement sortDropdown;
    @FindBy(xpath="//span[contains(@class,'style_dropdown-selector-options__BelBO') and normalize-space(text())='Top reviewed']")
    WebElement topReviewedOption;


    public String getResultsCount() {
        return resultsCount.getAttribute("aria-label");
    }
    public void applyPoolFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(poolFilter)).click();
    }
    public void refreshPage() {
        driver.navigate().refresh();
    }
    public List<WebElement> getHotelCards() {
        return hotelCards;
    }
    public void sortByTopReviewed() {
        wait.until(ExpectedConditions.visibilityOf(sortDropdown));
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
        wait.until(ExpectedConditions.visibilityOf(topReviewedOption));
        wait.until(ExpectedConditions.elementToBeClickable(topReviewedOption)).click();
    }

}
