package eapli.base.ordermanagement.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class CommentTest {

    private static final String validComment = "comment";
    private static final String invalidComment1 = "";
    private static final String invalidComment2 = "  ";

    @Test
    public void ensureCreatesWithValidComment() {
        Comment comment = new Comment(validComment);
        assertNotNull(comment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveValidComment() {
        new Comment(invalidComment1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveNotNullComment() {
        new Comment(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNotHaveEmptyComment() {
        new Comment(invalidComment2);
    }
}