import java.io.Serializable;

/**
 * A class that describes a single line item from a pizza order
 * <p>
 * A new pizza object and number of pizzas are recorded.
 * </p>
 *
 * @author Sarah Labrosse
 * @version 1.0
 */

public class LineItem implements Comparable<LineItem>, Serializable {

    private static final long serialVersionUID = 2939742718243042167L;

    private Pizza pizzaPointer;
    private int numPizzas;

    /**
     * Two parameter constructor
     * @param pizzas Number of pizzas.
     * @param newPizza Pizza object.
     * @throws IllegalPizza if argument is illegal.
     */
    //two parameter constructor invokes mutator
    public LineItem(int pizzas, Pizza newPizza) throws IllegalPizza{
        if (newPizza != null) {
            setNumber(pizzas);
            this.pizzaPointer = newPizza;
        }
        else
            throw new IllegalPizza("You have entered an illegal number of pizzas");
    } // end of constructor

    /**
     * One parameter constructor
     * @param pizzaPointer A Pizza object.
     * @throws IllegalPizza if illegal input.
     */
    // one parameter default constructor
    public LineItem(Pizza pizzaPointer) throws IllegalPizza{
        this( 1, pizzaPointer);
    } // end of constructor

    /**
     * Sets the number of Pizzas
     * @param pizzas Pizza object.
     * @throws IllegalPizza if Pizzas are less than 1 or greater than 100.
     */
    // mutator for number of Pizzas
    public void setNumber(int pizzas) throws IllegalPizza{
        if (pizzas >= 1 && pizzas <= 100)
            this.numPizzas = pizzas;
        else
            throw new IllegalPizza();
    } // end mutator

    /**
     * Returns the Pizza object.
     * @return pizzaPointer.
     */
    public Pizza getPizza() {
        return pizzaPointer;
    } // end getPizzaPointer

    /**
     * Returns the number of pizzas.
     * @return numPizzas.
     */
    public int getNumber() {
        return numPizzas;
    } // end getNumPizzas

    /**
     * Returns cost of all pizzas as a float, discount given if certain conditions are met.
     * @return totalCost, cost of all pizzas in line item.
     */
    public float getCost(){
        float cost = pizzaPointer.getCost();
        // discount if 10 or more and less than or 20 pizzas
        if (numPizzas >= 10 && numPizzas <= 20)
            cost *= 0.9; // gives a 10% discount
        // discount if more than 20 pizzas
        else if (numPizzas > 20)
            cost *= 0.85; // gives a 15% discount
        float totalCost = numPizzas * cost;
        return totalCost;
    } // end getCost

    /**
     * A String representation of the current object.
     * @return A string representation of the contents of the object containing the values
     * of all the attributes.
     */
    @Override
    public String toString(){

        if (numPizzas < 10)
            return (" " + numPizzas + " " + pizzaPointer);
        else
            return (numPizzas + " " + pizzaPointer);
    } // end toString method

    /**
     * Compares LineItem objects based only on total cost of the line item.
     * @param otherCost The other cost (LineItem object).
     * @return A integer based on difference between the costs, if the zero is the cost difference is less than one dollar.
     */
    public int compareTo(LineItem otherCost){
        System.out.println((int)(otherCost.getCost() - getCost()));

        return (int)(otherCost.getCost() - getCost());
    } // end of compareTo method

} // end of LineItem
