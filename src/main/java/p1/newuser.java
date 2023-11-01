package p1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class newuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public newuser() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			PrintWriter out= response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			String username =request.getParameter("USERNAME");
			String password =request.getParameter("PASSWORD");
		//	java.sql.Statement st=con.createStatement();
			//st.executeUpdate("insert into login values('"+username+"','"+password+"')");
			PreparedStatement st1=con.prepareStatement("insert into login values(?,?)");
			st1.setString(1, username);
			st1.setString(2, password);
			int i=st1.executeUpdate();
			if(i>0) {
			//con.close();
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.forward(request, response);
			con.close();
			}
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
		System.out.println(e);
	}
	}

}
