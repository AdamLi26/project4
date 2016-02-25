<% 
	String[] ids = (String[]) request.getAttribute("idList");
	String[] names = (String[]) request.getAttribute("nameList");
%>
<html>
<head>
    <title>Result</title>
</head>
<body>
    Hello, world.
    <TABLE BORDER=2>
	<%
	    for ( int i = 0; i < ids.length; i++ ) {
	        %>
	        <TR>
	        <TD><%= ids[i] %></TD>
	        <TD><%= names[i] %></TD>
	        </TR>
	        <%
	    }
	%>
	</TABLE>
</body>
</html>