package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInputField:TextView?=null

    private var lastNumeric:Boolean=false
    private var lastDot:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInputField=findViewById(R.id.tvInputField)

    }


    fun onDigit(view:View){


        tvInputField?.append((view as Button).text)
        lastNumeric=true

    }


    fun onCLear(view: View){
        tvInputField?.text=""
        lastDot=false


    }


    fun onDecimalPoint(view: View) {


         if (lastNumeric && !lastDot) {
            tvInputField?.append(".")
            lastNumeric = false
            lastDot = true

        }


    }




    fun onOperator(view: View){

        tvInputField?.text?.let {


                if(lastNumeric&&!isOperatorAdded(it.toString())){

                    tvInputField?.append((view as Button).text)
                    lastNumeric=false
                    lastDot=false



                }
        }
    }


    fun onEqual(view: View){

        if(lastNumeric){
            var tvValue=tvInputField?.text.toString()
            var prefix=""
            try{

                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    var splitValue=tvValue.split("-")
                    var one=splitValue[0]
                    var two=splitValue[1]


                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    tvInputField?.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())

                }
               else if(tvValue.contains("+")){
                    var splitValue=tvValue.split("+")
                    var one=splitValue[0]
                    var two=splitValue[1]


                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    tvInputField?.text=removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())

                }
               else if(tvValue.contains("/")){
                    var splitValue=tvValue.split("/")
                    var one=splitValue[0]
                    var two=splitValue[1]


                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    tvInputField?.text=removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())

                }
                else if(tvValue.contains("*")){
                    var splitValue=tvValue.split("*")
                    var one=splitValue[0]
                    var two=splitValue[1]


                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    tvInputField?.text=removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())



                }


            }catch(e:ArithmeticException){
                e.printStackTrace()
            }
        }

    }


    private fun removeZeroAfterDot(result:String):String{
        var value=result
        if(result.contains(".0"))
            value=result.substring(0,result.length-2)
        return value

    }



   private fun isOperatorAdded(value:String):Boolean{
       return if(value.startsWith("-")){
           false
       }else{
           value.contains("/") || value.contains("*")|| value.contains("+")||value.contains("-")
       }
   }

}