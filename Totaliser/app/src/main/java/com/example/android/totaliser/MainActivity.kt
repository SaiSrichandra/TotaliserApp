package com.example.android.totaliser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Number buttons listeners
        One.setOnClickListener { compute("1", true) }
        Two.setOnClickListener { compute("2", true) }
        Three.setOnClickListener { compute("3", true) }
        Four.setOnClickListener { compute("4", true) }
        Five.setOnClickListener { compute("5", true) }
        Six.setOnClickListener { compute("6", true) }
        Seven.setOnClickListener { compute("7", true) }
        Eight.setOnClickListener { compute("8", true) }
        Nine.setOnClickListener { compute("9", true) }
        Zero.setOnClickListener { compute("0", true) }
        Period.setOnClickListener { compute(".", true) }

        //Operator button Listeners
        Plus.setOnClickListener { compute("+", false) }
        Minus.setOnClickListener { compute("-", false) }
        Mul.setOnClickListener { compute("*", false) }
        Divide.setOnClickListener { compute("/", false) }

        Clear.setOnClickListener {
            Expression.text = ""
            Result.text = ""
        }

        Delete.setOnClickListener {
            val string = Expression.text.toString()
            if(string.isNotEmpty()){
                Expression.text = string.substring(0,string.length-1)
            }
            Result.text = ""
        }

        Equals.setOnClickListener {
        try{
            val expression = ExpressionBuilder(Expression.text.toString()).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if(result == longResult.toDouble())
                Result.text = longResult.toString()
            else
                Result.text = result.toString()
        } catch (e : Exception) {
            val myToast = Toast.makeText(applicationContext,
                Html.fromHtml("<font color='#e3f2fd' ><b> Invalid Operation </b></font>"),Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.START,0,-500)
            myToast.show()
            Result.text = ""
            Expression.text = ""
        }
        }

    }

    fun compute(string: String, Clear: Boolean) {

        if(Result.text.isNotEmpty()){
            Expression.text = ""
        }

        if (Clear) {
            Result.text = ""
            Expression.append(string)
        } else {
            Expression.append(Result.text)
            Expression.append(string)
            Result.text = ""
        }
    }
}
