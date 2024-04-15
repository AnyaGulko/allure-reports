package ru.ingver.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void setupConfig() {
        Configuration.browserSize = "1200x1080";
        Configuration.baseUrl = "https://www.saucedemo.com";
        Configuration.savePageSource = false;
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void disposeWindow() {
        Selenide.closeWebDriver();
    }
}
