package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    public TextArea showid;
    private List<String> data = new ArrayList<>();
    public void chose(ActionEvent actionEvent) {
        fileAdapter fileAdapter = new fileAdapter();
        data = fileAdapter.fileRead();
    }

    public void search(ActionEvent actionEvent) {
        countAdapter adapter = new countAdapter();
        adapter.initData(Integer.parseInt(data.get(0)),data);
        adapter.run();
        adapter.getTree();
    }
}
