package p1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class vehicle1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public vehicle1() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			int id=0;
			String name=request.getParameter("VNAME");
			String cap=request.getParameter("CAPACITY");
			String type=request.getParameter("TYPE");
			String reg=request.getParameter("REGNO");
			PreparedStatement st1=con.prepareStatement("select max(VID) from VEHICLE");
		
			ResultSet rs=st1.executeQuery();
			if(rs.next()) {
				id=rs.getInt(1);
			id++;

				PreparedStatement st=con.prepareStatement("insert into vehicle(vid,vname,capacity,type,regno) values (?,?,?,?,?)");
				st.setInt(1, id);
				st.setString(2, name);
				st.setString(3, cap);
				st.setString(4, type);
				st.setString(5, reg);
				int i=st.executeUpdate();
				if(i>0) {
					request.setAttribute("id",id);
					RequestDispatcher rd=request.getRequestDispatcher("vehregi1.jsp");
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
