package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CPerson {
    private int id;
    private String name;
    private ZonedDateTime insertDate;
    private String email;
    private String password;
}
