package com.Calculator.andriodmoise

import java.lang.Exception

/**
 * @property num1 The first number of the operation.
 * @property num2 The second number of the operation.
 */

class Logic {

    private var num1 = 0.0
    private var num2 = 0.0

    /**
     * @param number String that contains the 2 numbers to be extracted.
     * @param sign The sign that separates the 2 numbers.
     */
    fun updatenumb( number : String,sign : String){
        //Finds the index of the sign in the string
        val index = number.indexOf(sign)
        //Using the index as a reference point, extracts the 2 numbers
        this.num1 = toDouble(number.substring(0,index))
        this.num2 = toDouble(number.substring(index+1))

    }

    /**
     * @param sign The sign that has been pressed
     * @return Returns the result of the operation between the 2 numbers
     */
    fun calculate( sign:String): Double? {
        //Uses the variable sign to check what operation to do.
        val total: Double = when(sign){
            "+"-> this.num1 + this.num2
            "-"-> this.num1 - this.num2
            "×"-> this.num1 * this.num2
            "÷"-> this.num1 / this.num2
            else -> return null
        }
        //Uses the function to round the result to 2 decimal points.
        return round(total)
    }

    /**
     * @param value The value to be rounded up.
     * @exception  Exception If the number has less then 2 decimal places.
     * @return Returns the updated value.
     */
    private fun round(value:Double): Double {
        //converts the value to a string
        val num = value.toString()
        return try {
            //Looks for the index of the decimal place
            val pos = num.indexOf(".")
            //Uses the index to round the number to 2 decimal places
            num.substring(0,pos+3).toDouble()
        }catch (e:Exception){
            //Returns value as is if an error occurs
            value
        }
    }

    /**
     * @param number Number to be converted to double.
     * @throws Exception If no number is introduced.
     * @return Returns the number as a double.
     */
    private fun toDouble(number:String): Double {
        return try {
            val numb = number.toDouble()
            numb
        }catch (e:Exception){
            0.0
        }
    }

//Clears the 2 numbers
    fun clear(){
         num1 = 0.0
         num2 = 0.0
     }


}