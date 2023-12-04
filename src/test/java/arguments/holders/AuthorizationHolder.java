package arguments.holders;

import java.util.Map;

public class AuthorizationHolder {
    private final Map<String,String> authParam;
    private final String messageError;
    public AuthorizationHolder(Map<String, String> authParam, String messageError) {
        this.authParam = authParam;
        this.messageError = messageError;
    }

    public Map<String, String> getAuthParam() {
        return authParam;
    }

    public String getMessageError() {
        return messageError;
    }
}
