package p1;
import p1.confirm;
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
import java.util.ArrayList;


public class print extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=charset=ISO-8859-1");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			String cid1=request.getParameter("CID");
			int cid=Integer.parseInt(cid1);
			String tid1 =request.getParameter("TID");
			int tid=Integer.parseInt(tid1);
			PreparedStatement st=con.prepareStatement("select cid,tid from payment where cid=? and tid=?");
			st.setInt(1, cid);
			st.setInt(2, tid);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				PrintWriter out= response.getWriter();
				//out.println("<!DOCTYPE html>");
				//out.println("<html>");
				//out.println("<head>");
				//out.println("<title>confirmation </title>");
				//out.println("</head>");
				//out.println("<body>");
				//ArrayList<confirm> conf=new ArrayList<confirm>();
				PreparedStatement stmt=con.prepareStatement("select CNAME,CADDRESS,CPHONE from customer where cid=?");
				stmt.setInt(1, cid);
				ResultSet res=stmt.executeQuery();
				PreparedStatement stmt1=con.prepareStatement("select SOURCE,DESTINATION,SDATE from trip where tid=?");
				stmt1.setInt(1, tid);
				ResultSet res1=stmt1.executeQuery();
				PreparedStatement stmt2=con.prepareStatement("select distance,pfee from payment where cid=? and tid=?");
				stmt2.setInt(1, cid);
				stmt2.setInt(2, tid);
				ResultSet res2=stmt2.executeQuery();
				if(res.next()&&res1.next()&&res2.next()) {
				String name=res.getString(1);
				String address=res.getString(2);
				String phone=res.getString(3);
				String source=res1.getString(1);
				String destination=res1.getString(2);
				String date=res1.getString(3);
				int dis=res2.getInt(1);
				int fee=res2.getInt(2);
				
				//PreparedStatement stmt3=con.prepareStatement("select vid from place where dis_id="+dis+")");
			//	stmt3.setInt(1, dis);
			//	ResultSet res3=stmt3.executeQuery();
				
				//if(res3.next()) {
				//String vname=res3.getString(1);
				//String regno=res3.getString(2);
				String nw=System.lineSeparator();
				out.println("<p>cid:- "+cid+ nw +"</p>");
				out.println("<p>Tid:- "+tid+ nw +"</p>");
				out.println("<p>Name:- "+name+"</p>");
				out.println("<p>Address:- "+address+"</p>");
				out.println("<p>source:- "+source+"</p>");
				out.println("<p>Destination:- "+destination+"</p>");
				out.println("<p>Trip date:- "+date+"</p>");
			//	out.println("<p>Vehicle name:- "+vname+"</p>");
				//out.println("<p>Vehicle registration no:- "+regno+"</p>");
				out.println("<p>The total fees is:- "+fee+"</p>");
				out.println("<p><b>Your booking is confirmed</b></p>");
				
				out.println("<button onclick=\"myfun()\">print </button>\r\n"
						+ "<script type=\"text/javascript\">\r\n"
						+ "function myfun(){\r\n"
						+ "	window.print();\r\n"
						+ "}\r\n"
						+ "\r\n"
						+ "</script>");
				
				
			
				//}
				}
					//out.println("</body>");
					
				//out.println("</html>");
				
			}else {
				RequestDispatcher rd1=request.getRequestDispatcher("payment1.jsp");
				rd1.forward(request, response);
			}
			
			con.close();
		} catch (ClassNotFoundException | SQLException | java.lang.NullPointerException e) {
			//e1.printStackTrace();
			e.printStackTrace();
			System.out.println(e);
		}
	
	}

}
