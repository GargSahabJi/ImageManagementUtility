<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.nagarro.imagemanagement.utils.HibernateConnection"%>
<%@page import="com.nagarro.imagemanagement.model.ImageData"%>
<%@page import="com.nagarro.imagemanagement.utils.HibernateConnection"%>
<%@page import="com.nagarro.imagemanagement.utils.HibernateConnection"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="org.hibernate.Query"%>
<%@page import="java.io.OutputStream"%>
<%@ page import="org.hibernate.Session"%>
<%
int id = Integer.parseInt(request.getParameter("id"));
Session s = new HibernateConnection().getConnection();
ImageData image = s.get(ImageData.class, id);
byte byteArray[] = image.getImage();
response.setContentType("image/gif");
OutputStream os = response.getOutputStream();
os.write(byteArray);
os.close();
os.flush();
out.clear();
out = pageContext.pushBody();
%>