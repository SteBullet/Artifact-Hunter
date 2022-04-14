package com.example.artifacthunter.models;

public enum Status {
    /**
     * Cell that has been visited by a player
     */
    PLAYER_VISITED,
    /**
     * Artifact cell that hasn't been visited by a player
     */
    ARTIFACT_NOT_VISITED,
    /**
     *
     */
    DANGEROUS_NOT_VISITED,
    /**
     *
     */
    DANGEROUS_NOT_VISITED_MARKED,
    /**
     *
     */
    NOT_VISITED_MARKED,
    /**
     *
     */
    VISITED,
    /**
     *
     */
    NOT_VISITED,
    PLAYER_ARTIFACT,
    PLAYER_DANGEROUS,
    DANGEROUS_VISITED,
    /**
     *
     */
    ARTIFACT_NOT_VISITED_MARKED
}
