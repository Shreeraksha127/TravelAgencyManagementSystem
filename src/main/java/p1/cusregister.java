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

public class cusregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public cusregister(){
	        super();
	       
	    }

		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			
			int id=0;
			String name =request.getParameter("CNAME");
			String address =request.getParameter("CADDRESS");
			String phone =request.getParameter("CPHONE");
			
			
			PreparedStatement st1=con.prepareStatement("select max(CID) from customer");
		
			ResultSet rs=st1.executeQuery();
			if(rs.next()) {
				id=rs.getInt(1);
				id++;

				PreparedStatement st=con.prepareStatement("insert into customer values(?,?,?,?)");
				st.setInt(1, id);
				st.setString(2, name);
				st.setString(3, address);
				st.setString(4, phone);
				int i=st.executeUpdate();
				if(i>0) {
					request.setAttribute("id",id);
					RequestDispatcher rd=request.getRequestDispatcher("cusregi.jsp");
					rd.forward(request, response);
				}
			}
			
			con.close();
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
		System.out.println(e);
	}
	
	}

}
