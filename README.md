

# Game Integration Guide: Messaging & Publishing

This guide provides the technical specifications required to connect your game to our platform's **Anti-Corruption Layer (ACL)**. Communication is handled via **RabbitMQ** using JSON-formatted messages.

## 1. Connection Settings

To send events to the platform, your game server must connect to our RabbitMQ broker using the following credentials:

*   **Host:** `localhost` (or the provided environment IP)
*   **Port:** `5672`
*   **Username:** `user`
*   **Password:** `password`
*   **Exchange:** `gameExchange` (Type: `topic`)

If you are using Spring Boot, add these to your `application.properties`:
```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=user
spring.rabbitmq.password=password
```

---

## 2. Technical Requirements (Java/Spring Users)

Our ACL uses strict type mapping to ensure data integrity.

### Message Type Identifier
If your game is built with **Spring Boot**, our system expects a `__TypeId__` header in the RabbitMQ message. If you are not using Spring, ensure your JSON payload contains the `messageType` property.

The following Type IDs are recognized by our system:
*   `be.kdg.i5.chess.messaging.messages.GameRegisteredMessage`
*   `be.kdg.i5.chess.messaging.messages.AchievementAcquiredMessage`
*   `be.kdg.i5.chess.messaging.messages.GameCreatedMessage`
*   `be.kdg.i5.chess.messaging.messages.MoveMadeMessage`
*   `be.kdg.i5.chess.messaging.messages.GameEndedMessage`

---

## 3. Message Structures

### A. Game Registration (`GAME_REGISTERED`)
Send this message when your game server starts or when you want to update your game's metadata and available achievements.

**Payload:**
```json
{
  "messageType": "GAME_REGISTERED",
  "timestamp": "2024-05-20T10:00:00Z",
  "registrationId": "unique-game-identifier",
  "frontendUrl": "https://yourgame.com/play",
  "pictureUrl": "https://yourgame.com/thumbnail.png",
  "availableAchievements": [
    {
      "code": "FIRST_WIN",
      "description": "Awarded for winning your first match."
    },
    {
      "code": "MASTER_STRATEGIST",
      "description": "Win a match in under 20 moves."
    }
  ]
}
```

### B. Achievement Unlocked (`ACHIEVEMENT_ACQUIRED`)
Send this message whenever a player earns an achievement.

**Payload:**
```json
{
  "messageType": "ACHIEVEMENT_ACQUIRED",
  "timestamp": "2024-05-20T10:05:00Z",
  "achievementType": "FIRST_WIN",
  "playerId": "user-unique-id",
  "playerName": "Gamer123"
}
```

### C. Gameplay Events
The following messages are used for tracking match progress and statistics.

#### 1. Game Created (`GAME_CREATED`)
```json
{
  "messageType": "GAME_CREATED",
  "gameId": "match-abc-123",
  "whitePlayer": "Player1",
  "blackPlayer": "Player2",
  "status": "STARTED"
}
```

#### 2. Move Made (`MOVE_MADE`)
```json
{
  "messageType": "MOVE_MADE",
  "gameId": "match-abc-123",
  "fromSquare": "e2",
  "toSquare": "e4",
  "player": "WHITE",
  "moveNumber": 1
}
```

#### 3. Game Ended (`GAME_ENDED`)
```json
{
  "messageType": "GAME_ENDED",
  "gameId": "match-abc-123",
  "winner": "WHITE",
  "endReason": "CHECKMATE",
  "totalMoves": 24
}
```

---

## 4. Data Constraints & Enumerations

To ensure your messages are processed correctly, use the following exact string values (case-sensitive):

| Field | Allowed Values |
| :--- | :--- |
| **messageType** | `GAME_CREATED`, `GAME_ENDED`, `MOVE_MADE`, `GAME_REGISTERED`, `ACHIEVEMENT_ACQUIRED` |
| **player** | `WHITE`, `BLACK` |
| **winner** | `WHITE`, `BLACK`, `DRAW` |
| **endReason** | `CHECKMATE`, `DRAW` |

---

## 5. Debugging & Support

*   **Internal Service Port:** The ACL service runs on port `8087`.
*   **Logs:** Successful message ingestion will trigger a log entry: `Received Chess Message from RabbitMQ` followed by the `msg type`.
*   **Errors:** If a message is sent with an incorrect `messageType` or missing fields, the ACL will log a warning: `Unsupported chess message type` and the event will not be forwarded to the platform.