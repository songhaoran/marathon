<#include "/base/head.ftl">
<title>爬虫列表</title>
<link rel="stylesheet" href="${base}/static/css/index.css" media="all"/>
</head>
<body class="childrenBody" style="margin-top: 20px">
<div class="layui-row">
    <div class="layui-col-xs1 layui-col-xs-offset11">
        <button id="addnew" class="layui-btn">
            <i class="layui-icon">&#xe608;</i> 添加
        </button>
    </div>
</div>
<table class="layui-table" lay-even>
    <thead>
    <tr>
        <th>id</th>
        <th>名称</th>
        <th>类别</th>
        <th>爬取时间(天)</th>
        <th>上次爬取时间</th>
        <th>是否启用</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#list page.list as obj>
    <tr>
        <td>${obj.id}</td>
        <td>${obj.name}</td>
        <td>
            <#if obj.sourceType==0>微信公众号
            <#elseif obj.sourceType==1>其他
            </#if>
        </td>
        <td>${obj.snatchTime}</td>
        <td>
            <#if obj.lastSnatchTime?exists>
                ${obj.lastSnatchTime?string("yyyy-MM-dd mm:hh:ss")}
            </#if>
        </td>
        <td>
            <#if obj.isActive==false>
                停用
            <#elseif obj.isActive==true>
                启用
            </#if>
        </td>
        <td>
            <a href="${base}/ultron/snatch/edit?source_id=${obj.id}" title="编辑">
                <i class="layui-icon">&#xe642;</i>
            </a>
            &nbsp;&nbsp;
            <#if obj.isActive == false>
                <a title="启用" onclick="changeActive(${obj.id},true)" href="javascript:;">启用</a>
            <#elseif obj.isActive==true>
                <a title="停用" onclick="changeActive(${obj.id},false)" href="javascript:;">停用</a>
            </#if>
            &nbsp;&nbsp;
            <a onclick="toDel(${obj.id})" href="javascript:;" title="删除">
                <i class="layui-icon">&#xe640;</i>
            </a>
            &nbsp;&nbsp;
            <a onclick="toSnatch(${obj.id})" href="javascript:;" title="爬取">
                <i class="layui-icon">&#xe623;</i>
            </a>

        </td>
    </tr>
    </#list>
    </tbody>
</table>
<div id="paging_id"></div>

</body>
<script type="text/javascript" src="${base}/static/lib/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${base}/static/lib/layui/layui.all.js"></script>
<script type="text/javascript">

    layui.laypage.render({
        elem: 'paging_id'
        , count: ${page.total}
        , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
        , limit:${page.pageSize}
        , curr:${page.pageNum}
        , jump: function (obj, first) {
            if (!first) {
                console.log("obj:" + obj);
                window.open("/ultron/snatch/list?page_num=" + obj.curr + "&page_size=" + obj.limit, '_self');
            }
        }
    });

    $(function () {
        $('#addnew').click(function () {
            var url = "${base}/ultron/snatch/create";
            window.open(url, '_self');
        });
    });

    function toDel(sourceId) {
        layer.confirm('确认要删除吗？', function (index) {
            var loadIndex = layer.load();
            $.ajax({
                type: 'delete',
                url: "${base}/ultron/snatch",
                data: JSON.stringify({source_id: sourceId}),
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    layer.close(loadIndex);
                    if (0 == data.code) {
                        location.reload();
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 2000});
                    }
                },
                error: function () {
                    layer.close(loadIndex);
                    layer.msg('服务器忙，请稍后再试');
                }
            });
            //关闭弹窗
            var index = parent.layer.getFrameIndex(window.name);
            layer.close(index);
        });
    }

    function toSnatch(sourceId) {
        layer.confirm('确认要开始爬取吗？', function (index) {
            var loadIndex = layer.load();
            $.ajax({
                type: 'get',
                url: "${base}/ultron/snatch/start?sourceIdList=" + sourceId,
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    layer.close(loadIndex);
                    if (1 == data.code) {
                        layer.msg("已开始爬取", {icon: 3, time: 2000});
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 2000});
                    }
                },
                error: function () {
                    layer.close(loadIndex);
                    layer.msg('服务器忙，请稍后再试');
                }
            });
            //关闭弹窗
            var index = parent.layer.getFrameIndex(window.name);
            layer.close(index);
        });
    }

    function changeActive(sourceId, status) {
        var confirmStr;
        console.log(status);
        if (status == true) {
            confirmStr = "确认要启用吗?";
        } else {
            confirmStr = "确认要停用吗?";
        }
        layer.confirm(confirmStr, function (index) {
            var loadIndex = layer.load();
            $.ajax({
                type: 'post',
                url: "${base}/ultron/snatch/active",
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify({source_id: sourceId, is_active: status}),
                success: function (data) {
                    layer.close(loadIndex);
                    if (1 == data.code) {
                        location.reload();
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 2000});
                    }
                },
                error: function () {
                    layer.close(loadIndex);
                    layer.msg('服务器忙，请稍后再试');
                }
            });
            //关闭弹窗
            var index = parent.layer.getFrameIndex(window.name);
            layer.close(index);
        });
    }

</script>
</html>