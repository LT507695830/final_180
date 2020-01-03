<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>

<style>
    .ui-jqgrid .ui-userdata {
        padding: 13px 94px;
        overflow: hidden;
        min-height: 32px;
    }

    .modal-body {
        position: relative;
        padding: 21px 130px;
    }
    #myH22{
        margin-top: -42px;
        margin-bottom: 10px;
    }
</style>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${pageContext.request.contextPath}/Album/queryByPager",
            editurl: "${pageContext.request.contextPath}/Album/edit", //写操作请求地址
            styleUI: "Bootstrap",
            datatype: "json",
            autowidth: true,
            records: true,
            rowNum: 2,
            rowList: [2, 3, 4],
            height: 400,
            caption: "专辑",
            pager: "#albumPager",
            colNames: [
                "id", "title", "img", "score", "author", "broadcaster", "count", "brief", "create_date", "status"
            ],
            colModel: [
                {name: "id"},
                {name: "title", editable: true}, //名字可以更改
                {
                    name: "img", editable: true, edittype: 'file',
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img style='width:100%;height:100px' src='${pageContext.request.contextPath}/upload/video/img/" + cellvalue + "'/>";
                    }
                }, //图片可以更改
                {name: "score", editable: true}, //评分可以更改
                {name: "author", editable: true,},//作者可以更改
                {name: "broadcaster", editable: true},//播音员可以更改
                {name: "count"},//数量不能更改，随改专辑下的音频数量自增长
                {name: "brief", editable: true,edittype:'textarea'},//简介可以更改
                {name: "create_date"},//时间为默认系统时间
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
                }//专辑状态可以更改
            ],
            subGrid: true,         //开启子表格
            subGridRowExpanded: function (subGridId, albumId) {
                //添加子表格的方法
                addSubGrid(subGridId, albumId);
            }
        }).jqGrid("navGrid", "#albumPager", {search: false}, {}, {

            closeAfterAdd: true,
            afterSubmit: function (response) {
                var id = response.responseJSON.albumID;
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/Album/AlbumUpload",
                    fileElementId: 'img',
                    data: {albumID: id},
                    type: "post",
                    success: function () {
                        $("#albumList").trigger("reloadGrid");
                        $("#msgDiv").html("添加成功")
                        $("#msgDiv").show();
                        setTimeout(function () {
                            $("#msgDiv").hide();
                        }, 3000)
                    }
                })
                return response;
            }
        }, {})
    })

    //添加子表格
    function addSubGrid(subGridId, albumId) {
        //动态table  id
        var subGridTableId = subGridId + "table";
        //动态div id
        var subGridDivId = subGridId + "div";
        //动态添加子表格
        $("#" + subGridId).html("<table id='" + subGridTableId + "'></table>" +
            "<div id='" + subGridDivId + "' style='height: 50px'></div>"
        )
        $("#" + subGridTableId).jqGrid({
            url: "${pageContext.request.contextPath}/Chapter/queryByPager" + "?albumId=" + albumId, //查询出对应专辑下的所有音频
            styleUI: "Bootstrap",
            editurl: "${pageContext.request.contextPath}/Chapter/edit" + "?albumId=" + albumId, //写操作请求地址
            datatype: "json",
            autowidth: true,
            records: true,
            rowNum: 5,
            caption: "章节",
            toolbar: [true, "top"],
            pager: "#" + subGridDivId,
            rowList: [5, 7, 9],
            colNames: [
                "id", "title", "size", "src", "duration", "status"
            ],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "size"},
                {
                    name: "src", editable: true, edittype: 'file',
                    formatter: function (cellvalue, options, rowObject) {
                        return "<audio style='width:100%;height:50px'  controls='controls' src='${pageContext.request.contextPath}/upload/video/" + cellvalue + "'/>";
                        ;
                    }
                },
                {name: "duration"},
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
        }).jqGrid("navGrid", "#" + subGridDivId, {search: false}, {
            closeAfterEdit: true,
            afterSubmit: function (response) {
                var id = response.responseJSON.chapter_id;
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/Chapter/ChapterUpload",
                    fileElementId: 'src',
                    data: {chapter_id: id},
                    type: "post",
                    success: function () {
                        $("#" + subGridDivId).trigger("reloadGrid");
                        $("#msgDiv").html("修改成功")
                        $("#msgDiv").show();
                        setTimeout(function () {
                            $("#msgDiv").hide();
                        }, 3000)
                    }
                })
                return response;
            }
        }, {
            closeAfterAdd: true,
            afterSubmit: function (response) {
                var id = response.responseJSON.chapter_id;
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/Chapter/ChapterUpload",
                    fileElementId: 'src',
                    data: {chapter_id: id},
                    type: "post",
                    success: function () {
                        $("#" + subGridDivId).trigger("reloadGrid");
                        $("#msgDiv").html("添加成功")
                        $("#msgDiv").show();
                        setTimeout(function () {
                            $("#msgDiv").hide();
                        }, 3000)
                    }
                })
                return response;
            }


        }, {})

        //添加按钮
        $("#t_" + subGridTableId).html("<button class='btn btn-danger' onclick=\"play('" + subGridTableId + "')\">播放 <span class='glyphicon glyphicon-play'></span></button>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<button class='btn btn-danger'  onclick=\"download('" + subGridTableId + "')\">下载 <span class='glyphicon glyphicon-arrow-down'></span></button>"
        )
    }

    //播放
    function play(subGridTableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
            var gr = $("#" + subGridTableId).jqGrid('getGridParam', 'selrow');
            if (gr == null) {
                alert("请选中要播放的音频");
            } else {
                //1.请求后台
                //2.jqgrid 提供的方法 根据id拿到对应的值
                var data = $("#" + subGridTableId).jqGrid('getRowData', gr);
                console.log(data.src)
                $('#myModal').modal('show');
            $("#myAudio").html(data.src);
        }
    }

    function download(subGridTableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#" + subGridTableId).jqGrid('getGridParam', 'selrow');
        if (gr == null) {
            alert("请选中要下载的音频");
        } else {
            console.log(gr)
            var chapter_id = $("#" + subGridTableId).jqGrid('getRowData', gr);

            location.href = "${pageContext.request.contextPath}/Chapter/ChapterDownload?chapter_id=" + gr;
            alert("liuta")
        }
    }
</script>
<div class="page-header">
    <h2 id="myH22">专辑管理</h2>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑信息</a>
    </li>
</ul>
<div class="tab-content">
    <table id="albumList"></table>
</div>
<div id="albumPager" style="height: 50px"></div>
<div class="alert alert-success" style="display:none" id="msgDiv" style="text-align: center ">

</div>

<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">播放器</h4>
            </div>
            <div class="modal-body" id="myAudio">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>