package com.pilot.controller.model.request;

import io.swagger.annotations.ApiModel;

import java.util.Objects;

/**
 * Response for validation requests.
 */
@ApiModel(value = "Response", description = "Response Transfer Object")
public class Response {

    private String type;
    private String message;

    /**
     * Default constructor
     */
    public Response() {
        // Default for initialization
    }

    /**
     * Response for validation request constructor
     *
     * @param type    response type
     * @param message response message
     */
    public Response(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(type, response.type) &&
                Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, message);
    }
}
