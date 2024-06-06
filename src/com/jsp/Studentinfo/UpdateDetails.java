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
@WebServlet("/UpdateDetails")
public class UpdateDetails extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mobilenumber = req.getParameter("mb");
		String sname = req.getParameter("sname");
		String fname = req.getParameter("fname");
		String email = req.getParameter("email");
		String add = req.getParameter("add");
		String password = req.getParameter("password");		
		String gender = req.getParameter("gender");
		String cls = req.getParameter("cls");
		String rollnumber = req.getParameter("roll");
		PrintWriter writer = resp.getWriter();
		if(sname.length()>0 && fname.length()>0 && email.length()>0 && add.length()>0 && password.length()>0 && mobilenumber.length()>0 && gender.length()>0 && cls.length()>0 && rollnumber.length()>0)
		{
		String url ="jdbc:mysql://localhost:3306/teca43?user=root&password=1234";
		String update = "update technoschool set mobilenumber=?,sname=?,fname=?,email=?,Address=?,password=?,gender=?,class=? where rollnumber=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(update);
			ps.setString(1,mobilenumber);
			ps.setString(2,sname);
			ps.setString(3,fname);
			ps.setString(4,email);
			ps.setString(5,add);
			ps.setString(6,password);
			ps.setString(7,gender);
			ps.setString(8,cls);
			ps.setString(9,rollnumber);
			int rs = ps.executeUpdate();
			if(rs!=0)
			{
				RequestDispatcher rd = req.getRequestDispatcher("School.html");
				rd.include(req,resp);
				writer.println("UPdate details success");
			}
			else{
				RequestDispatcher rd = req.getRequestDispatcher("UpdateDetails");
				rd.include(req,resp);
				writer.println("DEtails not UPdated");
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else{
			RequestDispatcher rd = req.getRequestDispatcher("UpdateDetails.html");
			rd.include(req,resp);
			writer.println("INvaid Data");
		}
	}

}
