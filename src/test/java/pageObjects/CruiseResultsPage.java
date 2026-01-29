package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CruiseResultsPage extends basePage {
    public CruiseResultsPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath="//div[contains(@class,'product-name')]")
    List<WebElement> cruiseProducts;
    @FindBy(xpath="//div[@class='ship-parames']/span[contains(text(),'Guest capacity:')]")
    WebElement guestCapacityElem;
    @FindBy(xpath="//div[@class='ship-parames']/span[contains(text(),'Renovated:')]")
    WebElement renovatedYear;
    @FindBy(xpath="//div[contains(@class,'cruise-ids')]/span[contains(@class,'cruise-id') and contains(text(),'Cruise ID:')]")
    WebElement cruiseId;
    @FindBy(xpath="//i[contains(@class,'fi-close')]")
    List<WebElement> closeButtons;

    public void switchToLastWindow() {
        List<String> windowList = new java.util.ArrayList<>(driver.getWindowHandles());
        if (windowList.size() > 1) {
            driver.switchTo().window(windowList.getLast());
        }
    }

    public void selectFirstCruiseProduct() {
        if (!cruiseProducts.isEmpty()) {
            WebElement firstCruise = cruiseProducts.getFirst();
            wait.until(ExpectedConditions.elementToBeClickable(firstCruise)).click();
        }
    }

    public String getGuestCapacity() {
        try {
            wait.until(ExpectedConditions.visibilityOf(guestCapacityElem));
            return guestCapacityElem.getText().replace("Guest capacity:", "").trim();
        } catch (Exception e) {
            return "N/A";
        }
    }

    public String getRenovatedYear() {
        try {
            wait.until(ExpectedConditions.visibilityOf(renovatedYear));
            return renovatedYear.getText().replace("Renovated:", "").trim();
        } catch (Exception e) {
            return "N/A";
        }
    }

    public String getCruiseId() {
        try {
            String cruiseIdText = cruiseId.getText();
            return cruiseIdText.replace("Cruise ID:", "").trim();

        } catch (Exception e) {
            return "N/A";
        }
    }
    public void closeAllPopups() {
        for (WebElement btn : closeButtons) {
            if (btn.isDisplayed() && btn.isEnabled()) {
                btn.click();
            }
        }
    }
    public void waitForCruiseProductList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'product-name')]")));
    }

    public void waitForCruiseDetailsPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ship-parames']")));
    }

}
