package eapli.base.common.domain.model;

import com.github.curiousoddman.rgxgen.RgxGen;
import eapli.base.common.domain.model.Identifier;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.Random;

/**
 * Data Structure responsible for automatic generation of unique "Identifier" Value Object;
 *
 * @author Gonçalo Monteiro
 */
public class IdentifierGenerator {
    private static final int IDENTIFIER_LENGTH = 23;

    public IdentifierGenerator() {}

    public Identifier generateIdentifier() {
        Identifier generatedIdentifier;

        RgxGen identifierGenerator = new RgxGen("([A-Z0-9]{5,10}-[0-9]{5,15}|[0-9]{23})");

        return new Identifier(identifierGenerator.generate());
    }
}
