package com.Calculator.andriodmoise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var display : TextView
    private var displayedNum = ""
    private var currentNumber = ""
    private var numb1 = 0.0
    private var numb2 = 0.0
    private var buttonIdList = listOf(R.id.one,R.id.two,R.id.three,R.id.four,R.id.five,R.id.six,R.id.seven,R.id.eight,R.id.nine,R.id.subs,R.id.add,R.id.clr,R.id.zero,R.id.div,R.id.multi,R.id.ans)
    private var buttonList = mutableListOf<Button>()
    private var sign = ""
    private var zeroPressed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (buttonId in buttonIdList){
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { buttonClick(button) }
            buttonList.add(button)

        }

        display = findViewById(R.id.screen)

    }
    private fun buttonClick(button: Button){

        val logic = Logic()
        when(button.text){
            "1","2","3","4","5","6","7","8","9","0",->{
                if (displayedNum == "0.0"){
                    displayedNum = ""
                }
                if (button.text =="0"){
                    zeroPressed=true
                }
                displayedNum += button.text
                currentNumber += button.text
                display.text = displayedNum
            }
            "-","+","x","/"->{
                if(logic.check(displayedNum,zeroPressed)) {
                    numb1 = numConvert(displayedNum)
                    currentNumber = ""
                    displayedNum += button.text
                    display.text = displayedNum
                    sign = button.text.toString()

                }
            }
            "C" ->{
                displayedNum = ""
                numb2 = 0.0
                numb1 = 0.0
                currentNumber = ""
                display.text = displayedNum
                zeroPressed = false
            }
            "=" ->{
                numb2 = numConvert(currentNumber)
                if (logic.check(numb2.toString(),zeroPressed)){
                    displayedNum = logic.calculate(numb1,numb2,sign).toString()
                    display.text = intOrDouble(displayedNum)
                }
                zeroPressed = false
            }
        }
    }
}
fun numConvert(numb : String): Double {
    val numb = try {
        numb.toDouble()
    }catch (e: NumberFormatException){
        0.0
    }
    return numb
}
fun intOrDouble(value : String): String {

    return if (value.substring(value.indexOf(".") + 1).toInt() == 0){
        value.toDouble().toInt().toString()
    }
    else{
        value
    }

}