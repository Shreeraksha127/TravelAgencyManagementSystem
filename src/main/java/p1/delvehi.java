package p1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class delvehi extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public delvehi() {
        super();
        // TODO Auto-generated constructor stub
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
			
			PrintWriter out= response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			String vid1 =request.getParameter("VID");
			int vid=Integer.parseInt(vid1);
			PreparedStatement st=con.prepareStatement("delete from vehicle where vid=?");
			st.setInt(1, vid);
			//ResultSet rs=st.executeQuery();
			int i=st.executeUpdate();
			if(i>0) {
			//con.close();
				RequestDispatcher rd1=request.getRequestDispatcher("delvehi.jsp");
				rd1.forward(request, response);
			con.close();
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("delerrvehi.jsp");
				rd.forward(request, response);
			}
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
		System.out.println(e);
	}
	

	}

}
