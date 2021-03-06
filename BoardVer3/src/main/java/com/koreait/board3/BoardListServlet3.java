package com.koreait.board3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/list3")
public class BoardListServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<BoardVO3> list = BoardDAO.selBoardList();	// sel = select, List = 여러개 / () = argument가 없다는 것은 기준이 없음 (모든 것을 받을 수 있음)
														// BoardDAO.selBoardList() = ArrayList의 배열 주소 값 -> BoardDAO에 객체 생성
		request.setAttribute("list", list);
		
		MyUtils.openJSP("list3", request, response);		// static일 경우 = 클래스명.메소드명
		
		/*
		MyUtils utils = new MyUtils();					// void = 객체 생성 후 메소드 호출
		utils.openJSP(jsp, request, response);
		*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
