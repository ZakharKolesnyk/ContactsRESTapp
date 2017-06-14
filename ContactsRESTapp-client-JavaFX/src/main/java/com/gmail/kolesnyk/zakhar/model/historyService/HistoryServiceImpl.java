package com.gmail.kolesnyk.zakhar.model.historyService;


import com.gmail.kolesnyk.zakhar.model.AbstractService;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public class HistoryServiceImpl extends AbstractService implements HistoryService {

    private ChoiceBox<FieldsContainer> choiceBox;

    public HistoryServiceImpl(ChoiceBox<FieldsContainer> choiceBox) {
        this.choiceBox = choiceBox;
    }

    public void addToHistory(String idText, String nameText, String jsonText) {
        ObservableList<FieldsContainer> list = choiceBox.getItems();
        FieldsContainer container = new FieldsContainer(idText, nameText, jsonText);
        if (list.size() == 0) {
            list.add(container);
        }
        if (!container.equals(list.get(0))) {
            list.add(0, container);
            if (list.size() > SIZE_HISTORY) {
                list.remove(list.size() - 1);
            }
        }
    }
}
