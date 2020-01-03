<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>


<script>
    var kin;
    function motai() {
        kin = KindEditor.create('#editor_id', {
            width: '100%',
            height: '300px',
            minHeight: 400,
            resizeType: 0,
            allowFileManager: true,
            filePostName: 'img',
            uploadJson: '${pageContext.request.contextPath}/Kindeditor/uploadImg',
            fileManagerJson: "${pageContext.request.contextPath}/Kindeditor/getAllImgs"
        });
        return kin;

    }

    function articleUpdate() {



        var gr = $("#articleList").jqGrid('getGridParam', 'selrow');
        if(gr==null){
            alert("请选择一条数据");
        }else {
            var r = $("#articleList").jqGrid('getRowData', gr);

            alert(gr);






        }



    }

    $(function () {
        var motai1 = motai();
        $("#liutao").click(function () {
            $("#myModal").modal('hide');
            var cont = motai1.html();
            console.log(cont);
            /*  var contt = $("#content").val(cont);
              console.log(contt);
              var data = $("#addfrom").serialize();
              console.log(data);*/

            var title = $("#title").val();
            var status = $("#status").val();
            var author = $("#author").val();
            var guru_id = $("#guru_id").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/Article/edit?oper=add",
                data: {title: title, status: status, content: cont, author: author, guru_id: guru_id},
                type: "post",
                success: function () {
                    $("#title").val("");
                    $("#status").val("");
                    $("#author").val("");
                    $("#guru_id").val("");
                    $("#articleList").jqGrid().trigger("reloadGrid");
                }
            })

        })


        $("#articleList").jqGrid({
            url: "${pageContext.request.contextPath}/Article/queryByPager", //查询数据的请求地址
            editurl: "${pageContext.request.contextPath}/Article/edit", //写操作请求地址
            datatype: "json", //数据的形式
            styleUI: "Bootstrap",
            colNames: ["id", "title", "author", "content", "guru_id", "create_date", "status", "操作"],
            pager: "#articlePager", //分页展示
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
                    name: "author", editable: true, edittype: 'textarea'
                },
                {name: "content", editable: true},
                {name: "guru_id", editable: true},
                {name: "create_date", editable: true, edittype: 'date'},
                {
                    name: "status", editable: true, edittype: "select", editoptions: {
                        value: "0:展示;1:不展示"
                    },
                    formatter: function (cellvalue, options, rowObject) {

                        if (cellvalue == 0) {
                            return "展示";
                        } else {
                            return "不展示";
                        }

                    }
                }, {
                    name: "操作", formatter: function (cellvalue, options, rowObject) {

                        return "<a href='#' onclick='articleUpdate();'><span class='glyphicon glyphicon-th-list'></span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' data-toggle='modal' data-target='#myModal' ><span class='glyphicon glyphicon-pencil'></span></a>"

                    }
                }

            ]
        }).jqGrid("navGrid", "#articlePager", {add: false, edit: false, search: false}, {
            /*
              * 修改
              * */

            closeAfterEdit: true


        }, {

            /*
              * 添加
              * */


            closeAfterAdd: true
        }, {
            closeAfterDel: true

        })


    })


</script>
<a class="btn btn-default" data-toggle='modal' data-target='#myModa2'>打开</a>
<div class="page-header">
    <h2 id="myH22">文章管理</h2>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑信息</a>
    </li>
    <li role="presentation">
        <a href="#WZ" aria-controls="WZ" role="tab" data-toggle="modal" data-target="#myModal">添加文章</a>
    </li>
</ul>
<div class="tab-content">
    <table id="articleList"></table>
</div>
<div id="articlePager" style="height: 50px"></div>
<div class="alert alert-success" style="display:none" id="msgDiv" style="text-align: center ">

</div>


<div class="modal fade" role="dialog" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">编辑用户信息</h4>
            </div>
            <div class="modal-body">
                <form id="addfrom" action="javascript:void(0)">
                    <div class="form-group">
                        <label for="author">作者</label>
                        <input type="text" class="form-control" id="author" placeholder="作者">
                    </div>
                    <div class="form-group">
                        <label for="guru_id">上师ID</label>
                        <input type="text" class="form-control" id="guru_id" placeholder="上师ID">
                    </div>
                    <div class="form-group">
                        <label for="title">标题</label>
                        <input type="text" class="form-control" id="title" placeholder="标题">
                    </div>

                    <div class="form-group">
                        <label for="status">状态</label>
                        <select id="status" class="form-control">
                            <option value="0">展示</option>
                            <option value="1">不展示</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="content" id="content">
                        <textarea id="editor_id" style="width:700px;height:300px;">
                                <h1 id="ArticleContent">请输入内容</h1>
                        </textarea>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <a id="liutao" type="submit" class="btn btn-default" data-dismiss="modal">保存</a>
                <a type="button" class="btn btn-primary">退出</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>


<%--<script>
    $(function () {
        $("#liutao").click(function () {
            var kin = motai();
            alert(kin.html());
            var title = $("#title").val();
            var status = $("#status").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/Article/edit?oper=add",
                data:{title:title ,status:status },
                type: "post",
                success: function () {
                    alert("哈哈哈");
                }
            })

        })
    })
</script>--%>


