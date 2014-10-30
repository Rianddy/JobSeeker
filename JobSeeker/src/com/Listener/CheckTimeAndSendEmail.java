package com.Listener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.GetJobidsFromIndex;
import common.SendMessage;
import function.index.ConnectingToMysql;

public class CheckTimeAndSendEmail {
	ConnectingToMysql connecting;
	Connection connection;
	ResultSet rs;
	ResultSet rs2;
	List<String> contentList=new ArrayList<String>();
	static private String usersTableName="Users";
	static private String jobsTableName="Jobs";
	static private String feedsTableName="Feeds";
	public CheckTimeAndSendEmail(){
		try {
			connecting = new ConnectingToMysql();
			connection = connecting.Get_Connection();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void Start(Date dtime) throws Exception{
		SendMessage sendEmail=new SendMessage();
		PreparedStatement ps2 = connection.prepareStatement("SELECT "+feedsTableName+".username,title,location,date,email,feedid FROM "
				+ feedsTableName+","+usersTableName+" WHERE "+feedsTableName+".username="+usersTableName+".username" );
		rs2= ps2.executeQuery();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		while(rs2.next()){
			String username=rs2.getString(1);
            String title=rs2.getString(2);
            String location=rs2.getString(3);
            String date=rs2.getString(4);
            String email=rs2.getString(5);
            String feedid=rs2.getString(6);
			GetJobidsFromIndex getJobids=new GetJobidsFromIndex(title+" "+location);
			List<String> jobids=getJobids.Search();
			String inStatement="('";
			int i=0;
			for(i=0;i<jobids.size()-1;i++){
				inStatement=inStatement+jobids.get(i)+"','";
			}
			inStatement=inStatement+jobids.get(i)+"')";
			PreparedStatement ps = connection.prepareStatement("SELECT jobid,title,company,location,summary,date FROM "
					+ jobsTableName+" WHERE jobid IN "+inStatement);	
			rs = ps.executeQuery();
			String emailContent="";
			while (rs.next()) {
	            String jobId=rs.getString(1);
	            String jobtitle=rs.getString(2);
	            String company=rs.getString(3);
	            String joblocation=rs.getString(4);
	            String summary=rs.getString(5);
	            String jobdate=rs.getString(6);
	            if(getIntervalDays((Date)format.parse(date),(Date)format.parse(jobdate))>1){
	            	emailContent+=packageEmailContent(jobtitle,company,joblocation,summary,jobdate);
	            }  
	        }
			if(!emailContent.equals("")){
				sendEmail.Start("Job Alert", emailContent, email);
			}
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			PreparedStatement ps3 = connection.prepareStatement("UPDATE "+feedsTableName+" SET date='"+currentTime+"' WHERE feedid= '"
					+ feedid+"'");
			ps3.executeUpdate();
		}
		rs2.close();
		rs.close();
	}
	public String packageEmailContent(String title,String company, String location, String summary, String date){
		return title+"\n"+company+"\n"+location+"\n"+summary+"\n"+date+"\n\n\n";
	}
	public int getIntervalDays(Date startday,Date endday)
	{              
        long sl=startday.getTime(); 
        long el=endday.getTime();       
        long ei=el-sl;
        return (int)(ei/(1000*60*60*24)); 
    } 
}
