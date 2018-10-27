package ru.boiko.se.lessonseven;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;

@Getter
@Setter
@ApplicationScoped
public class Config {
    private Integer port = 8179;
    private String host = "localhost";
}
