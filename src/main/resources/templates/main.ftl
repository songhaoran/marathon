<#include "/base/head.ftl">
<link rel="stylesheet" href="${base}/static/css/index.css" media="all"/>
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote layui-bg-green">
    <div id="nowTime"></div>
</blockquote>
<div class="layui-row layui-col-space10 panel_box">
<#--<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">-->
<#--<a href="javascript:;" &lt;#&ndash;data-url="page/user/userList.ftl"&ndash;&gt;>-->
<#--<div class="panel_icon layui-bg-orange">-->
<#--<i class="layui-anim seraph icon-icon10" data-icon="icon-icon10"></i>-->
<#--</div>-->
<#--<div class="panel_word userAll">-->
<#--<span></span>-->
<#--<em>用户总数</em>-->
<#--<cite class="layui-hide">用户中心</cite>-->
<#--</div>-->
<#--</a>-->
<#--</div>-->
<#--<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">-->
<#--<a href="javascript:;" &lt;#&ndash;data-url="page/systemSetting/icons.html"&ndash;&gt;>-->
<#--<div class="panel_icon layui-bg-cyan">-->
<#--<i class="layui-anim layui-icon" data-icon="&#xe857;">&#xe857;</i>-->
<#--</div>-->
<#--<div class="panel_word outIcons">-->
<#--<span></span>-->
<#--<em>外部图标</em>-->
<#--<cite class="layui-hide">图标管理</cite>-->
<#--</div>-->
<#--</a>-->
<#--</div>-->
<#--<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">-->
<#--<a href="javascript:;">-->
<#--<div class="panel_icon layui-bg-blue">-->
<#--<i class="layui-anim seraph icon-clock"></i>-->
<#--</div>-->
<#--<div class="panel_word">-->
<#--<span class="loginTime"></span>-->
<#--<cite>上次登录时间</cite>-->
<#--</div>-->
<#--</a>-->
<#--</div>-->
</div>
<blockquote class="layui-elem-quote main_btn">
    <p>财富云后台管理系统</p>
</blockquote>
<div class="layui-row layui-col-space10">

    <div class="layui-form-item">
        <label class="layui-form-label">理财师id</label>
        <div class="layui-input-block">
            <input id="advisor_id" type="text" required lay-verify="required" placeholder="必填" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="authorization_id" class="layui-btn" lay-submit lay-filter="formDemo">授权</button>
        </div>
    </div>

</div>

<script type="text/javascript" src="${base}/static/lib/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${base}/static/lib/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/layui/main.js"></script>
</body>
</html>