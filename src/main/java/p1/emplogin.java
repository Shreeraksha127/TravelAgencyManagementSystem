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
import java.sql.ResultSet;
import java.sql.SQLException;


public class emplogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public emplogin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out= response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			String id =request.getParameter("EID");
			String ename =request.getParameter("ENAME");
			PreparedStatement st=con.prepareStatement("select EID,ENAME from employee where EID=? AND ENAME=?");
			st.setString(1,id);
			st.setString(2, ename);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("empdash.html");
				rd.forward(request, response);
			}
			else {
				out.println("<font colour=red size=18>Login failed!");
				out.println("Invalid id");
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			System.out.println(e);
		}
		
	}

}
