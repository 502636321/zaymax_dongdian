<#macro pageable data url>
<nav aria-label="Page navigation" class="text-center">
    <#if (data.totalPages > 1) >
        <ul class="pagination">
        <#--判断是否是第一页-->
            <#if data.isFirst() >
                <li><a>&laquo;</a></li>
            <#else >
                <li><a href="${ url }?page=0">&laquo;</a></li>
            </#if>
            <#assign index = 0 />
            <#list (data.number - 5)..data.number as number >
                <#if (number >= 0) >
                    <#assign index = (index + 1) />
                    <li <#if ( number == data.number ) >class="active"</#if>><a
                            href="${ url }?page=${ number }">${ (number + 1) }</a></li>
                </#if>
            </#list>
            <#list (data.number + 1)..(data.number + (10 - index)) as number >
                <#if (number < data.totalPages) >
                    <#assign index = (index + 1) />
                    <li <#if ( number == data.number ) >class="active"</#if>><a
                            href="${ url }?page=${ number }">${ (number + 1) }</a></li>
                </#if>
            </#list>

        <#--判断是否是最后一页-->
            <#if data.isLast() >
                <li><a>&raquo;</a></li>
            <#else >
                <li><a href="${ url }?page=${ (data.totalPages - 1) }">&raquo;</a></li>
            </#if>
        </ul>
    </#if>
</nav>
</#macro>