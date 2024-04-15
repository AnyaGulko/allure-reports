package ru.ingver;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class WebSteps {
    private final SelenideElement searchInput = $(".search-input-container"),
            queryBuilder = $("#query-builder-test"),
            issuesTab = $("#issues-tab"),
            resultsList = $(byAttribute("data-testid", "results-list"));

    private final ElementsCollection nameOfIssue = $$(byAttribute("aria-label", "Issues"));


    @Step("Открываем главную страницу")
    public WebSteps openMainPage() {
        open("https://github.com");
        return this;
    }

    @Step("Ищем репозиторий {repository}")
    public WebSteps searchForRepository(String repository) {
        searchInput.click();
        queryBuilder.setValue(repository).pressEnter();
        return this;
    }

    @Step("Кликаем по ссылке {repository}")
    public WebSteps clickOnRepositoryLink(String repository) {
        resultsList.find(byPartialLinkText(repository)).click();
        return this;
    }

    @Step("Открываем таб issues")
    public WebSteps openIssuesTab() {
        issuesTab.click();
        return this;
    }

    @Step("Проверяем что на странице есть issues с названием {value}")
    public WebSteps shouldSeeIssueName(String value) {
        nameOfIssue
                .findBy(attribute("role", "group"))
                .find(byTagAndText("a", value))
                .shouldHave(text(value));
        return this;
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}

