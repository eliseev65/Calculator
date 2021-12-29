package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

var oper = ""
var res = ""

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        math_operation.text = oper
        result_text.text = res

        btn_0.setOnClickListener { setTextFields("0") }
        btn_1.setOnClickListener { setTextFields("1") }
        btn_2.setOnClickListener { setTextFields("2") }
        btn_3.setOnClickListener { setTextFields("3") }
        btn_4.setOnClickListener { setTextFields("4") }
        btn_5.setOnClickListener { setTextFields("5") }
        btn_6.setOnClickListener { setTextFields("6") }
        btn_7.setOnClickListener { setTextFields("7") }
        btn_8.setOnClickListener { setTextFields("8") }
        btn_9.setOnClickListener { setTextFields("9") }

        dot_btn.setOnClickListener { setTextFields(".") }
        minus_btn.setOnClickListener {setTextFields("-")}
        plus_btn.setOnClickListener {setTextFields("+")}
        div_btn.setOnClickListener { setTextFields("/") }
        mul_btn.setOnClickListener { setTextFields("*") }
        open_bracket_btn.setOnClickListener { setTextFields("(") }
        close_bracket_btn.setOnClickListener { setTextFields(")") }

        clear_btn.setOnClickListener {
            math_operation.text = ""
            result_text.text = ""
            oper = ""
            res = ""
        }

        equal_btn.setOnClickListener {
            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if (result == longRes.toDouble())
                    result_text.text = longRes.toString()
                else
                    result_text.text = result.toString()

            } catch (e:Exception) {
                Log.d("Ошибка", "сообщение: ${e.message}")

                result_text.text = getString(R.string.error_name)
            }
            res = result_text.text.toString()
        }

        back_btn.setOnClickListener {
            val str = math_operation.text.toString()
            if (str.isNotEmpty()) {
                val s = str.substring(0, str.length - 1)
                math_operation.text = s
                oper = s
            }
            result_text.text = ""
            res = ""
        }
    }

    private fun setTextFields(str: String) {
//        if (result_text.text != "") {
//            math_operation.text = result_text.text
//            result_text.text = ""
//        }
        math_operation.append(str)
        oper = math_operation.text.toString()
    }
}