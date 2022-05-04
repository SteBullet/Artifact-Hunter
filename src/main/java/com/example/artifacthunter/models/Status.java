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
     * Marked artifact cell that hasn't been visited by a player
     */
    ARTIFACT_NOT_VISITED_MARKED,
    /**
     * Dangerous cell that hasn't been visited by a player
     */
    DANGEROUS_NOT_VISITED,
    /**
     * Marked dangerous cell that hasn't been visited by a player
     */
    DANGEROUS_NOT_VISITED_MARKED,
    /**
     * Marked safe cell that hasn't been visited by a player
     */
    NOT_VISITED_MARKED,
    /**
     * Safe cell that have been visited by a player
     */
    VISITED,
    /**
     * Safe cell that hasn't been visited by a player
     */
    NOT_VISITED,
    /**
     * Player found the artifact, win
     */
    PLAYER_ARTIFACT,
    /**
     * Player on dangerous cell, end of game
     */
    PLAYER_DANGEROUS,
    DANGEROUS_OPEN,
    ARTIFACT_OPEN
}
