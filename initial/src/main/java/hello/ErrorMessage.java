package hello;

import java.util.List;

public class ErrorMessage {
    private String message;
    private List<String> stackTrace;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<String> stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                '}';
    }
}
