package arguments.providers;

import arguments.holders.ValidationHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class CardValidationProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(new ValidationHolder(Map.of("card_id", "invalid"), "invalid id", 400),
                new ValidationHolder(Map.of("card_id", "6560f094e2b0c1cfc50eecc1"), "The requested resource was not found.", 404)
        ).map(Arguments::of);
    }
}
