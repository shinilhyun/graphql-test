package shin.aiden.graphqltest;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Component
public class CursorUtil {

    public ConnectionCursor from(int id) {
        return new DefaultConnectionCursor(Base64.getEncoder().encodeToString(String.valueOf(id).getBytes(StandardCharsets.UTF_8)));
    }

    public int decodeCursorWith(String id) {
        return Integer.parseInt(new String(Base64.getDecoder().decode(id)));
    }

    public <T> ConnectionCursor getFirstCursorFrom(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(0).getCursor();
    }

    public <T> ConnectionCursor getLastCursorFrom(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(edges.size() - 1).getCursor();
    }
}
