package com.example.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Message {

    private UUID uuid;
    private Integer priority;

}
