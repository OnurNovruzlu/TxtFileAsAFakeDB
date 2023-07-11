package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private int id;
    private String name;
    private ZonedDateTime insertDate = ZonedDateTime.now(ZoneId.of("UTC+4"));
    private String email;
    private String password;
}
