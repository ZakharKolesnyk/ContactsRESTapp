package com.gmail.kolesnyk.zakhar.model.historyService;


public class FieldsContainer {
    private String idText = "";
    private String nameText = "";
    private String jsonText = "";

    public FieldsContainer(String idText, String nameText, String jsonText) {
        if (idText != null) {
            this.idText = idText;
        }
        if (nameText != null) {
            this.nameText = nameText;
        }
        if (jsonText != null) {
            this.jsonText = jsonText;
        }
    }

    public String getIdText() {
        return idText;
    }

    public String getNameText() {
        return nameText;
    }

    public String getJsonText() {
        return jsonText;
    }

    @Override
    public String toString() {
        return "id:'" + idText + '\'' +
                ", name:'" + nameText + '\'' +
                ", json:'" + jsonText + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldsContainer that = (FieldsContainer) o;

        if (!idText.equals(that.idText)) return false;
        if (!nameText.equals(that.nameText)) return false;
        return jsonText.equals(that.jsonText);
    }

    @Override
    public int hashCode() {
        int result = idText.hashCode();
        result = 31 * result + nameText.hashCode();
        result = 31 * result + jsonText.hashCode();
        return result;
    }
}
