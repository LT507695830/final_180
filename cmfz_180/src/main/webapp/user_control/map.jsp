<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/echarts/china.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script>
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-f203c05bb195458bb2f1c31ecb7edac9", //替换为您的应用appkey
        });

        $(function () {
            // 基于准备好的dom，初始化echarts实例
            // 参数必须为 js
            var myChart = echarts.init($("#main")[0]);

            // 指定图表的配置项和数据
            option = {
                title : {
                    text: '用户地理分布图',
                    left: 'center'
                },
                tooltip : {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data:['人数']
                },
                visualMap: {
                    min: 0,
                    max: 2500,
                    left: 'left',
                    top: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : true
                },
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    left: 'right',
                    top: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [
                    {
                        name: '人数',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:[

                        ]
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            //动态数据   必须连后台(ajax)
            $.ajax({
                url:"${pageContext.request.contextPath}/User/getMapData",
                datatype:"json",
                success:function (data) {
                    console.log(data);
                    myChart.setOption({
                        series : [
                            {
                                data:data
                            }
                        ]
                    })
                }
            })

            goEasy.subscribe({
                channel: "liutao", //替换为您自己的channel
                onMessage: function (message) {
                    /*alert("Channel:" + message.channel + " content:" + message.content);*/
                    console.log(message.content);

                    myChart.setOption({
                        series: [{
                            data: JSON.parse(message.content)
                        }]
                    })
                }
            });

        })
    </script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>