package ru.itis.kpfu.postgresfts.functions;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.dialect.function.SQLFunction;

public class FTSDialect extends PostgreSQL9Dialect {

    @Override
    protected void registerFunction(String name, SQLFunction function) {
        super.registerFunction("fts", new PostgresFTSFunction());
    }
}
