<body>
<#if role == 'ADMIN'>
    <#include "navigation_admin.ftlh">
</#if>
<#if role == 'USER'>
<#include "navigation_user.ftlh">
</#if>