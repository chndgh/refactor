package com.company.project.client.quartz.core.exception;

import com.uumai.client.quartz.core.mail.NeteastMail;

public class ExceptionHelper {

	public void sendexception2mail(String title,String content, Exception e) {
		StringBuffer sb = new StringBuffer();
		sb.append(content);
		sb.append(e.getMessage());
		StackTraceElement[] messages = e.getStackTrace();
		if (messages != null) {
			for (int i = 0; i < messages.length; i++) {
				sb.append("ClassName:" + messages[i].getClassName());
				sb.append("\t");
				sb.append("getFileName:" + messages[i].getFileName());
				sb.append("\t");
				sb.append("getLineNumber:" + messages[i].getLineNumber());
				sb.append("\t");
				sb.append("getMethodName:" + messages[i].getMethodName());
				sb.append("\t");
				sb.append("toString:" + messages[i].toString());
			}
		}
		new NeteastMail().sendmail(title, "kanxg@163.com", sb.toString());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
