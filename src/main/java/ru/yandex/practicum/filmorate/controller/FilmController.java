package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.ValidationException;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();
    private int filmId = 1;

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            film.setName("common");
        }

        validateReleaseDate(film);
        film.setId(filmId++);
        films.put(film.getId(), film);
        log.info("Добавлен фильм: {}", film.getName());
        return film;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        if (film.getId() == 0 || !films.containsKey(film.getId())) {
            log.error("Фильм с ID {} не найден", film.getId());
            throw new ValidationException("Фильм не найден");
        }
        validateReleaseDate(film);
        films.put(film.getId(), film);
        log.info("Обновлён фильм: {}", film.getName());
        return film;
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return films.values();
    }

    private void validateReleaseDate(Film film) {
        LocalDate minDate = LocalDate.of(1895, 12, 28);
        if (film.getReleaseDate().isBefore(minDate)) {
            log.error("Некорректная дата релиза: {}", film.getReleaseDate());
            throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года");
        }
    }
}