package com.example.restservice;

import java.util.List;

/** Root object for roster JSON file. */
public record RosterFileDto(
    List<RosterEntryDto> rookie,
    List<RosterEntryDto> champion,
    List<RosterEntryDto> ultimate,
    List<RosterEntryDto> mega
) {
    public RosterFileDto() {
        this(List.of(), List.of(), List.of(), List.of());
    }
}
