package com.example.stockholder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stockholder.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        
        /*데이터 베이스의 각 값을 리스트 형태로 보여주기
        * 해당 리스트를 클릭하면 대쉬보드로 넘어가기,
        * 대쉬보드에서는 해당 DB의 모든 값 보여주기
        * 대쉬보드의 수정하기 버튼을 누르면 수정이 가능한 상태로 변경하기, 
        * 수정을 누르면 수정 버튼이 저장으로 바뀌기, 초기화 버튼, 계산 버튼 활성화하기
        * 저장 누르면 저장 버튼이 수정 버튼으로 바뀌기"겹치는 레이아웃과 ebable활용"*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}