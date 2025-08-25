package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private Integer id;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Email введен некорректно")
    private String email;

    @NotBlank(message = "Логин не должно быть пустым")
    @Pattern(regexp = "\\S+", message = "Логин не должен содержать пробел")
    private String login;

    private String name;

    @PastOrPresent(message = "Дата рождения некорректная")
    private LocalDate birthday;

}