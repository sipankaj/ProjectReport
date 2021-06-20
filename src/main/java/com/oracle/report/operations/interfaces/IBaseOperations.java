package com.oracle.report.operations.interfaces;

import com.oracle.report.datatypes.enums.DataOperation;

public interface IBaseOperations<T> {
    DataOperation getOperationType();
    void execute(T object);
}
