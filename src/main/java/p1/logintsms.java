package p1;

import jakarta.servlet.RequestDispatcher;



import jakarta.servlet.ServletConfig;


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
//@WebServlet("/logintams")
public class logintsms extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    
    public logintsms() {
        super();
       
    }

	
	public ServletConfig getServletConfig() {
		
		return null;
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			PrintWriter out1= response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			String username =request.getParameter("USERNAME");
			String password =request.getParameter("PASSWORD");
			PreparedStatement st=con.prepareStatement("select USERNAME,PASSWORD from login where USERNAME=? AND PASSWORD=?");
			st.setString(1,username);
			st.setString(2, password);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				
				RequestDispatcher rd1=request.getRequestDispatcher("login.jsp");
			
				rd1.forward(request, response);
				
			}
			else {
				
				
				RequestDispatcher rd=request.getRequestDispatcher("newuser.jsp");
				rd.forward(request, response);
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			System.out.println(e);
		}
	
		
	}

}
