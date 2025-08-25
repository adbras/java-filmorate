package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Film {
    private int id;

    @NotBlank(message = "Название не должно быть пустым")
    private String name;

    @Size(max = 200, message = "Описание фильма не должно превышать 200 символов")
    private String description;

    @NotNull(message = "Дата релиза обязательна должна быть")
    private LocalDate releaseDate;

    @Positive(message = "Не может быть минусовая продолжительность фильма")
    private int duration;
}