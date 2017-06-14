package com.gmail.kolesnyk.zakhar.model;


import com.gmail.kolesnyk.zakhar.config.Environment;

public abstract class AbstractService {
    protected final String ROOT_CONTEXT;
    protected final Integer SIZE_HISTORY;

    public AbstractService() {
        ROOT_CONTEXT = Environment.getProperty("rootContext");
        SIZE_HISTORY = Integer.parseInt(Environment.getProperty("sizeHistory"));
    }
}
