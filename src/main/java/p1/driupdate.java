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
import java.util.Date;


public class driupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public driupdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int dis=0;
			PrintWriter out= response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			String eid1=request.getParameter("EID");
			int eid=Integer.parseInt(eid1);
			String vid1=request.getParameter("vID");
			int vid=Integer.parseInt(vid1);
			String tid1 =request.getParameter("TID");
			int tid=Integer.parseInt(tid1);
			//String date=request.getParameter("DDATE");
			PreparedStatement st=con.prepareStatement("select eid from employee where eid=?");
			st.setInt(1, eid);
			ResultSet rs=st.executeQuery();
			PreparedStatement st1=con.prepareStatement("select cid from vehicle where vid=?");
			st1.setInt(1, vid);
			ResultSet rs1=st1.executeQuery();
			PreparedStatement st2=con.prepareStatement("select tid from trip where tid=?");
			st2.setInt(1, tid);
			ResultSet rs2=st2.executeQuery();
			if(rs.next()&&rs1.next()&&rs2.next()) {
				PreparedStatement st3=con.prepareStatement("select  sdate from trip where tid ="+tid+"");
				ResultSet rs3=st3.executeQuery();
				
				if(rs3.next()) {
					Date date=rs3.getDate(1);
					
					java.sql.Statement st0=con.createStatement();
					st0.executeUpdate("update driven_by set eid="+eid+",vid="+vid+",tid="+tid+",ddate="+date+" where eid in(select eid from employee where eid="+eid+")  and vid in( select vid from vehicle where vid="+vid+") and tid in(select tid from trip where tid="+tid+"");
						
					RequestDispatcher rd=request.getRequestDispatcher("driver.jsp");
					rd.forward(request, response); 
				
			}
			
			
				
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("driver.html");
				rd.forward(request, response);
			}
			
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			System.out.println(e);
		}
	
	
	}

}
