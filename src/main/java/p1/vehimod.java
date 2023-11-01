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


public class vehimod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
           
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			PrintWriter out= response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
		
			String name=request.getParameter("SOURCE");
			String cap=request.getParameter("DESTINATION");
			String vid1=request.getParameter("VID");
			int vid=Integer.parseInt(vid1);
		
			
			PreparedStatement st1=con.prepareStatement("select VID from VEHICLE where vid=?");
			st1.setInt(1, vid);
			ResultSet rs=st1.executeQuery();
			
			PreparedStatement st2=con.prepareStatement("select dis_id from place where source=? and destination=?");
			st2.setString(1, name);
			st2.setString(2, cap);
			ResultSet rs2=st2.executeQuery();
			
			if(rs.next()&&rs2.next()) {
					
					int dis=rs2.getInt(1);
				
					PreparedStatement st=con.prepareStatement("update place set vid="+vid+" where dis_id=?");
					//st.setInt(1,vid);
					st.setInt(1,dis);
					int i=st.executeUpdate();
				
					if(i>0) {
						RequestDispatcher rd=request.getRequestDispatcher("vehimod.jsp");
						rd.forward(request, response);
					}
				
			}else {
				RequestDispatcher rd1=request.getRequestDispatcher("delerrvehi.jsp");
				rd1.forward(request, response);
			}
			
		con.close();
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
		System.out.println(e);
	}
	
	}

	



	}


