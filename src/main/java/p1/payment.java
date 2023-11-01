package p1;

import jakarta.servlet.RequestDispatcher;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
public class payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public payment() {
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
			PreparedStatement st=con.prepareStatement("select cid from customer where cid= ?");
			st.setInt(1, cid);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				
				PreparedStatement st1=con.prepareStatement("select tid from trip where tid=?");
				st1.setInt(1, tid);
				ResultSet rs1=st1.executeQuery();
				if(rs1.next()) {
					
					PreparedStatement st2=con.prepareStatement("select  distance_tra from place where dis_id =(select dis_id from trip where tid=?)");
					st2.setInt(1, tid);
					ResultSet rs2=st2.executeQuery();
					PreparedStatement st9=con.prepareStatement("select  vid from place  where dis_id =(select dis_id from trip  where tid=?)");
					st9.setInt(1, tid);
					ResultSet rs9=st9.executeQuery();
					PreparedStatement st6=con.prepareStatement("select  sdate from trip  where tid=?");
					st6.setInt(1, tid);
					ResultSet rs6=st6.executeQuery();
					if(rs2.next()&&rs9.next()&&rs6.next()) {
						int vid=rs9.getInt(1);
						String date=rs6.getString(1);
						dis=rs2.getInt(1);
						
						int pay=15*dis;
						java.sql.Statement st4=con.createStatement();
						st4.executeUpdate("insert into payment(cid,tid,distance,pfee) values('"+cid+"','"+tid+"','"+dis+"','"+pay+"')");
						java.sql.Statement st0=con.createStatement();
						st0.executeUpdate("insert into booked_for values('"+cid+"','"+vid+"','"+tid+"','"+date+"')");
						request.setAttribute("pay",pay);
						RequestDispatcher rd1=request.getRequestDispatcher("confirm.jsp");
						rd1.forward(request, response);
						con.close();
							//	}
						//}
					}		
				}
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

		private Statement createStatement() {
			// TODO Auto-generated method stub
			return null;
		}

}
