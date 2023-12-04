package arguments.providers;

import arguments.holders.ValidationHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class BoardValidationProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(new ValidationHolder(Map.of("id", "invalid"), "invalid id", 400),
                new ValidationHolder(Map.of("id", "655fa3fd40fc94ec6f1bb7b3"), "The requested resource was not found.", 404)
        ).map(Arguments::of);
    }
}
