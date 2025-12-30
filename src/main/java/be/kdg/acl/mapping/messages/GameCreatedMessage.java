package be.kdg.acl.mapping.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameCreatedMessage extends ChessBaseMessage {
    private String gameId;
    private String whitePlayer;
    private String blackPlayer;
    private String currentFen;
    private String status;
}