//package com.gmail.kolesnyk.zakhar;
//
//import com.gmail.kolesnyk.zakhar.config.Environment;
//import com.gmail.kolesnyk.zakhar.model.contactsService.ContactsServiceImpl;
//import com.gmail.kolesnyk.zakhar.model.historyService.FieldsContainer;
//import com.gmail.kolesnyk.zakhar.model.webService.WebService;
//import com.gmail.kolesnyk.zakhar.model.webService.WebServiceImpl;
//import javafx.application.Platform;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.stage.Stage;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.io.IOException;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MainThreadTest {
//
//    @InjectMocks
//    private static MainThread mainThread;
//    @Mock
//    private static WebService webService=new WebServiceImpl();
//
//    private static Object sync;
//    private static Stage stage;
//    private static Scene scene;
//
//    private static TextArea jsonArea;
//    private static TextField idTextField;
//    private static TextField nameTextField;
//    private static Label warningLabel;
//    private static ChoiceBox<FieldsContainer> historyChoiceBox;
//    private static Button addButton;
//    private static Button getByNameButton;
//    private static Button getByIdButton;
//    private static Button updateButton;
//    private static Button deleteByJsonButton;
//    private static Button deleteByIDButton;
//
//    @BeforeClass
//    public static void initJFX() throws InterruptedException, IOException {
//        Mockito.when(webService.sendRequest(Environment.getProperty("rootContext") + "id/" + "1", "GET", null)).thenReturn("{\"id\":100,\"userName\":\"Mitchel\",\"phone\":\"814968214124123\"}");
//        sync = new Object();
//        new JFXPanel();
//        synchronized (sync) {
//            Platform.runLater(() -> {
//                synchronized (sync) {
//                    stage = new Stage();
//                    try {
//                        mainThread = new MainThread();
//                        mainThread.start(stage);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    scene = stage.getScene();
//                    jsonArea = (TextArea) scene.lookup("#jsonArea");
//                    idTextField = (TextField) scene.lookup("#idTextField");
//                    nameTextField = (TextField) scene.lookup("#nameTextField");
//                    warningLabel = (Label) scene.lookup("#warningLabel");
//                    historyChoiceBox = (ChoiceBox<FieldsContainer>) scene.lookup("#historyChoiceBox");
//                    addButton = (Button) scene.lookup("#addButton");
//                    getByNameButton = (Button) scene.lookup("#getByNameButton");
//                    getByIdButton = (Button) scene.lookup("#getByIdButton");
//                    updateButton = (Button) scene.lookup("#updateButton");
//                    deleteByJsonButton = (Button) scene.lookup("#deleteByJsonButton");
//                    deleteByIDButton = (Button) scene.lookup("#deleteByIDButton");
//                    sync.notify();
//                }
//            });
//            sync.wait();
//        }
//    }
//
//    @Test
//    public void test() throws InterruptedException {
//        idTextField.setText("1");
//        Thread.sleep(1000);
//        nameTextField.setText("Fill");
//        Thread.sleep(1000);
//        while (true) {
//            getByIdButton.fire();
//            getByNameButton.fire();
//        }
//    }
//}