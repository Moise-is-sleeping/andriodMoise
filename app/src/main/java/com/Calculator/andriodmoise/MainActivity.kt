package com.Calculator.andriodmoise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

/**
 * @property display Contains whats displayed on the screen through the XML.
 * @property displayedNumbers Contains everything that's seen on screen.
 * @property buttonIdList A list of all the button ids.
 * @property buttonList A list of the buttons after they have been initialized.
 * @property sign Variable that saves the sign that has been pressed.
 * @property logic Initialization of the Class logic.
 *
 */
class MainActivity : AppCompatActivity() {
    private lateinit var display : TextView
    private var displayedNumbers = ""
    private var buttonIdList = listOf(R.id.one,R.id.two,R.id.three,R.id.four,R.id.five,R.id.six,R.id.seven,R.id.eight,R.id.nine,R.id.subs,R.id.add,R.id.clr,R.id.zero,R.id.div,R.id.multi,R.id.ans,R.id.decimal,R.id.del)
    private var buttonList = mutableListOf<Button>()
    private var sign = ""
    private var logic = Logic()
    private var checkdecimal = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Loop that uses the button id to initialize the buttons and saves them in a list
        for (buttonId in buttonIdList){
            val button = findViewById<Button>(buttonId)
            buttonList.add(button)
            //Uses the function to check what to do when a button has been pressed.
            button.setOnClickListener { buttonClick(button) }
        }
        //Uses the TextView id to display whats on screen.
        display = findViewById(R.id.screen)
    }

    /**
     * @param button Button to check
     * @throws Exception If no number has been pressed
     */
    private fun buttonClick(button: Button){
        //Uses the text in the button to identify what button has been pressed.
        when(button.text){
            "1","2","3","4","5","6","7","8","9","0"->{
                displayedNumbers += button.text
            }
            "-","+","×","÷"->{
                //If no number has been pressed before an operation, en error messages is displayed using Toast.
                if (displayedNumbers.isNotEmpty()){
                    //If the function returns true the operation is done
                    if (operateOrCalculate(displayedNumbers)){
                        calculate()
                    }
                    //Updates the sign to whatever operation has been pressed.
                    sign = button.text.toString()
                    //Uses the function to check whether a sign is present in order to replace.
                    displayedNumbers = changeSign(displayedNumbers,sign)
                    checkdecimal = true

                }else{
                    Toast.makeText(applicationContext,"Number not present !!",Toast.LENGTH_SHORT).show()
                }


            }
            "C" ->{
                //Clears the variables in the logic class as well as everything being displayed.
                displayedNumbers = ""
                logic.clear()
                checkdecimal = true
            }
            "=" ->{
                //If no button has been pressed, an error message is Displayed using Toast.
                if (displayedNumbers.isNotEmpty()){
                    calculate()
                    if (displayedNumbers.contains(".")){
                        checkdecimal = false
                    }
                }else{
                    Toast.makeText(applicationContext,"Number not present !!",Toast.LENGTH_SHORT).show()
                }
            }
            //Deletes the last character that's being displayed.
            "Del"->{
                if(displayedNumbers[displayedNumbers.length-1].toString() == "."){
                    checkdecimal = true
                }
                displayedNumbers = displayedNumbers.dropLast(1)

            }
            "."->{
                if(checkdecimal){
                    displayedNumbers += button.text
                    checkdecimal = false
                }
            }

        }
        //Displays the variable displayedNumbers on screen.
        display.text = displayedNumbers
    }

    /**
     * @throws Exception If the 2 numbers arent present
     */
    fun calculate(){
        //Updates the numbers in the logic class
        logic.updatenumb(displayedNumbers,sign)
        //Calculates the result of the operation, then checks if the number to be displayed is a int or double
        try {
            displayedNumbers = intOrDouble( logic.calculate(sign).toString())
            //Resets the sign
            sign = ""
        }catch (e:Exception){
            Toast.makeText(applicationContext,"Number not present !!",Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * @param text String to be checked.
 * @return True if the string contains 2 numbers and an operation.
 */
fun operateOrCalculate(text: String): Boolean {
    //Contains a list of signs.
    val signs = listOf("-","+","×","÷")
    //Contains the index of the sign
    var signIndex = 0
    for (sign in signs){
        //Checks the string for a sign.
        signIndex= text.indexOf(sign)
        //If a sign is found the loop is broken.
        if (signIndex > 0){
            break
        }
    }
    return signIndex > 0 && signIndex < text.length-1

}

/**
 * @param text The string that needs to be checked.
 * @param signo The sign that has just been pressed.
 * @return Returns the updated string with the new sign.
 */
fun changeSign(text : String,signo : String): String {
    //Contains a list of signs.
    val signs = listOf("-","+","×","÷")
    //Contains the last character in the string.  
    val lastChar = text[text.lastIndex].toString()
    //If the last position of the string already has a sign, it gets replaced, otherwise it adds the sign to the string.
    return if (signs.contains(lastChar)){
        text.replace(lastChar,signo)
    }else{
        text+signo
    }


}

/**
 * @param value The value to be converted to Int or Double.
 * @return Returns the value as an Int or Double.
 */
fun intOrDouble(value : String): String {
    return if (value.substring(value.indexOf(".") + 1).toInt() == 0){
        value.toDouble().toInt().toString()
    }
    else{
        value
    }
}





