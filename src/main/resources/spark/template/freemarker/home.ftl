<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>

  <div class="page">
  
    <h1>Web Checkers</h1>
    
    <div class="navigation">
      <a href="/">My Home</a>
    </div>

      <#if isSignedIn == false>
          <div class="navigation">
              <a href="/signin">Sign In</a>
          </div>

      <#else>
          <div class="navigation">
              <a href="/signout">Sign Out</a>
          </div>

          <strong>Current Player: </strong> ${currentPlayer}<br><br>
          <strong>Players Currently Online</strong><br>
          <#list players as username, value>
              <input type="button" class="btn btn-default" value=${username} /><br>
              <#else>
              No players online :( <br>
          </#list>
      </#if>
  </div>

</body>
</html>