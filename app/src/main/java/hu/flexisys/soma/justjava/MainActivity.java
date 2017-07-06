package hu.flexisys.soma.justjava;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public static final int COFFEE_PRICE = 2;
    public static final int WHIPPED_CREAM_PRICE = 1;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price;
        CheckBox whipped_cream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean isWhippedCream = whipped_cream.isChecked();
        int oneCoffeePrice = COFFEE_PRICE;
        if (isWhippedCream) oneCoffeePrice+=WHIPPED_CREAM_PRICE;
        price = calculatePrice(quantity,oneCoffeePrice);
        TextView nameText = (TextView) findViewById(R.id.name_text);
        String name = nameText.getText().toString();
        String message = createOrderSummary(price, name , quantity);
        displayMessage(message);
        coffeeToast();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(number));
    }



    public void decrease(View view) {
        if (quantity > 0) {
            quantity--;
        }
        displayQuantity(quantity);
    }

    public void increase(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

    }

    private int calculatePrice (int price, int quantity){


        return price*quantity;
    }

    private String createOrderSummary (int price, String name, int quantity){
        return  "Name: " + name + "\n" + "Quantity: " + quantity + "\n" + "Total: " + NumberFormat.getCurrencyInstance().format(price) + "\n" + "Thanks!";
    }

    private void coffeeToast (){
        Context context = getApplicationContext();
            CharSequence text;
            if(quantity>2) { text = "You drink too muck coffee!"; }
            else if (quantity == 0) { text = "You should order something";}
            else {text = "Enjoy your coffee!";}
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

    }
}