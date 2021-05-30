package com.oracle.report.errorhandling.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SystemException extends BaseException {
    private String errorCode;
    private String message;
    private Exception originalException;
}
