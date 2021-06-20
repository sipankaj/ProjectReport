package com.oracle.report.operations.factories;

import com.oracle.report.datatypes.enums.DataOperation;
import com.oracle.report.operations.interfaces.IBaseOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
@Component
public class OperationsFactory {

    private final Map<DataOperation, IBaseOperations> operationsMap = new EnumMap<DataOperation, IBaseOperations>(DataOperation.class);

    @Autowired
    private OperationsFactory(List<IBaseOperations> opertions){

        opertions.forEach( opr ->{
            operationsMap.put(opr.getOperationType(),opr);
        });
    }

    public <T> IBaseOperations<T> getOperation(DataOperation oprType){
        IBaseOperations<T> operations = operationsMap.get(oprType);
        if(operations == null){
            throw new IllegalArgumentException();
        }
        return operations;
    }

}
