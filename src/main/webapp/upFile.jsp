<%-- 
    Document   : upFile
    Created on : 2013-3-11, 9:49:07
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>File upload</title>
</head>
<body>
     <form name="myform" action="fileupload" method="post"   enctype="multipart/form-data">
       文件<br />
       <input type="file" name="myfile">
      <input type="file" name="myfile1">
       <br>
       <br>
       <input type="submit" name="submit" value="提交">
    </form>
</body>
</html>
