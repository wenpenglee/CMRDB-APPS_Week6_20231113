package com.wenpenglee.tipscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wenpenglee.tipscalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.calculateBtn.setOnClickListener{
            calculateTip()
        }

    }

    private fun calculateTip() {

        /**
         * 設定一個變數來獲取並記住在畫面中的消費總額文字輸入框
         * */
        val stringInTextField = binding.spendAmountTv.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        /**
         * 設定一個變數來獲取並記住在畫面中的小費百分比的文字輸入框
         * */
        val percentageTipsTextField = binding.percentageTv.text.toString()
        val tipsPercentage = percentageTipsTextField.toDoubleOrNull()

        /**
         * 假如 cost 變數和 tipsPercentage 為 null ,
         * 則把顯示『小費+消費總額』和『『小費金額』的文字內容設為$0,
         * 接著跳出此function
         * */
        if (cost == null || tipsPercentage == null ) {
            binding.tipAmountTv.text = "\$0"
            binding.totalAmountTv.text = "\$0"
            return
        }

        /**
         * 計算『小費金額』的數學公式
         * */
        var tip = cost * (tipsPercentage/100)

        /**
         * 設定一個變數來獲取並記住在畫面中的進位按鈕是否選取,
         * 如果選取,則把小費金額小數點無條件進位
         * */
        val roundUp = binding.roundUpTipSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        /**
         * 設定一個變數來存儲『消費金額』+『小費金額』的總和
         * */
        val totalCost = cost + tip

        /**
         * 把『小費金額』設定為金額$符號
         * 把『小費+消費總額』設定為金額$符號
         */
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        val formattedTotalCost = NumberFormat.getCurrencyInstance().format(totalCost)

        /**
         * 使用binding把運算的『小費金額』傳到畫面中呈現;
         * 使用binding把運算的『小費+消費總額』傳到畫面中呈現
         */
        binding.tipAmountTv.text = formattedTip
        binding.totalAmountTv.text = formattedTotalCost
    }


}