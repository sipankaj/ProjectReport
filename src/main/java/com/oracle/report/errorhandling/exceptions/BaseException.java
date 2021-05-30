package com.oracle.report.errorhandling.exceptions;

public abstract class BaseException extends Exception {
    public abstract Exception getOriginalException();

    public abstract String getMessage();

    public abstract String getErrorCode();
}
