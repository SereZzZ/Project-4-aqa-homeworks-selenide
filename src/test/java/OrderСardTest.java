import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderСardTest {

private String generateDate(long addDays, String pattern) {
    return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
}

    @Test
    void shouldPositiveCardDeliveryOrder() throws InterruptedException {
        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").setValue("Тверь");
        String planDate = generateDate(3 , "dd.MM.yyyy");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(planDate);
        $("[data-test-id='name'] input").setValue("Гаврилив Сергей");
        $("[data-test-id='phone'] input").setValue("+79969236311");
        $("[data-test-id='agreement']").click();
        $("[class='button__content']").click();
        $("[class='notification__content']").shouldBe(Condition.visible,Duration.ofSeconds(15)).shouldHave(Condition.text("Встреча успешно забронирована на " + planDate));
    }
}


