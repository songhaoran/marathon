<#include "/base/head.ftl">
<title>文章详情</title>
<link rel="stylesheet" href="${base}/static/css/index.css" media="all"/>
</head>
<body class="childrenBody" style="margin-top: 20px">
<form id="data_form" class="layui-form layui-row layui-col-space10">
    <input id="article_id" type="hidden" name="id" value="${obj.id}">
    <input id="found_type_id" type="hidden" name="found_type" value="${obj.foundType}">
    <input id="found_type_id" type="hidden" name="status" value="${obj.status}">
    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-block">
            <input type="text" name="title" placeholder="请输入" autocomplete="off" class="layui-input"
                   lay-verify="required" value="${obj.title}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">列表图</label>
        <div class="layui-inline">
            <input type="hidden" id="list_img_id" name="list_img" value="${obj.listImg}">
            <img id="list_img_url_id"
                 src="<#if obj.listImg?exists>${obj.listImg}<#else>${base}/static/images/unknown.png</#if>"
                 style="height: 170px;width: 170px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">类别</label>
        <div class="layui-input-block">
            <input type="hidden" name="tag_id_list" id="tag_id_list_id">
            <#list articleTagList as tag>
                <input lay-filter="tagId" type="checkbox" name="tag_id_list[${tag_index}]" title='${tag.name}'
                       value="${tag.id}" ${obj.tagIdList?seq_contains(tag)?string("checked","")}>
            </#list>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">文章内容</label>
        <div class="layui-col-xs10" style="height: 400px;width: 1000px">
        <textarea class="layui-textarea" name="content" id="content_id" style="display: none">
        ${obj.content}
        </textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button lay-filter="add" class="layui-btn layui-btn-green" lay-submit>立即提交</button>
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
    var layedit = layui.layedit;
    var upload = layui.upload;

    layedit.set({
        uploadImage: {
            url: '${base}/back/layedit/upload/file' //接口url
            , type: 'post' //默认post
        }
    });

    var index = layedit.build('content_id'); //建立编辑器
    //执行实例
    var uploadInst = upload.render({
        elem: '#list_img_url_id' //绑定元素
        , url: '${base}/back/layedit/upload/file' //上传接口
        , done: function (res) {
            //上传完毕回调
            if (res.code == 0) {
                $("#list_img_id").val(res.data.src);
                $("#list_img_url_id").attr('src', res.data.src);
            } else {
                layer.msg(resultData.msg, {icon: 5, time: 2000});
            }
        }
        , error: function () {
            //请求异常回调
            layer.msg('服务器忙，请稍后再试!!', {icon: 5, time: 2000});
        }
    });

    form.on('checkbox(tagId)', function (data) {
        console.log(data.elem); //得到checkbox原始DOM对象
        console.log(data.elem.checked); //是否被选中，true或者false
        console.log(data.value); //复选框value值，也可以通过data.elem.value得到
        console.log(data.othis); //得到美化后的DOM对象


    });

    form.on('submit(add)', function (data) {
        console.log(data.field)//当前容器的全部表单字段，名值对形式：{name: value}
        var loadIndex = layer.load();
        $("#content_id").val(layedit.getContent(index));
        $.ajax({
            type: 'POST',
            url: "${base}/back/article",
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