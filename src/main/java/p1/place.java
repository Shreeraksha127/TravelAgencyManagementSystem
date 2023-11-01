package p1;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


public class place extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public place() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id=0;
			PrintWriter out= response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			String source=request.getParameter("SOURCE");
			String dest =request.getParameter("DESTINATION");
			String dis1=request.getParameter("DISTANCE_TRA");
			int dist=Integer.parseInt(dis1);
			
		//	out.println("hi");
			PreparedStatement st=con.prepareStatement("select source,destination from place where source=? AND destination=?");
			st.setString(1,source);
			st.setString(2, dest);
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {
				
				RequestDispatcher rd1=request.getRequestDispatcher("placepre.jsp");
				rd1.forward(request, response);
				
			}
			else {
				//RequestDispatcher rd=request.getRequestDispatcher("place1.java");
				//rd.forward(request, response);
				PreparedStatement st1=con.prepareStatement("select max(dis_id) from place");
				ResultSet rs1=st1.executeQuery();
				if(rs1.next()) {
					id=rs1.getInt(1);
					id++;
					PreparedStatement st2=con.prepareStatement("insert into place values(?,?,?,?,?)");
					st2.setInt(1, id);
					st2.setString(2, source);
					st2.setString(3, dest);
					st2.setInt(4, dist);
					st2.setNull(5, Types.NULL);
					int i=st2.executeUpdate();
					if(i>0) {
						request.setAttribute("id",id);
						RequestDispatcher rd=request.getRequestDispatcher("place.jsp");
						rd.forward(request, response);
					}
				}
			}
		} catch (ClassNotFoundException | SQLException  |NumberFormatException e) {
			
			e.printStackTrace();
			System.out.println(e);
		}
	
		
	
	}

		

	

}
