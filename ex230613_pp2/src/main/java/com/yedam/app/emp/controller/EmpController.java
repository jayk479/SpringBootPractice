package com.yedam.app.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	//전체조회 페이지
	@GetMapping("empList")
	public String empList(Model model) {
		model.addAttribute("empList", empService.getEmpList());
		return "empList";//확장자제외파일이름만넣어줘
	}
	
	//단건조회 페이지
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) {
		model.addAttribute("empinfo", empService.getEmpInfo(empVO));
		return "empInfo";
	}
	
	//등록 페이지
	@GetMapping("empInsert")
	public void empInsertForm(Model model) {
		model.addAttribute("empVO", new EmpVO());
		//th:object='${empVO}' 를 쓰고 싶으면 페이지 불러올 때 별도의 객체도 같이 보내줘야 됨
		//empVO는 등록페이지, 등록처리에서도 같이 쓰이기 때문이 이름이 같아야 됨
		//이거 받아서 다시 쓸거잖아
	}
	
	//등록 처리
	@PostMapping("empInsert")
	public String empInsert(EmpVO empVO) {
		empService.insertEmpInfo(empVO);
		return "redirect:empList";
	}
}
