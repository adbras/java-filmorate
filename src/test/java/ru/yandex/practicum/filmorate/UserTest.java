package ru.yandex.practicum.filmorate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldFailWhenEmailIsBlank() {
        User user = new User();
        user.setEmail(""); // пустой email
        user.setLogin("abdras");
        user.setBirthday(LocalDate.of(2025, 8, 26));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Ожидалась ошибка при пустом email");
    }

    @Test
    void shouldFailWhenEmailIsInvalid() {
        User user = new User();
        user.setEmail("abdras-email"); // некорректный email
        user.setLogin("abdras");
        user.setBirthday(LocalDate.of(2025, 8, 26));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Ожидалась ошибка при некорректном email");
    }

    @Test
    void shouldFailWhenLoginIsBlank() {
        User user = new User();
        user.setEmail("abdras@yandex.kz");
        user.setLogin(""); // пустой логин
        user.setBirthday(LocalDate.of(2025, 8, 26));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Ожидалась ошибка при пустом логине");
    }

    @Test
    void shouldFailWhenLoginHasSpaces() {
        User user = new User();
        user.setEmail("abdras@yandex.kz");
        user.setLogin("abdras abdras"); // логин с пробелом
        user.setBirthday(LocalDate.of(2025, 8, 26));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Ожидалась ошибка при логине с пробелами");
    }

    @Test
    void shouldFailWhenBirthdayIsInFuture() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setLogin("abdras");
        user.setBirthday(LocalDate.now().plusDays(1)); // будущее

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Ожидалась ошибка при дате рождения в будущем");
    }

    @Test
    void getNameShouldReturnLoginIfNameIsNullOrBlank() {
        User user = new User();
        user.setLogin("abdras");
        user.setName(null);
        assertEquals("abdras", user.getName());

        user.setName("");
        assertEquals("abdras", user.getName());

        user.setName("   ");
        assertEquals("abdras", user.getName());
    }

    @Test
    void getNameShouldReturnNameIfNotBlank() {
        User user = new User();
        user.setLogin("abdras");
        user.setName("Aizat");
        assertEquals("Aizat", user.getName());
    }
}
