package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/nhap-diem")
public class NhapDiemController extends HttpServlet {
	public static int setId(String s) {
		if(s==null || s=="null") {
			return -1;
		}
		else {
			return Integer.valueOf(s);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Integer idGV = (Integer) req.getSession().getAttribute("gv_id");
		int idGV = 1;
		
		if(idGV==-1) {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		req.getRequestDispatcher("nhap-diem.jsp").forward(req, resp);
	}
}
