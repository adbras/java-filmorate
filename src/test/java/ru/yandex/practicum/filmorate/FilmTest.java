package ru.yandex.practicum.filmorate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilmTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldFailWhenNameIsBlank() {
        Film film = new Film();
        film.setName(""); // пустое имя
        film.setDescription("Описание");
        film.setReleaseDate(LocalDate.of(2025, 8, 26));
        film.setDuration(120);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);

        assertFalse(violations.isEmpty(), "Ожидалась ошибка при пустом названии");
    }

    @Test
    void shouldPassWhenDescriptionIs200Chars() {
        Film film = new Film();
        film.setName("Film");
        film.setDescription("a".repeat(201));
        film.setReleaseDate(LocalDate.of(2025, 8, 26));
        film.setDuration(120);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty(), "Описание должно вызывать ошибку");
    }

    @Test
    void shouldFailWhenReleaseDateIsNull() {
        Film film = new Film();
        film.setName("Film");
        film.setDescription("Описание");
        film.setReleaseDate(null); // null дата
        film.setDuration(120);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);

        assertFalse(violations.isEmpty(), "Ожидалась ошибка при null дате релиза");
    }

    @Test
    void shouldFailWhenDurationIsNegative() {
        Film film = new Film();
        film.setName("Film");
        film.setDescription("Описание");
        film.setReleaseDate(LocalDate.of(2025, 8, 26));
        film.setDuration(-10); // отрицательная продолжительность

        Set<ConstraintViolation<Film>> violations = validator.validate(film);

        assertFalse(violations.isEmpty(), "Ожидалась ошибка при отрицательной продолжительности");
    }
}