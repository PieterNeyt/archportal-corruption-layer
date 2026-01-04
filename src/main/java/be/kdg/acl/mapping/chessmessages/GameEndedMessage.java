package be.kdg.acl.mapping.chessmessages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameEndedMessage extends ChessBaseMessage {
    private String gameId;
    private String whitePlayer;
    private String blackPlayer;
    private String finalFen;
    private String endReason; // CHECKMATE of DRAW
    private String winner;    // WHITE, BLACK of DRAW
    private int totalMoves;
}