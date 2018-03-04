<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="2">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>

  <div class="page">
  
    <h1>Web Checkers</h1>
    
    <div class="navigation">
      <a href="/">My Home</a>
    </div>
      <#--is Signed Out-->
      <#if isSignedIn == false>
          <div class="navigation">
              <a href="/signin">Sign In</a>
          </div><br><br>
          <strong> Number of other players currently online :</strong> ${players?size}

      <#else>
          <div class="navigation">
              <a href="/signout">Sign Out</a>
          </div>

          <strong>Current Player: </strong> ${currentPlayer}<br><br>
          <strong>Other Players Currently Online</strong><br>
          <#list players as username, value>

                <form action="/board" method="GET">
                    <form action = "" method = "get">
                        <input type="submit" class="btn btn-default" name=${username} value=${username} /><br>
                    </form>
                </form>

              <#else>
              No other players online :( <br>
          </#list>
      </#if >
  </div>

</body>
</html>