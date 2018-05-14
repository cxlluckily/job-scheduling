<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shankephone.job.scheduling.util.CookieUtils" %>
<%
	String defaultProfile = "crisp";
	String profile = CookieUtils.getCookieValue(request, "profile");
	if (profile == null || "".equals(profile)) {
		profile = defaultProfile;
		Cookie cookie = new Cookie("profile", profile);
		cookie.setMaxAge(Integer.MAX_VALUE);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
%>                 
<link rel="stylesheet" href="ext/<%=profile %>/resources/KitchenSink-all_1.css"/>
<link rel="stylesheet" href="ext/<%=profile %>/resources/KitchenSink-all_2.css"/>
<link rel="stylesheet" href="ext/packages/ux/classic/<%=profile %>/resources/ux-all.css"/>
<link rel='stylesheet' href='ext/resources/Sencha-Examples/style.css'/>
<link rel='stylesheet' href='resources/css/app.css'/>
<script type='text/javascript' src='ext/ext-all-debug.js'></script>
<script type="text/javascript" src="ext/<%=profile %>/theme.js"></script>
<script type='text/javascript' src='ext/packages/ux/classic/ux.js'></script>
<script type='text/javascript' src='ext/locale-zh_CN.js'></script>
<script type='text/javascript' src='app/app.js'></script>
