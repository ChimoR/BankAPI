package model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Exception {

    @JsonProperty("Exception")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception() {
    }
}
