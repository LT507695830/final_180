<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function(K) {
            K.create('#editor_id',{
                width:'900px',
                height:'500px',
                minHeight:400,
                resizeType:0,
                allowFileManager:true,
                filePostName:'img',
                uploadJson:'${pageContext.request.contextPath}/Kindeditor/uploadImg',
                fileManagerJson:"${pageContext.request.contextPath}/Kindeditor/getAllImgs"
            });
        });
    </script>
</head>
<body>
<form>
    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
        请输入内容

    </textarea>
</form>
</body>
</html>