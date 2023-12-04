package arguments.providers;

import arguments.holders.AuthorizationHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static arguments.consts.UrlParamValues.VALID_KEY;
import static arguments.consts.UrlParamValues.VALID_TOKEN;

public class CardAuthorizationProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(new AuthorizationHolder(Collections.emptyMap(), "invalid key"),
                new AuthorizationHolder(Map.of("key", VALID_KEY), "unauthorized card permission requested"),
                new AuthorizationHolder(Map.of("token", VALID_TOKEN), "invalid key")
        ).map(Arguments::of);
    }
}
