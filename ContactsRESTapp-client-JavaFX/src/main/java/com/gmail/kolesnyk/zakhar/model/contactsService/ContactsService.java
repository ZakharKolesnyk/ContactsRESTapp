package com.gmail.kolesnyk.zakhar.model.contactsService;

import java.io.IOException;

public interface ContactsService {

    String addContact(String json) throws IOException;

    String getContactByID(String id) throws IOException;

    String getContactByName(String name) throws IOException;

    String updateContact(String id, String json) throws IOException;

    void deleteContactByID(String id) throws IOException;

    void deleteContactByJson(String json) throws IOException;
}
