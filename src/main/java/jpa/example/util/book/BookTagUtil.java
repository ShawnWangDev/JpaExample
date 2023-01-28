package jpa.example.util.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import jpa.example.entity.book.Book;
import lombok.Getter;

@Getter
public class BookTagUtil {
    public static Map<String, List<Book>> bookListToTagMap(List<Book> books, String tokens) {
        Map<String, List<Book>> tagBookMap = new HashMap<>();
        for (Book book : books) {
            StringTokenizer tokenizer = new StringTokenizer(book.getTags(), tokens);
            while (tokenizer.hasMoreTokens()) {
                String tag = tokenizer.nextToken();
                if (!tagBookMap.containsKey(tag)) {
                    tagBookMap.put(tag, new ArrayList<>());
                }
                tagBookMap.get(tag).add(book);
            }
        }
        return tagBookMap;
    }
}
