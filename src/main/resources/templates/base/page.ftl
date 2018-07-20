<div style="margin-top:5px;margin-bottom: 10px; text-align:center;">
    <div class="laypage_main laypageskin_default">
    <#if page.pageNum != 1 && page.pageNum != 0>
        <a href="javascript:;" class="laypage_prev"
           onclick="changePage(<#if page.pageNum == 1>1<#else>${page.pageNum -1}</#if>);">上一页</a>
        <a href="javascript:;" class="laypage_first" title="首页" onclick="changePage(1);">首页</a>
    </#if>

    <#list page.navigatepageNums as navNum>
        <#if page.pageNum == navNum>
            <span class="laypage_curr">${navNum}</span>
        <#else >
            <a href="javascript:;" onclick="changePage(${navNum});">${navNum}</a>
        </#if>
    </#list>
    <#if page.pageNum != page.pages>
        <a href="javascript:;" class="laypage_last" title="尾页"
           onclick="changePage(${page.pages});">尾页</a>
        <a href="javascript:;" class="laypage_next"
           onclick="changePage(<#if page.pageNum == page.pages>${page.pageNum}<#else>${page.pageNum +1}</#if>);">下一页</a>
    </#if>
    </div>
</div>
