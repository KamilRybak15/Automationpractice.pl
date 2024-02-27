package tests;

import enums.MessageSubject;
import model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ContactUsFromPage;
import pages.TopMenuPage;
import utils.PageTitleUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactUsTest extends BaseTest {

    private TopMenuPage topMenuPage;
    private ContactUsFromPage contactUsFromPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        topMenuPage = new TopMenuPage(driver);
        contactUsFromPage = new ContactUsFromPage(driver);

    }

    @Test
    public void shouldNotAllowToSentEmptyContactUsForm() {
        topMenuPage.clickOnContactUsLink();
        contactUsFromPage.clickOnSendButton();

        assertThat(contactUsFromPage.isRedAlertDisplayed()).isTrue();
    }

    @Test
    public void shouldNotAllowToSendContactUsFormWithEmailOnly() {
        topMenuPage.clickOnContactUsLink();
        contactUsFromPage.enterEmail("test@test.com");
        contactUsFromPage.clickOnSendButton();

        assertThat(contactUsFromPage.isRedAlertDisplayed()).isTrue();
    }

    @Test
    public void shouldSendContactUsFromWithValidData() {
        topMenuPage.clickOnContactUsLink();

        Message message = new Message();
        message.setSubject(MessageSubject.WEBMASTER);
        message.setEmail("test@test.com");
        message.setOrderReference("123");
        message.setMessage("Jakaś wiadomość");

        contactUsFromPage.sendContactUsForm(message);

        assertThat(contactUsFromPage.isGreenAlertDisplayed()).isTrue();
    }
}
