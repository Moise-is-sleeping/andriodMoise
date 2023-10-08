package com.Calculator.andriodmoise

import java.lang.Exception


class Logic {

    fun calculate(num1 : Double,num2 : Double, sign:String):Double{
        var total = 0.0
        when(sign){
            "+"-> total = num1 + num2
            "-"-> total = num1 - num2
            "x"-> total = num1 * num2
            "/"-> total = num1 / num2
        }
        return round(total)
    }

    fun check(value : String, zeroPressed :Boolean): Boolean {
        var bool = false
        try {
            if(value.toDouble() != 0.0){
                bool = true
            }
            else if (zeroPressed){
                bool = true
            }
        }catch (e:Exception){
           bool = false
        }
        return bool
    }

    private fun round(value:Double): Double {
        val num = value.toString()
        return try {
            val pos = num.indexOf(".")
            num.substring(0,pos+3).toDouble()
        }catch (e:Exception){
            value
        }


    }
}