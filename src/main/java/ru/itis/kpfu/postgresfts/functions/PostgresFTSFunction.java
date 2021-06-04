package ru.itis.kpfu.postgresfts.functions;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;

import java.util.List;

public class PostgresFTSFunction implements SQLFunction {

    @Override
    public String render(Type type, List args, SessionFactoryImplementor sessionFactoryImplementor)
            throws QueryException {
        if (args.size() != 2) {
            throw new IllegalArgumentException("The function must be passed 3 args");
        }
        String field = (String) args.get(0);
        String value = (String) args.get(1);

        String fragment = null;
        fragment =  "( to_tsvector(coalesce(" + field + ",' ')) @@ " + "plainto_tsquery('%" +
                value + "%')) ";

        return fragment;
    }

    @Override
    public Type getReturnType(Type columnType, Mapping mapping) throws QueryException {
        return new BooleanType();
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public boolean hasParenthesesIfNoArguments() {
        return false;
    }
}
