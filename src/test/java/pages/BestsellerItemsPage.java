package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class BestsellerItemsPage extends BasePage {

    public BestsellerItemsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".blockbestsellers")
    WebElement bestsellersButton;
    @FindBy(css = "#blockbestsellers .product-name")
    List<WebElement> bestsellersNames;

    public void clickOnBestsellerButton() {
        bestsellersButton.click();
    }

    public List<String> getBestsellersNames() {
        return bestsellersNames.stream()
                .map(el -> el.getText().trim())
                .collect(Collectors.toList());
    }
}


