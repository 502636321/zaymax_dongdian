<#if ((success_msg!"")?length > 0) >
    <@spring.message code="alert_success" arguments='${ success_msg!"" }' />
</#if>
<#if ((error_msg!"")?length > 0) >
    <@spring.message code="alert_warning" arguments='${ error_msg!"" }' />
</#if>