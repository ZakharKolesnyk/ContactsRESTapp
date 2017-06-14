package com.gmail.kolesnyk.zakhar.model.contactsService;

import com.gmail.kolesnyk.zakhar.model.AbstractService;
import com.gmail.kolesnyk.zakhar.model.webService.WebService;
import com.gmail.kolesnyk.zakhar.model.webService.WebServiceImpl;

import java.io.IOException;

public class ContactsServiceImpl extends AbstractService implements ContactsService {

    private WebService webService;

    public ContactsServiceImpl() {
        webService = new WebServiceImpl();
    }

    @Override
    public String getContactByID(String id) throws IOException {
        return webService.sendRequest(ROOT_CONTEXT + "id/" + id, "GET", null);
    }

    @Override
    public String getContactByName(String name) throws IOException {
        return webService.sendRequest(ROOT_CONTEXT + "name/" + name, "GET", null);
    }

    @Override
    public String addContact(String json) throws IOException {
        return webService.sendRequest(ROOT_CONTEXT + "add", "POST", json);
    }

    @Override
    public String updateContact(String id, String json) throws IOException {
        return webService.sendRequest(ROOT_CONTEXT + "update/" + id, "PUT", json);
    }

    @Override
    public void deleteContactByID(String id) throws IOException {
        webService.sendRequest(ROOT_CONTEXT + "del/" + id, "PUT", null);
    }

    @Override
    public void deleteContactByJson(String json) throws IOException {
        webService.sendRequest(ROOT_CONTEXT + "del", "PUT", json);
    }
}
