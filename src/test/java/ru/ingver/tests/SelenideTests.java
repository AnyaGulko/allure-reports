package ru.ingver.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTests extends TestBase {

    @Test
    @Feature("Issue в репозитории")
    @Story("Поиск Issue")
    @Owner("Anna Gulko")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Поиск issue в репозитории по названию")
    public void testIssueSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".search-input-container").click();
        $("#query-builder-test").setValue("eroshenkoam/allure-example").pressEnter();
        $(byLinkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("Issue_created_to_test_allure_reports")).should(Condition.exist);

    }
}
