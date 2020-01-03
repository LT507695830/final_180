<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script   src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>

    <%--2.jqgrid相关css--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--3.jquery核心js--%>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <%--5.jqgrid相关js--%>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>

    <script type="text/javascript">
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-f203c05bb195458bb2f1c31ecb7edac9", //替换为您的应用appkey
        });


        $(function () {
            // 基于准备好的dom，初始化echarts实例
            // 参数必须为 js
            var myChart = echarts.init($("#main")[0]);

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '近七天注册上师数'
                },
                tooltip: {},
                legend: {
                    data:['人数']
                },
                xAxis: {
                    data: ["day1","day2","day3","day4","day5","day6","day7"]
                },
                yAxis: {},
                series: [{
                    name: '人数',
                    type: 'bar'
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            //动态数据   必须连后台(ajax)
            $.ajax({
                url:"${pageContext.request.contextPath}/Guru/getData",
                datatype:"json",
                success:function (data) {
                    console.log(data);
                    myChart.setOption({
                        series: [{
                            data: data
                        }]
                    })
                }
            })
            goEasy.subscribe({
                channel: "liutao", //替换为您自己的channel
                onMessage: function (message) {
                    /*alert("Channel:" + message.channel + " content:" + message.content);*/
                    console.log(message.content);
                    var parse = JSON.parse(message.content);
                    console.log(parse);
                    myChart.setOption({
                        series: [{
                            data: parse
                        }]
                    })
                }
            });
        })
    </script>

</head>
<body>
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>