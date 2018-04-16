<!DOCTYPE html>
<body>

<img src=${imageSrc} height="100%" width="100%" style="vertical-align: middle">

<br><br><br>

<#if validMoveStrings??>
    <strong>Recommended Valid Moves</strong><br>(row, col) to (row, col)<br><br>
    <#list validMoveStrings as validMoveString>
          ${validMoveString}<br>
    </#list>
</#if>

</body>
</html>