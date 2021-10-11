package user.complaint.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import user.complaint.model.service.complaintService;
import user.complaint.model.vo.complaint_manager;

/**
 * Servlet implementation class ComplaintDeleteReplyServlet
 */
@WebServlet("/complaint/deleteReply")
public class ComplaintDeleteReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplaintDeleteReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cm_no = Integer.parseInt(request.getParameter("cm_no"));
		int c_no = Integer.parseInt(request.getParameter("c_no"));
		
		complaint_manager r = new complaint_manager(); 
		r.setCm_no(cm_no);
		r.setC_no(c_no);
		 
		
		List<complaint_manager> replyList = new complaintService().deleteReply(r);
		
		// GSON 라이브러리 추가 후 replyList 응답
		// GSON 사용 시 날짜 값 Date 포맷에 대한 컨트롤 가능(GsonBuilder 객체가 가진 기능)
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();
		gson.toJson(replyList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
