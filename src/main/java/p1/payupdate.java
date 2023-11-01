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


public class payupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public payupdate() {
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
			String cid1=request.getParameter("CID");
			int cid=Integer.parseInt(cid1);
			String tid1 =request.getParameter("TID");
			int tid=Integer.parseInt(tid1);
			String date=request.getParameter("PDATE");
			//Date datel=Date.parse(date);
			//request.setAttribute("date", date);
			
			//request.setAttribute("date", date);
			//RequestDispatcher rd=request.getRequestDispatcher("payment.jsp");
			//rd.forward(request, response);
			PreparedStatement st=con.prepareStatement("select cid,tid from payment where cid=? and tid=?");
			st.setInt(1, cid);
			st.setInt(2, tid);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				
				PreparedStatement st0=con.prepareStatement("update payment set PDATE=? where cid=? and tid=?");
				st0.setString(1, date);
				st0.setInt(2,cid);
				st0.setInt(3, tid);
				//ResultSet res=st0.executeQuery();
				int i=st0.executeUpdate();
				
				if(i>0) {
					RequestDispatcher rd=request.getRequestDispatcher("payment.jsp");
					rd.forward(request, response);
					
				}
			}else {
				RequestDispatcher rd1=request.getRequestDispatcher("payment.html");
				rd1.forward(request, response);
			}
			
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			System.out.println(e);
		}
	
	}
}
