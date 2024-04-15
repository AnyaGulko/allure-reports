package ru.ingver.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ingver.WebSteps;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class StepsTest extends TestBase {
    final static String REPOSITORY = "eroshenkoam/allure-example";
    final static String ISSUE_NAME = "Issue_created_to_test_allure_reports";

    @Test
    @Feature("Issue в репозитории")
    @Story("Поиск Issue")
    @Owner("Anna Gulko")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Поиск issue в репозитории по названию c лямбдами")
    public void lambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".search-input-container").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозтитория" + REPOSITORY, () -> {
            $(byLinkText(REPOSITORY)).click();
        });
        step("Открываем таб issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем что на странице есть issues с названием" + ISSUE_NAME, () -> {
            $(withText("Issue_created_to_test_allure_reports")).should(Condition.exist);
            attachment("Sourse", webdriver().driver().source());
        });
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Поиск Issue")
    @Owner("Anna Gulko")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Поиск issue в репозитории по названию с аннотациями @Step")
    public void annotatedStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps webStepsTest = new WebSteps();

        webStepsTest.openMainPage()
                .searchForRepository(REPOSITORY)
                .clickOnRepositoryLink(REPOSITORY)
                .openIssuesTab()
                .shouldSeeIssueName(ISSUE_NAME)
                .takeScreenshot();
    }
}
