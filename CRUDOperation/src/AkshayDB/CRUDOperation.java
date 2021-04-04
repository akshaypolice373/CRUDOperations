package AkshayDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CRUDOperation {
	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps_Sins=null, ps_marksIns=null, ps_selS=null;
		ResultSet rs=null;
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		String name, email;
		String strDt;
		int marks;
		String std;		
		try {
			con = JDBCHelper.getConnection();
			con.setAutoCommit(false);
			ps_Sins = con.prepareStatement("insert into student(name, email, dob) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps_marksIns = con.prepareStatement("insert into student_marks(student_sl, std, marks) values(?, ?, ?)");
			
			System.out.println("Established Connection = "+ con);
		
			System.out.println("Enter name of Student");
			name = sc2.nextLine();
			System.out.println("Enter email of Student");
			email = sc2.nextLine();
			System.out.println("Enter dob of the Student (dd/mm/yyyy)");
			strDt = sc2.nextLine();
			
			ps_Sins.setString(1, name);
			ps_Sins.setString(2, email);	
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			java.util.Date dt = sdf.parse(strDt);
			java.sql.Date sqlDt = new java.sql.Date(dt.getTime());;
			ps_Sins.setDate(3, sqlDt);
			ps_Sins.execute();
			
			rs = ps_Sins.getGeneratedKeys();
			rs.next();
			int sl_no = rs.getInt(1);
			
			
			int ch = 0;
			while(ch!=2) {
				System.out.println("Press 1 to enter std marks");
				System.out.println("Press 2 to go back");
				ch = sc1.nextInt();
				
				switch(ch) {
				case 1: 
					System.out.println("Enter standard for Student"+name);
					std = sc2.nextLine();
					System.out.println("Enter % of student for std as integer "+std);
					marks = sc1.nextInt();
					
					ps_marksIns.setInt(1, sl_no);
					ps_marksIns.setString(2, std);
					ps_marksIns.setInt(3, marks);
					
					ps_marksIns.execute();
					
					
					break;
				case 2:
					
					break;
					
				}
			}
			
			con.commit();
			
		}
		
		catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) { 
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_marksIns);
			JDBCHelper.close(ps_selS);
			JDBCHelper.close(ps_Sins);
			JDBCHelper.close(con);
		}
	}
}
