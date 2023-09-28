package com.in28minutes.learnspringframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;

interface DataService{
    int[] retrieveData();
}
@Primary
class MongoDbDataService implements  DataService{

    @Override
    public int[] retrieveData() {
        return new int[] {11, 22, 33, 44, 55};
    }
}
class MySQLDataService implements DataService{
    @Override
    public int[] retrieveData() {
        return new int[] {1, 2, 3, 4, 5};
    }
}

public class BusinessCalcuationService {
    private DataService dataService;
    @Autowired
    public BusinessCalcuationService(@Qualifier("MongoDbDataService") DataService dataService) {
        this.dataService = dataService;
    }
    public int findMax(){
        return Arrays.stream(dataService.retrieveData())
                .max().orElse(0);
    }
}
