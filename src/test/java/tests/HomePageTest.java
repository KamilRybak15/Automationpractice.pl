package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BestsellerItemsPage;
import utils.PageTitleUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTest extends BaseTest {
    private BestsellerItemsPage bestSellerItemsPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        bestSellerItemsPage = new BestsellerItemsPage(driver);
    }

    @Test
    public void shouldSeeItemsOnBestsellersPage() {
        bestSellerItemsPage.clickOnBestsellerButton();
        List<String> productNames = bestSellerItemsPage.getBestsellersNames();

        List<String> productWithEmptyName = productNames.stream()
                .filter(el -> el.isEmpty())
                .collect(Collectors.toList());

        assertThat(productWithEmptyName).isEmpty();
    }
}
