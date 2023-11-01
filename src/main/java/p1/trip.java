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


public class trip extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public trip() {
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
			int dis=0;
			PrintWriter out= response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			String source =request.getParameter("SOURCE");
			String dest =request.getParameter("DESTINATION");
			String date=request.getParameter("SDATE");
			PreparedStatement st1=con.prepareStatement("select max(TID) from trip");
			ResultSet rs=st1.executeQuery();
			
			if(rs.next()) {
				id=rs.getInt(1);
				id++;
				PreparedStatement st2=con.prepareStatement("select dis_id from place where source=? and  destination= ?");
				st2.setString(1,source);
				st2.setString(2,dest);
				
				ResultSet rs2=st2.executeQuery();
				if(rs2.next()) {
					dis=rs2.getInt(1);
					PreparedStatement st=con.prepareStatement("insert into trip values(?,?,?,?,?)");
					st.setInt(1, id);
					st.setString(2, source);
					st.setString(3, dest);
					st.setString(4, date);
					st.setInt(5, dis);
					int i=st.executeUpdate();
					if(i>0) {
						request.setAttribute("id",id);
						RequestDispatcher rd=request.getRequestDispatcher("trip.jsp");
						rd.forward(request, response);
					}
				}
			}
			
			con.close();
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
		System.out.println(e);
	}
	
	}


}
