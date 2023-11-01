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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public employee() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id=0;
			response.setContentType("text/html");
			PrintWriter out= response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agency_db","root","123456");
			//int id =request.getParameter("EID");
			
			String name =request.getParameter("ENAME");
			String address =request.getParameter("EADDRESS");
			String phone =request.getParameter("EPHONE");
			String date=request.getParameter("EJDATE");
			//String date=request.getParameter("EJDATE");
			//DateFormat f=new SimpleDateFormat("yyyy-mm-dd");
			//java.sql.Date date1 = null;
			//date1 = (java.sql.Date)f.parse("EJDATE");
			PreparedStatement st=con.prepareStatement("select max(EID)from employee");
			///st.executeUpdate("insert into EMPLOYEE values('"+id+"','"+name+"','"+address+"','"+phone+"','"+date+"')");

			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				id=rs.getInt(1);
				id++;
				//out.println(id);
				PreparedStatement st1=con.prepareStatement("insert into employee values(?,?,?,?,?)");
				st1.setInt(1, id);
				st1.setString(2, name);
				st1.setString(3, address);
				st1.setString(4, phone);
				st1.setString(5, date);
				int i=st1.executeUpdate();
				if(i>0) {
					request.setAttribute("id",id);
					RequestDispatcher rd=request.getRequestDispatcher("emp.jsp");
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

	
