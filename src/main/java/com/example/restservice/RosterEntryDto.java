package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Serializable roster entry for file persistence.
 * ownerUserId/ownerUsername are null for initial (catalog) entries.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RosterEntryDto(String charaId, long addedAt, String ownerUserId, String ownerUsername) { }
