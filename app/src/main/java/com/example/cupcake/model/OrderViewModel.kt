package com.example.cupcake.model



import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale



/**
 * [OrderViewModel] holds information about a cupcake order in terms of quantity, flavor, and
 * pickup date. It also knows how to calculate the total price based on these order details.
 */
private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY=3.00
private const val pricevanilla=2.00
private const val pricechocolate=4.00
private const val priceredvelvet=4.00
private const val pricecaramel=3.00
private const val pricecoffe=2.00


class OrderViewModel : ViewModel() {




    // Quantity of cupcakes in this order
    val dateOptions = getPickupOption()


    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    // Cupcake flavor for this order
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor



    // Pickup date
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    // Price of the order so far
    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price



    /**
     * Set the quantity of cupcakes for this order.
     *
     * @param numberCupcakes to order
     */
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()

    }

    /**
     * Set the flavor of cupcakes for this order. Only 1 flavor can be selected for the whole order.
     *
     * @param desiredFlavor is the cupcake flavor as a string
     */
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    /**
     * Set the pickup date for this order.
     *
     * @param pickupDate is the date for pickup as a string
     */
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()

    }

    /**
     * Returns true if a flavor has not been selected for the order yet. Returns false otherwise.
     */
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }
    fun getPickupOption(): List<String>{
        val options= mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d",Locale.getDefault())
        val calendar=Calendar.getInstance()
        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)

        }
        return options
    }
    fun resetOrder(){
        _quantity.value=0
        _flavor.value=""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }
    init {
        resetOrder()
    }
    private fun updatePrice(){
        var calculatedprice=(quantity.value ?:0)* PRICE_PER_CUPCAKE
        if(dateOptions[0]==_date.value){
             calculatedprice+= PRICE_FOR_SAME_DAY
        }
        //if(== _flavor.value)
        _price.value=calculatedprice
    }

    /**
     * Reset the order by using initial default values for the quantity, flavor, date, and price.
     */


    /**
     * Updates the price based on the order details.
     */


    /**
     * Returns a list of date options starting with the current date and the following 3 dates.
     */

}