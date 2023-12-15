package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DbConnection;
import dao.studentDao;
import modal.student;

public class StudentDaoImpl implements studentDao{

	public static void main(String[] args) {
		/*create
		 * student s=new student(1, "teacher",90,60);
		new StudentDaoImpl().add(s);*/
		
		/*read
		 * System.out.println(new StudentDaoImpl().queryAll1());*/
		
		/*read-list*/
		List<student> l=new StudentDaoImpl().queryAll2();
		int sum=0;
		for(student o:l)
		{
			System.out.println("id:"+o.getId()+
					           "\tname:"+o.getName()+
					           "\tchi:"+o.getChi()+
					           "\teng:"+o.getEng()+
					           "\t總分:"+o.getChi()+o.getEng());
			sum=sum+o.getChi()+o.getEng();
		}
		System.out.println("合計："+sum);
		

	}
    
	@Override
	//create
	public void add(student s) {
		Connection conn=DbConnection.getDb();
		String SQL="insert into student(name,chi,eng) values(?,?,?)";
		
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1,s.getName());
			ps.setInt(2,s.getChi());
			ps.setInt(3,s.getEng());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	//read
	public String queryAll1() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from student";
		String show="";
		
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				show=show+"id:"+rs.getInt("id")+
						  "\t名:"+rs.getString("name")+
						  "\t國文:"+rs.getInt("chi")+
						  "\t英文:"+rs.getString("eng")+"\n";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return show;
	}

	@Override
	//read-list
	public List<student> queryAll2() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from student";
		List<student> l=new ArrayList();
		
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				student s=new student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setChi(rs.getInt("chi"));
				s.setEng(rs.getInt("eng"));
				
				l.add(s);//調出來的資料寫入陣列中
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

}
