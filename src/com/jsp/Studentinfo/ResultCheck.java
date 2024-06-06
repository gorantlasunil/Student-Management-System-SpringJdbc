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
@WebServlet("/ResultCheck")
public class ResultCheck extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rollnum = req.getParameter("roll");
		int roll = Integer.parseInt(rollnum);
		String name = req.getParameter("sname");
		String url ="jdbc:mysql://localhost:3306/teca43?user=root&password=1234";
		String select = "select * from studentmarks where rollnumber=? and sname=?";
		PrintWriter writer = resp.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setInt(1,roll);
			ps.setString(2,name);
			ResultSet rs = ps.executeQuery();
			resp.setContentType("text/html");
			if(rs.next())
			{
          	int marks= rs.getInt(3)+rs.getInt(4)+rs.getInt(5)+rs.getInt(6)+rs.getInt(7)+rs.getInt(8);
          	  writer.println("<div style='width:100%;height=1000px'>");
          	 writer.println("<img src='Student login back.jpg' style='width:100%;height:1000px'>");
				writer.println("<center><div><table style='border:3px solid black;border-collapse:collapse'><tr><th style='border:2px solid black'colspan=2><h1>Results</h1></th></tr>");
				writer.println("<tr><td style='border:2px solid black'><h3>RollNumber</h3></td>");
				writer.println("<td style='border:2px solid black'>"+roll+"</td></tr>");
				writer.println("<tr><td style='border:2px solid black'><h3>StudentName</h3></td>");
				writer.println("<td style='border:2px solid black'>"+name+"</td></tr>");
				writer.println("<tr><td style='border:2px solid black'><h3>Marks</h3></td>");
				writer.println("<td style='border:2px solid black'>"+marks+"</td></tr>");
				double percentage =marks*100/600;
				writer.println("<tr><td style='border:2px solid black'><h3>Percentage</h3></td>");
				writer.println("<td style='border:2px solid black'>"+percentage+"</td></tr>");
				if(marks>=550)
				{
					char grade='O';
					writer.println("<tr><td style='border:2px solid black'><h3>Grade</h3></td>");
					writer.println("<td style='border:2px solid black'>"+grade+"</td></tr>");
					
				}
				else if (marks>=500) {
					char grade='A';
					writer.println("<tr><td style='border:2px solid black'><h3>Grade</h3></td>");
					writer.println("<td style='border:2px solid black'>"+grade+"</td></tr>");
				}
				else if (marks>=450) {
					char grade='B';
					writer.println("<tr><td style='border:2px solid black'><h3>Grade</h3></td>");
					writer.println("<td style='border:2px solid black'>"+grade+"</td></tr>");
				}
				else if (marks>=400) {
					char grade='B';
					writer.println("<tr><td style='border:2px solid black'><h3>Grade</h3></td>");
					writer.println("<td style='border:2px solid black'>"+grade+"</td></tr>");
				}
				else if (marks>=350) {
					char grade='C';
					writer.println("<tr><td style='border:2px solid black'><h3>Grade</h3></td>");
					writer.println("<td style='border:2px solid black'>"+grade+"</td></tr>");
				}
				else if (marks>=300) {
					char grade='D';
					writer.println("<tr><td style='border:2px solid black'><h3>Grade</h3></td>");
					writer.println("<td style='border:2px solid black'>"+grade+"</td></tr>");
				}
				else if (marks>=250) {
					char grade='E';
					writer.println("<tr><td style='border:2px solid black'><h3>Grade</h3></td>");
					writer.println("<td style='border:2px solid black'>"+grade+"</td></tr>");
				}
				else{
						String grade="Fail";
						writer.println("<tr><td style='border:2px solid black'><h3>Grade</h3></td>");
						writer.println("<td style='border:2px solid black'>"+grade+"</td></tr>");
				}
//			writer.println("<div style='width:100%;height=700px'>");
//			writer.println("<img src='Student login back.jpg' style='width:100%;height:700px'>");
			writer.println("<br><br><center><table style='border:3px solid black;border-collapse:collapse;border-radius:5px'colspan=6><tr><th style='border:2px solid black;color:Green'colspan=6><h1></h1>MARKS</th></tr>");
			writer.println("<tr><th style='border:2px solid black'><h3>TELUGU</h3></th>");
			writer.println("<th style='border:2px solid black'><h3>HINDHI</h3></th>");
			writer.println("<th style='border:2px solid black'><h3>ENGLISH</h3></th>");
			writer.println("<th style='border:2px solid black'><h3>MATHEMATICS</h3></th>");
			writer.println("<th style='border:2px solid black'><h3>SCIENCE</h3></th>");
			writer.println("<th style='border:2px solid black'><h3>SOCIAL</h3></th></tr>");
	
			writer.println("<tr><td style='border:2px solid black'>"+rs.getInt(3)+"</td>");
			writer.println("<td style='border:2px solid black'>"+rs.getInt(4)+"</td>");
			writer.println("<td style='border:2px solid black'>"+rs.getInt(5)+"</td>");
			writer.println("<td style='border:2px solid black'>"+rs.getInt(6)+"</td>");
			writer.println("<td style='border:2px solid black'>"+rs.getInt(7)+"</td>");
			writer.println("<td style='border:2px solid black'>"+rs.getInt(8)+"</td></tr></table></div></center>");	
			writer.println("</div>");
			}
			else
			{
				RequestDispatcher Dispatcher = req.getRequestDispatcher("Checkmarks.html");
				Dispatcher.include(req,resp);
				writer.println("<center style='color:white';><h1>Enter vaild roll and Name</h1></center>");
				
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
