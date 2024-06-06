package com.jsp.Studentinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/StudentMarks")
public class StudentMarks extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rollnum = req.getParameter("roll");
		int roll = Integer.parseInt(rollnum);
		String name = req.getParameter("sname");
		String tel = req.getParameter("te");
		int te = Integer.parseInt(tel);
		String hin = req.getParameter("hi");
		int hi = Integer.parseInt(hin);
		String eng = req.getParameter("en");
		int en = Integer.parseInt(eng);
		String maths = req.getParameter("ma");
		int ma = Integer.parseInt(maths);
		String science = req.getParameter("sci");
		int sci = Integer.parseInt(science);
		String social= req.getParameter("soc");
		int soc = Integer.parseInt(social);
		String url ="jdbc:mysql://localhost:3306/teca43?user=root&password=1234";
		String select = "select * from technoschool where rollnumber=? and sname=?";
		PrintWriter writer = resp.getWriter();
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setInt(1,roll);
			ps.setString(2,name);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Class.forName("com.mysql.jdbc.Driver");
				String insert = "insert into studentmarks values(?,?,?,?,?,?,?,?)";
				PreparedStatement ps1 = connection.prepareStatement(insert);
				ps1.setInt(1,roll);
				ps1.setString(2,name);
				if(te>=0&&te<=100)
				{
					ps1.setInt(3,te);
				}
				if(hi>=0&&hi<=100)
				{
					ps1.setInt(4,hi);
				}
				if(en>=0&&en<=100)
				{
					ps1.setInt(5,en);
				}
				if(ma>=0&&ma<=100)
				{
					ps1.setInt(6,ma);
				}
				if(sci>=0&&sci<=100)
				{
					ps1.setInt(7,sci);
				}
				if(soc>=0&&soc<=100)
				{
					ps1.setInt(8,soc);
				}
				int result=0;
				if(te>=0 && hi>=0 &&en>=0 && ma>=0 && sci>=0 && soc>=0)
				{
					result = ps1.executeUpdate();
				}
				if(result!=0)
				{
					RequestDispatcher Dispatcher = req.getRequestDispatcher("School.html");
					Dispatcher.include(req,resp);
					writer.println("m");
				}
				else{
					RequestDispatcher Dispatcher = req.getRequestDispatcher("Marks.html");
					Dispatcher.include(req,resp);
					writer.println("Enetr vaild marks");
				}
			}
				else{
					RequestDispatcher Dispatcher = req.getRequestDispatcher("Marks.html");
					Dispatcher.include(req,resp);
					writer.println("Invaild Data Entered");
				}	 
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
