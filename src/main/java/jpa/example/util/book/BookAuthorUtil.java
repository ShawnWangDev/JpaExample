package jpa.example.util.book;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BookAuthorUtil {
    public static List<String> authorsRawArrayToList(String rawAuthors, String tokens){
        List<String> authorList=new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(rawAuthors, tokens);
        while (tokenizer.hasMoreTokens()) {
            authorList.add(tokenizer.nextToken());
        }
        return authorList;
    }
}
