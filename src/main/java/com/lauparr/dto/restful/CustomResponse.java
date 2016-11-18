package com.lauparr.dto.restful;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by lauparr on 17/11/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {

    private boolean success;

    private Object data;

    private Throwable error;

    public CustomResponse ok() {
        return this.ok(null);
    }

    public CustomResponse ok(Object data) {
        success = true;
        this.data = data;
        return this;
    }

    public CustomResponse error(Throwable e) {
        success = false;
        data = null;
        error = e;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }
}
