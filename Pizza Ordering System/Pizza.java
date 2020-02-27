import javax.security.sasl.SaslServer;
import java.io.Serializable;

/**
 * A class to store information for a pizza
 *<p>
 * The size, type of cheese, and toppings: pineapple, green pepper and ham are all recorded.
 *</p>
 * @author Sarah Labrosse
 * @version 1.0
 */

public class Pizza implements Serializable {

    private static final long serialVersionUID = 2866326578932306799L;


    private String size;
    private String cheese;
    private String pineapple;
    private String greenPepper;
    private String ham;

    /**
     * Full parameter constructor.
     * @param pSize The size of the pizza.
     * @param pCheese The amount of cheese on the pizza.
     * @param pPineapple If there is or is not pineapple.
     * @param pGreenPepper If there is or is not green pepper.
     * @param pHam If there is or is not ham.
     * @throws IllegalPizza if arguments are not legal.
     */

    // 5 parameter constructor invokes mutators
    public Pizza(String pSize, String pCheese, String pPineapple, String pGreenPepper, String pHam)
            throws IllegalPizza {
        if (pSize == null || pCheese == null || pPineapple == null || pGreenPepper == null || pHam == null)
            throw new IllegalPizza("Null entered");
        else {
            setSize(pSize);
            setCheese(pCheese);
            setToppings(pPineapple, pGreenPepper, pHam);
        }
    } // end Pizza 5 parameter constructor

    /**
     * Zero parameter constructor.
     * @throws IllegalPizza if argument is not legal
     */
    // default constructor for a small pizza with single cheese and ham
   public Pizza() throws IllegalPizza{
        this("small", "single", "none", "none", "single");
   }

    /**
     * Sets the size of the pizza.
     * @param pSize The size of the pizza.
     * @throws IllegalPizza if argument is not 'small', 'medium' or 'large'.
     */
    // mutator for size
    public void setSize(String pSize) throws IllegalPizza {
        // this checks all the acceptable cases for size
        if ((pSize.equalsIgnoreCase("small")) ||
                (pSize.equalsIgnoreCase("medium")) ||
                (pSize.equalsIgnoreCase("large"))) {
            this.size = pSize;
        } else
            throw new IllegalPizza("Illegal size choice");
    } // end size mutator

    /**
     * This sets the amount of cheese on the pizza.
     * @param pCheese The amount of cheese on the pizza.
     * @throws IllegalPizza if argument is not 'single', 'double', or 'triple'.
     */
    // mutator for cheese
    public void setCheese(String pCheese) throws IllegalPizza {
        // this checks all the acceptable cases for cheese
        if ((pCheese.equalsIgnoreCase("single")) ||
                (pCheese.equalsIgnoreCase("double")) ||
                (pCheese.equalsIgnoreCase("triple"))) {
            this.cheese = pCheese;
        } else
            throw new IllegalPizza("Illegal cheese choice");
    } // end cheese mutator

    // mutator for pineapple, green pepper and ham

    /**
     * This sets the toppings for the pizza.
     * @param pPineapple If there is or is not pineapples.
     * @param pGreenPepper If there is or is not green pepper.
     * @param pHam If there is or is not ham.
     * @throws IllegalPizza If illegal topping choice.
     */
    public void setToppings(String pPineapple, String pGreenPepper, String pHam) throws IllegalPizza {
        // boolean variables defining whether the topping is single or none
        boolean greenPepperSingle = pGreenPepper.equalsIgnoreCase("single");
        boolean pineappleSingle = pPineapple.equalsIgnoreCase("single");
        boolean hamSingle = pHam.equalsIgnoreCase("single");
        boolean greenPepperNone = pGreenPepper.equalsIgnoreCase("none");
        boolean pineappleNone = pPineapple.equalsIgnoreCase("none");
        boolean hamNone = pHam.equalsIgnoreCase("none");

        // this checks all the possible conditions for a legal pizza
        // must be ham if there is pineapple or green pepper
        if (hamSingle && greenPepperSingle && pineappleSingle || hamSingle && greenPepperSingle && pineappleNone ||
        hamSingle && greenPepperNone && pineappleSingle || hamSingle & greenPepperNone && pineappleNone ||
        hamNone && greenPepperNone && pineappleNone) {
            this.pineapple = pPineapple;
            this.greenPepper = pGreenPepper;
            this.ham = pHam;
        }
        else
            throw new IllegalPizza("Illegal topping choice");
    } // end mutator for pineapple, green pepper and ham

    /**
     * Returns The total cost of the pizza.
     * @return Total cost of the pizza in dollars as a float.
     */
    // accessor to return cost of pizza
    public float getCost() {
        float cost = 0.00f;
        // checks pizza and adds costs if necessary
        if (size.equalsIgnoreCase("small"))
            cost += 7.00;
        if (size.equalsIgnoreCase("medium"))
            cost += 9.00;
        if (size.equalsIgnoreCase("large"))
            cost += 11.00;
        if (cheese.equalsIgnoreCase("double"))
            cost += 1.50;
        if (cheese.equalsIgnoreCase("triple"))
            cost += 3.00;
        if (pineapple.equalsIgnoreCase("single"))
            cost += 1.50;
        if (greenPepper.equalsIgnoreCase("single"))
            cost += 1.50;
        if (ham.equalsIgnoreCase("single"))
            cost += 1.50;

        return cost;
    } // end getCost

    /**
     * A String representation of the current object.
     * @return A string representation of the contents of the object containing the values
     * of all the attributes.
     */
    // Overrides (replaces) the toString method of the Object class.
    @Override
    // toString method
    public String toString(){
        String s = size.toLowerCase() + " pizza, " + cheese.toLowerCase() + " cheese";
        if (pineapple.equalsIgnoreCase("single"))
            s += ", pineapple";
        if (greenPepper.equalsIgnoreCase("single"))
            s += ", green pepper";
        if (ham.equalsIgnoreCase("single"))
            s += ", ham";
        s += ". Cost: $" + String.format("%.2f", getCost()) + " each.";

        return s;
    } // end toString method

    /**
     * Tests two Pizza objects for equality.
     * @param otherObject The other Pizza Object.
     * @return true if all the attributes of both objects are exactly equal, false if otherwise
     */
    // Overrides the equals method of the Object class
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Pizza) {
            Pizza otherP = (Pizza)otherObject;
            return size.equalsIgnoreCase(otherP.size) && cheese.equalsIgnoreCase(otherP.cheese) &&
                    pineapple.equalsIgnoreCase(otherP.pineapple) && greenPepper.equalsIgnoreCase(otherP.greenPepper) &&
                    ham.equalsIgnoreCase(otherP.ham);
        }
        return false;
    } // end assertEquals

    /**
     * Returns a copy of the current Pizza object.
     * @return A copy of the current object.
     */
    // Overrides the clone method in the Object class.
    @Override
    public Pizza clone() {
        Pizza pizzaCopy = null;
        try{
            pizzaCopy = new Pizza(size, cheese, pineapple, greenPepper, ham);
        } catch (IllegalPizza e) {
            return null;
        } // end try/catch
        return pizzaCopy;
    } // end clone

} // end Pizza

