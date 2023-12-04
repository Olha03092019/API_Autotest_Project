package arguments.holders;

import java.util.Map;

public class ValidationHolder {
    private final Map<String, String> pathParam;
    private final String errorMessage;
    private final int code;

    public ValidationHolder(Map<String, String> pathParam, String errorMessage, int code) {
        this.pathParam = pathParam;
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public Map<String, String> getPathParam() {
        return pathParam;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getCode() {
        return code;
    }
}
