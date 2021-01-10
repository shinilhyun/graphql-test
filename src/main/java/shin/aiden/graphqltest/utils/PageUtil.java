package shin.aiden.graphqltest.utils;

import graphql.relay.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Component
public class PageUtil {

    public ConnectionCursor cursorFrom(int id) {
        return new DefaultConnectionCursor(Base64.getEncoder().encodeToString(String.valueOf(id).getBytes(StandardCharsets.UTF_8)));
    }

    public int decodeCursorWith(String id) {
        return Integer.parseInt(new String(Base64.getDecoder().decode(id)));
    }

    private <T> ConnectionCursor getFirstCursorFrom(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(0).getCursor();
    }

    private <T> ConnectionCursor getLastCursorFrom(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(edges.size() - 1).getCursor();
    }

    public <T> Edge<T> edge(T source, int id) {
        return new DefaultEdge<>(source, cursorFrom(id));
    }

    private <T> PageInfo pageInfo(List<Edge<T>> edges, int size, String cursor) {
        return new DefaultPageInfo(
                getFirstCursorFrom(edges),
                getLastCursorFrom(edges),
                cursor != null,
                edges.size() >= size);
    }

    public <T> Connection<T> connection(List<Edge<T>> edges, int size, String cursor) {
        PageInfo pageInfo = pageInfo(edges, size, cursor);
        return new DefaultConnection<>(edges, pageInfo);
    }
}
