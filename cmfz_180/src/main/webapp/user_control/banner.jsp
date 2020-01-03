<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>


<script>
    $(function () {
        $("#bannerList").jqGrid({
            url: "${pageContext.request.contextPath}/Banner/queryByPager", //查询数据的请求地址
            editurl: "${pageContext.request.contextPath}/Banner/edit", //写操作请求地址
            datatype: "json", //数据的形式
            styleUI: "Bootstrap",
            colNames: ["id", "title", "img", "create_date", "status"],
            pager: "#bannerPager", //分页展示
            rowNum: 2, //每页展示条数
            rowList: [2, 3, 4],//分页展示可选的展示条数
            viewrecords: true,                   //是否显示总记录数
            autowidth: true,                     //自适应父容器
            multiselect: true,                   //是否多选
            height: '400px',
            colModel: [
                {name: "id"},
                {name: "title", editable: true},//editable':true ; 是否可以编辑  edittype:'file'：指定添加的格式
                {
                    name: "img", editable: true, edittype: 'file',
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img style='width:100%;height:100px' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'/>";
                    }
                },
                {name: "create_date", editable: true, edittype: 'date'},
                {
                    name: "status", editable: true, edittype: "select", editoptions: {
                        value: "0:激活;1:冻结"
                    },
                    formatter: function (cellvalue, options, rowObject) {

                        if (cellvalue == 0) {
                            return "激活";
                        } else {
                            return "冻结";
                        }

                    }
                }
            ]
        }).jqGrid("navGrid", "#bannerPager", {search: false}, {
            /*
              * 修改
              * */


            afterSubmit: function (response) {
                var id = response.responseJSON.bannerId;
                alert(id)
                if(id==null){
                    id="";
                    alert(id)
                }
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/Banner/bannerUpload",
                    fileElementId: 'img',
                    data: {bannerId: id},
                    type: "post",
                    success: function () {
                        $("#msgDiv").html("修改成功")
                        $("#msgDiv").show();
                        setTimeout(function () {
                            $("#msgDiv").hide();
                        }, 3000)
                    }
                })
                return response;
            }, closeAfterEdit: true


        }, {

            /*
              * 添加
              * */

            afterSubmit: function (response) {
                var id = response.responseJSON.bannerId;
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/Banner/bannerUpload",
                    fileElementId: 'img',
                    data: {bannerId: id},
                    type: "post",
                    success: function () {
                        $("#bannerList").trigger("reloadGrid");
                        $("#msgDiv").html("添加成功")
                        $("#msgDiv").show();
                        setTimeout(function () {
                            $("#msgDiv").hide();
                        }, 3000)
                    }
                })
                return response;
            },
            closeAfterAdd: true
        }, {
            closeAfterDel: true

        })


    })


</script>


<table id="bannerList"></table>
<div id="bannerPager" style="height: 50px"></div>
<div class="alert alert-success" style="display:none" id="msgDiv" style="text-align: center ">

</div>