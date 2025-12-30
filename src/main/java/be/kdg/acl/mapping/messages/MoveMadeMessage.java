package be.kdg.acl.mapping.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveMadeMessage extends ChessBaseMessage {
    private String gameId;
    private String fromSquare;
    private String toSquare;
    private String sanNotation;
    private String fenAfterMove;
    private String player; // WHITE of BLACK
    private int moveNumber;
    private String whitePlayer;
    private String blackPlayer;
    private String moveTime;
}