package be.kdg.acl.mapping;

import be.kdg.acl.mapping.platformmessages.RegisterAchievementEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ChessAchievementMapper {
    public List<RegisterAchievementEvent> map(List<?> chessAchievements) {
        if (chessAchievements == null) return List.of();

        return chessAchievements.stream()
                .map(this::mapOne)
                .toList();
    }

    private RegisterAchievementEvent mapOne(Object raw) {
        String code = readString(raw, "getCode", "code");
        String desc = readString(raw, "getDescription", "description");

        var e = new RegisterAchievementEvent();
        e.setExternalAchId(code);
        e.setTitle(code);
        e.setDescription(desc != null ? desc : "");
        e.setImageUrl("https://img.freepik.com/premium-vector/goals-achievements-vector-flat-illustration_93208-321.jpg?semt=ais_hybrid&w=740&q=80");
        return e;
    }



    private String readString(Object raw, String getterName, String fieldName) {
        try {
            var m = raw.getClass().getMethod(getterName);
            var v = m.invoke(raw);
            return v != null ? v.toString() : null;
        } catch (Exception ignored) {
            try {
                var f = raw.getClass().getDeclaredField(fieldName);
                f.setAccessible(true);
                var v = f.get(raw);
                return v != null ? v.toString() : null;
            } catch (Exception ignored2) {
                log.warn("Could not read {} from {}", fieldName, raw.getClass().getName());
                return null;
            }
        }
    }
}
