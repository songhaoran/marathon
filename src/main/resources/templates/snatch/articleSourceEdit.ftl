<#include "/base/head.ftl">
<title>爬虫详情</title>
<link rel="stylesheet" href="${base}/static/css/index.css" media="all"/>
</head>
<body class="childrenBody" style="margin-top: 20px">
<form id="data_form" class="layui-form layui-row layui-col-space10">
    <input id="source_id" type="hidden" name="id" value="${obj.id}">
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" placeholder="请输入" autocomplete="off" class="layui-input"
                   lay-verify="required" value="${obj.name}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类别</label>
        <div>
            <input type="radio" name="sourceType" value="0" title="微信" <#if obj.sourceType==0>checked</#if>
                   lay-verify="required">
            <input type="radio" name="sourceType" value="1" title="其他" <#if obj.sourceType==1>checked</#if>
                   lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">路径</label>
        <div class="layui-input-block">
            <input type="text" name="snatchUrl" placeholder="请输入(例09:55)" autocomplete="off" class="layui-input"
                   value="${obj.snatchUrl}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">定时爬取时间</label>
        <div class="layui-input-block">
            <input type="text" name="snatchTime" placeholder="请输入"
                   class="layui-input" lay-verify="required" value="${obj.snatchTime}">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button lay-filter="addNews" class="layui-btn layui-btn-green" lay-submit>立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        <#--<button id="to_list_id" class="layui-btn layui-btn-primary">返回</button>-->
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/lib/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${base}/static/lib/layui/layui.all.js"></script>
<script>
    var form = layui.form;
    var layer = layui.layer;
    form.on('submit(addNews)', function (data) {
        console.log(data.field)//当前容器的全部表单字段，名值对形式：{name: value}
        var loadIndex = layer.load();
        $.ajax({
            type: 'POST',
            url: "${base}/ultron/snatch",
            data: JSON.stringify(data.field),
            dataType: 'json',
            contentType: 'application/json',
            success: function (resultData) {
                layer.close(loadIndex);
                parent.$("#result").val(1);
                if (1 == resultData.code) {
                    layer.msg("添加成功!", {icon: 1, time: 2000});
                    $("#source_id").val(resultData.body.id);
                } else {
                    layer.msg(resultData.msg, {icon: 5, time: 2000});
                }
            },
            error: function () {
                layer.close(loadIndex);
                layer.msg('服务器忙，请稍后再试!!', {icon: 5, time: 2000});
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    $(function () {
        $('#to_list_id').click(function () {
            var url = "${base}/ultron/snatch/list?page_num=1&page_size=10";
            window.open(url, '_self');
        });
    });
</script>
</body>
</html>