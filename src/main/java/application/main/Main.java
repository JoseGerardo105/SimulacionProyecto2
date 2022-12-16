package application.main;

import javafx.application.Application;
import javafx.stage.Stage;
import application.model.PathFinder;
import application.presenter.Presenter;
import application.view.View;

/**
 *
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public Main() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        PathFinder model = new PathFinder();

        View view = new View(stage);

        Presenter presenter = new Presenter(model, view);

        view.showStage();
    }
}
