package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private ToggleGroup Size;

    @FXML
    private Button saveOrder;

    @FXML
    private Button checkPrice;

    @FXML
    private CheckBox GreenPepper;

    @FXML
    private ToggleGroup Cheese;

    @FXML
    private TextArea orderSummary;

    @FXML
    private ChoiceBox<Integer> numPizzas;

    @FXML
    private CheckBox Ham;

    @FXML
    private CheckBox Pineapple;

    @FXML
    private Label pizzaCost;

    // toppings
    String hamTopping;
    String greenPepperTopping;
    String pineappleTopping;

    // size, cheese, quantity
    String sizeChoice;
    String cheeseChoice;
    int quantityChoice;

    @FXML
    // ensures that Ham is selected before pineapple or green pepper
    private void exceptionHam() {
        Ham.setAllowIndeterminate(false);
        if (Ham.isSelected()) {
            hamTopping = "Single";
        } else {
            Pineapple.setSelected(false);
            GreenPepper.setSelected(false);
        }
    }
    @FXML
    private void exceptionPineapple() {
        if (Pineapple.isSelected()) {
            //Check if the user has selected ham, if not do not let them select pineapple
            if (Ham.isSelected()) {
                pineappleTopping = "Single";
            } else if(!(Ham.isSelected())) {
                Pineapple.setSelected(false);
            }
        }
    }
    @FXML
    private void exceptionGreenPepper() {
        if (GreenPepper.isSelected()) {
            //Check if the user has selected ham, if not do not let them select green pepper
            if (Ham.isSelected()) {
                greenPepperTopping = "Single";
            } else if(!(Ham.isSelected())) {
                GreenPepper.setSelected(false);
            }
        }
    }

    private ObservableList<Integer> quantityOption = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
            30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
            40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
            50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
            60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
            70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
            80, 81, 82, 83, 84, 85, 86, 87, 88, 89,
            90, 91, 92, 93, 94, 95, 96, 97, 98, 99,
            100);

    @FXML
    private void initialize() {
        numPizzas.setItems(quantityOption);
        numPizzas.setValue(1);
    }

    //This method is used for both buttons. It creates a pizza object based on what was entered by user
    private LineItem savePizza() throws IllegalPizza{
        sizeChoice = ((RadioButton)Size.getSelectedToggle()).getText();
        cheeseChoice = ((RadioButton)Cheese.getSelectedToggle()).getText();
        quantityChoice = numPizzas.getValue();

        if (hamTopping == null)
            hamTopping = "None";
        if ( pineappleTopping == null)
            pineappleTopping = "None";
        if (greenPepperTopping == null)
            greenPepperTopping = "None";

        Pizza pizzaOrder = new Pizza(sizeChoice, cheeseChoice, pineappleTopping, greenPepperTopping, hamTopping);
        LineItem lineItemOrder = new LineItem(quantityChoice, pizzaOrder);

        return lineItemOrder;
    }

    @FXML
    private void pressCheckPrice(ActionEvent checkPrice) throws IllegalPizza {
        LineItem lineItemObject = savePizza();
        String costValue = String.valueOf(lineItemObject.getCost());
        pizzaCost.setText("$" + costValue + "0");
    }

    @FXML
    private void orderSavedClicked() throws IllegalPizza {
        LineItem lineItemObject =  savePizza();
        // this will run after only after the first order has been saved.
        // the line consisting of the total order cost is removed and updated
        try {
            // delete the last line from the TextField
            String orderSummaryString = orderSummary.getText();
            int indexStart = orderSummary.getText().indexOf("T");
            int indexEnd = orderSummary.getText().lastIndexOf("0");
            orderSummary.deleteText(indexStart, indexEnd + 1);

            // get the value of the cost of the previous order, add it to current cost for total cost
            String previousCostString = orderSummaryString.substring(orderSummaryString.lastIndexOf("$") + 1, indexEnd);
            double previousCostDouble = Double.valueOf(previousCostString);
            double orderCost = lineItemObject.getCost();
            double totalCost = orderCost + previousCostDouble;

            // display to user
            String lineItemString = lineItemObject.toString();
            orderSummary.appendText(lineItemString + "\n");
            orderSummary.appendText("Total Cost: $" + totalCost + "0\n");

            // first run through, since there will be an IndexOutOfBoundsException when trying to delete previous total cost
        } catch (Exception IndexOutOfBoundsException) {
            String lineItemString = lineItemObject.toString();
            String orderCost = String.valueOf(lineItemObject.getCost());
            orderSummary.appendText(lineItemString + "\n");
            orderSummary.appendText("Total Cost: $" + orderCost + "0\n");
        }
    }

}