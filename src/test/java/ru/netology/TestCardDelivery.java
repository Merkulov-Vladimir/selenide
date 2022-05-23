package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TestCardDelivery {
    LocalDate currentDate = LocalDate.now();

    @BeforeEach
    void openWebsite() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        LocalDate date = currentDate.plusDays(3);
        String appointmentDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=Город]").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);//clear field
        $("[placeholder=\"Дата встречи\"]").setValue(appointmentDate);
        $(byName("name")).setValue("Иванов Иван");
        $(byName("phone")).setValue("+79765132341");
        $(".checkbox").click();
        //$(".grid-row button").click();
        $(withText("Забронировать")).click();
       // $(".notification__title").shouldHave(ownText("Успешно!"), Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на \r\n" +
                appointmentDate), Duration.ofSeconds(15));
    }
}


