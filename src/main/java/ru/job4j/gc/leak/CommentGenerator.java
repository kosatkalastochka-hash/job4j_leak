package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    public static final String PATH_PHRASES = "files/phrases.txt";

    public static final String SEPARATOR = System.lineSeparator();
    private final List<Comment> COMMENTS = new ArrayList<>();
    public static final Integer COUNT = 50;
    private  List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return COMMENTS;
    }

    @Override
    public void generate() {
        read();
        int phrasesSize = phrases.size();
        for (int i = 0; i < COUNT; i++) {
            String text = String.format("%s%s%s%s%s",
                    phrases.get(random.nextInt(phrasesSize)), SEPARATOR,
                    phrases.get(random.nextInt(phrasesSize)), SEPARATOR,
                    phrases.get(random.nextInt(phrasesSize)));
            var comment = new Comment();
            comment.setText(text);
            comment.setUser(userGenerator.randomUser());
            COMMENTS.add(comment);
        }
    }

}