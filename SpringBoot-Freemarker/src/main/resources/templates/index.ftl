<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Demo</title>
    <script src="/static/main.js"></script>
</head>

<body>
<#-- 注释 取出变量 -->
Hello ${name}

<#-- if判断-->
<#if name == "Tom">
    <span>is Tom</span>
<#elseif name == "Jack">
    <span>is Jack</span>
<#else>
    <span>not is Tom</span>
</#if>

<#--for循环-->
<#list list as item>
    <p>${item.name} ${item.age}</p>
</#list>

<#-- 引入模板 -->
<#include "./footer.html">
</body>

</html>