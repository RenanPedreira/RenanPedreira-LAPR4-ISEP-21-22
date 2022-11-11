package eapli.base.common.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IdentifierTest {

    private static final Identifier id = new Identifier("CU-123456789");

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveValidId(){
        new Identifier("Cu-12");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveNotEmptyId(){
        new Identifier("");
    }

    @Test(expected = NullPointerException.class)
    public void ensureMustHaveNotNullId(){
        new Identifier(null);
    }

    @Test
    public void ensureValidIdentifier(){
        assertNotNull(id);
    }
}