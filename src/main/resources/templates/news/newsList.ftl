<#include "/base/head.ftl">
<title>文章列表</title>
<link rel="stylesheet" href="${base}/static/css/index.css" media="all"/>
</head>
<body class="childrenBody" style="margin-top: 20px">
<from action="${base}/back/article/page" id="search_form_id" class="layui-form" method="post">
    <input id="page_num_id" type="hidden" name="page_num">
    <input id="page_size_id" type="hidden" name="page_size">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">文章类型</label>
            <div class="layui-input-inline">
                <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">创建类型</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">来源id</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">创建者</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
</from>
<#--<div class="layui-row">-->
<#--<div class="layui-col-xs1 layui-col-xs-offset11">-->
<#--<button id="addnew" class="layui-btn">-->
<#--<i class="layui-icon">&#xe608;</i> 添加-->
<#--</button>-->
<#--</div>-->
<#--</div>-->
<table class="layui-table" lay-even>
    <thead>
    <tr>
        <th>id</th>
        <th>标题</th>
        <th>创建类型</th>
        <th>类别</th>
        <th>阅读量</th>
        <th>发布时间</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#list page.list as obj>
    <tr>
        <td>${obj.id}</td>
        <td>${obj.title}</td>
        <td>
            <#if obj.foundType==0>系统抓取
            <#elseif obj.foundType==1>理财师原创
            <#elseif obj.foundType==2>后台编辑
            </#if>
        </td>
        <td>${obj.tagName}</td>
        <td>${obj.pv}</td>
        <td>
            <#if obj.createdAt?exists>
                ${obj.createdAt?string("yyyy-MM-dd mm:hh:ss")}
            </#if>
        </td>
        <td>
            <#if obj.status==0>待发布
            <#elseif obj.status==1>发布
            <#elseif obj.status==2>下架
            </#if>
        </td>
        <td>
            <a href="${base}/back/article/${obj.id}" title="编辑">
                <i class="layui-icon">&#xe642;</i>
            </a>
            &nbsp;&nbsp;
            <#if obj.status == 0||obj.status==2>
                <a title="上架" onclick="changeStatus(${obj.id},1)" href="javascript:;">上架</a>
            <#elseif obj.status == 1>
                <a title="下架" onclick="changeStatus(${obj.id},2)" href="javascript:;">下架</a>
            </#if>
            &nbsp;&nbsp;
            <a onclick="toDel(${obj.id})" href="javascript:;" title="删除">
                <i class="layui-icon">&#xe640;</i>
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
            <#--window.open("${base}/back/article/page?page_num=" + obj.curr + "&page_size=" + obj.limit, '_self');-->
                $("#page_num_id").val(obj.curr);
                $("#page_size_id").val(obj.limit);
                $("#search_form_id").submit();
            }
        }
    });

    $(function () {
        $('#addnew').click(function () {
            var url = "${base}/ultron/snatch/to_create";
            window.open(url, '_self');
        });
    });

    function toDel(articleId) {
        layer.confirm('确认要删除吗？', function (index) {
            var loadIndex = layer.load();
            $.ajax({
                type: 'delete',
                url: "${base}/back/article/" + articleId,
                data: {},
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

    function changeStatus(articleId, status) {
        var confirmStr;
        console.log(status);
        if (status == 1) {
            confirmStr = "确认要上架吗?";
        } else {
            confirmStr = "确认要下架吗?";
        }
        layer.confirm(confirmStr, function (index) {
            var loadIndex = layer.load();
            $.ajax({
                type: 'post',
                url: "${base}/back/article/status",
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify({article_id: articleId, status: status}),
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