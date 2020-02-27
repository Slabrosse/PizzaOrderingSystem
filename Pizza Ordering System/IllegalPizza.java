/**
 * Exception object thrown by Pizza and LineItem Object if parameters are not legal
 * <ul>
 * <li>All attributes of pizza must be proper keywords.</li>
 * <li>A pizza with pineapple or greenpepper must have ham.</li>
 * <li>Number of pizzas must be between 1-100 inclusive.</li>
 * <li>No null attributes.</li>
 * </ul>
 * @author Sarah Labrosse
 * @version 1.0
 */

public class IllegalPizza extends Exception {

    public IllegalPizza() { super("Illegal parameter value supplied to object"); }

    public IllegalPizza(String message) { super(message);}

} // end Illegal Pizza
