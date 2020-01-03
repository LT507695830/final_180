<%--
  Created by IntelliJ IDEA.
  User: LT
  Date: 2019/11/27
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/zhu.ico" type="image/x-icon">
    <title>后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--1.bootstrap 核心 css--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <%--2.jqgrid相关css--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--3.jquery核心js--%>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <%--4.bootstrap相关js--%>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>
    <%--5.jqgrid相关js--%>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%--上传--%>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <%--富文本编辑器--%>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <style type="text/css">

    </style>

    <script>


    </script>
</head>
<body>
<!--导航栏-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持明法州管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#">
                        <span>欢迎 ${sessionScope.Admin.username}</span>
                    </a>
                </li>
                <li class="dropdown">
                    <a href="#">
                        <span class="glyphicon glyphicon-hand-right">退出登录</span>
                    </a>
                </li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<!-- 主体 -->
<div class="container-fluid">
    <div class="row">


        <!-- 左栏 -->
        <div class="col-sm-3">
            <!-- 手风琴 -->
            <br/><br/>
            <div class="panel-group" id="parent">
                <!--
                    用户
                -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <center>
                            <h3 class="panel-title">
                                <a href="#first" data-toggle="collapse" data-parent="#parent">
                                    用户管理
                                </a>
                            </h3>
                        </center>
                    </div>

                    <div id="first" class="panel-collapse collapse in ">
                        <center>
                            <button class="btn btn-danger">用户列表</button>
                        </center>
                    </div>
                </div>

                <!--
                    上师
                -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <center>
                            <h3 class="panel-title">
                                <a href="#second" data-toggle="collapse" data-parent="#parent">
                                    上师管理
                                </a>
                            </h3>
                        </center>
                    </div>

                    <div class="panel-collapse collapse" id="second">
                        <div class="panel-body">
                            <center>
                                <button class="btn btn-danger">
                                    上师管理
                                </button>
                            </center>
                        </div>
                    </div>
                </div>

                <!--
                    文章
                -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <center>
                            <h3 class="panel-title">
                                <a href="#third" data-toggle="collapse" data-parent="#parent">
                                    文章管理
                                </a>
                            </h3>
                        </center>
                    </div>
                    <div class="panel-collapse collapse" id="third">
                        <div class="panel-body">
                            <center>
                                <a class="btn btn-danger" href="javascript:$('#content').load('article.jsp')">
                                    文章
                                </a>
                            </center>
                        </div>
                    </div>
                </div>

                <!--专辑-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <center>
                            <h3 class="panel-title">
                                <a href="#zj" data-toggle="collapse" data-parent="#parent">
                                    专辑管理
                                </a>
                            </h3>
                        </center>
                    </div>
                    <div class="panel-collapse collapse" id="zj">
                        <div class="panel-body">
                            <center>
                                <a class="btn btn-danger" href="javascript:$('#content').load('album.jsp')">
                                    专辑
                                </a>
                            </center>
                        </div>
                    </div>
                </div>
                <!--轮播图-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <center>
                            <h3 class="panel-title">
                                <a href="#lbt" data-toggle="collapse" data-parent="#parent">
                                    轮播图管理
                                </a>
                            </h3>
                        </center>
                    </div>
                    <div class="panel-collapse collapse" id="lbt">
                        <div class="panel-body">
                            <center>
                                <a class="btn btn-danger" href="javascript:$('#content').load('banner.jsp')">
                                    轮播图
                                </a>
                            </center>
                        </div>
                    </div>
                </div>
            </div>


        </div>

        <!-- 右栏 -->
        <div class="col-sm-9 " id="content">
            <div class="jumbotron">
                <h2>欢迎来到持明法州管理系统</h2>
            </div>
            <div class="container-fluid">
                <div class="container col-sm-12">
                    <div class="carousel slide" id="slidershow" data-ride="carousel" data-interval="2000">
                        <!--计数器-->
                        <ol class="carousel-indicators">
                            <li class="active" data-target="#slidershow" data-slide-to="0"></li>
                            <li data-target="#slidershow" data-slide-to="1"></li>
                            <li data-target="#slidershow" data-slide-to="2"></li>
                        </ol>
                        <!--图片容器-->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img style="height: 600px;width: 100%" src="${pageContext.request.contextPath}/img/shouye.jpg"/>
                                <!--添加对应标题和内容-->
                                <div class="carousel-caption">
                                </div>
                            </div>
                            <div class="item">
                                <img style="height: 600px;width: 100%" src="${pageContext.request.contextPath}/img/A2.jpg"/>
                                <!--添加对应标题和内容-->
                                <div class="carousel-caption">

                                </div>
                            </div>
                            <div class="item">
                                <img style="height: 600px;width: 100%" src="${pageContext.request.contextPath}/img/shouye.jpg"/>
                                <!--添加对应标题和内容-->
                                <div class="carousel-captio">
                                </div>
                            </div>
                            <!--轮播导航-->
                            <a href="#slidershow" data-slide="prev" class="left carousel-control" role="button">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            </a>
                            <a href="#slidershow" data-slide="next" class="right carousel-control" role="button">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
    <div class="well well-lg">
        <center>@百知教育 baizhi @zaprkhr.com.cn</center>
    </div>

</div>
</body>
</html>
