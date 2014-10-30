package com.Listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.text.ParseException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.DispatcherListener;

import common.Constants;

public class init implements ServletContextListener{
	private Timer judge = new Timer();
	private CheckTimeAndSendEmail check=new CheckTimeAndSendEmail();
	public void contextDestroyed(ServletContextEvent sce) {  
    }
	public void contextInitialized(ServletContextEvent sce) {  
		try {
			SimpleDateFormat sdfDuty = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm:ss");
			Date starttime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
					.parse(sdfDuty.format(new Date()));
			TimerTask updataTask = new TimerTask() {
				Date now = new Date();
				@Override
				public void run() {
					try {
						check.Start(now);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			};
			judge.scheduleAtFixedRate(updataTask, starttime,
					Constants.UPDATESPAN);
		} catch (ParseException e) {
			System.out.println(e);
		}
    }  
//	@Override
//	public void dispatcherInitialized(Dispatcher dispatcher) {
//		
//	}
//
//	@Override
//	public void dispatcherDestroyed(Dispatcher dispatcher) {
//		// do nothing
//	}
}