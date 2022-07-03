package com.example.stockholder.ui.dashboard

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stockholder.DBHelper
import com.example.stockholder.MainActivity
import com.example.stockholder.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    lateinit var mainActivity: MainActivity

    private var _binding: FragmentDashboardBinding? = null
    //val b2 = FragmentDashboardBinding.inflate(layoutInflater)
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /*아래 코드 심화 공부*/
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        //https://curryyou.tistory.com/386
        //https://m.blog.naver.com/wnstn0154/221855117820
        //읽어보기
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val stockName : EditText = binding.stockName
        val btnCalcul : Button = binding.btnCalcul
        val btnReset : Button = binding.btnReset
        val btnSave : Button = binding.btnSave
//        var dbHelper: DBHelper
//        var database: SQLiteDatabase

        val editableText:List<EditText> = listOf(
            binding.e0,binding.e1,binding.e2,binding.e3,binding.e4,binding.e5,binding.e6,binding.e7,
            binding.e8,binding.e9,binding.e10,binding.e11,binding.e12,binding.e13,binding.e14,
            binding.e15,binding.e16,binding.e17,binding.e18,binding.e19
        )
        val averagePrice = binding.averageResult

        dashboardViewModel.text2.observe(viewLifecycleOwner){
            stockName.setText(it)
            Log.d("발동1 ", "발동 2입니다.")
        }

        /*데이터베이스 생성*/
//        dbHelper = DBHelper(mainActivity, "newdb.db", null, 1)
//        database = dbHelper.writableDatabase
//        dbHelper.onCreate(database)


        btnCalcul.setOnClickListener {
            if(editableText[0].text.toString().trim().length == 0) Toast.makeText(view?.context, "목표메수가의 원가를 입력해주세요.", Toast.LENGTH_SHORT).show()
            else{
                if(isNumber(editableText[0].text.toString()) == false){
                    Toast.makeText(mainActivity, "숫자와 자리수<<.>>만 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
                else{
                    var cost : Double  = editableText[0].text.toString().toDouble ()
                    editableText[4].setText(String.format("%.2f", (cost - cost * 0.1)))
                    editableText[8].setText(String.format("%.2f", (cost - cost * 0.2)))
                    editableText[12].setText(String.format("%.2f", (cost - cost * 0.3)))
                    editableText[16].setText(String.format("%.2f", (cost - cost * 0.4)))
                }
            }
        }

        btnReset.setOnClickListener{
            for(i in editableText){
                i.setText("")
            }
            averagePrice.setText("")
        }

        btnSave.setOnClickListener{
            var stockName_db:String = stockName.text.toString()
            /*추후 stock_545를 입력 받은 주식명으로 변경*/
            var sql:String = "INSERT INTO stock_545 values('0', '${stockName_db}', '${editableText[0].text.toString().toFloat()}', '${editableText[1].text.toString().toFloat()}', '${editableText[2].text.toString().toFloat()}', '${editableText[3].text.toString().toFloat()}'), "+
                    "('1', '${stockName_db}', '${editableText[4].text.toString().toFloat()}', '${editableText[5].text.toString().toFloat()}', '${editableText[6].text.toString().toFloat()}', '${editableText[7].text.toString().toFloat()}'), " +
                    "('2', '${stockName_db}', '${editableText[8].text.toString().toFloat()}', '${editableText[9].text.toString().toFloat()}', '${editableText[10].text.toString().toFloat()}', '${editableText[11].text.toString().toFloat()}'), " +
                    "('3', '${stockName_db}', '${editableText[12].text.toString().toFloat()}', '${editableText[13].text.toString().toFloat()}', '${editableText[14].text.toString().toFloat()}', '${editableText[15].text.toString().toFloat()}'), " +
                    "('4', '${stockName_db}', '${editableText[16].text.toString().toFloat()}', '${editableText[17].text.toString().toFloat()}', '${editableText[18].text.toString().toFloat()}', '${editableText[19].text.toString().toFloat()}');"
            mainActivity.database.execSQL(sql)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun isNumber(str: String) : Boolean {
        var temp: Char

        var result = true

        for (i in 0 until str.length) {
            temp = str.elementAt(i)
            if ((temp.toInt() < 48 || temp.toInt() > 57) && temp != '.') {
                result = false
            }
        }

        return result
    }
}