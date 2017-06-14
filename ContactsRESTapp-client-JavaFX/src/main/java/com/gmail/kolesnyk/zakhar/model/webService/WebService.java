package com.gmail.kolesnyk.zakhar.model.webService;


import java.io.IOException;

public interface WebService {
    String sendRequest(String request, String method, String json) throws IOException;
}
