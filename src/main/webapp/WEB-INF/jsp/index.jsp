<body>
<head>
    <script src="scriptExample.js"></script>
    <title>Example JSP e JS</title>

</head>
<h2>Login</h2>
<form action="login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br><br>
    <input type="submit" value="Submit">
</form>

<button onclick="doGet()">Send GET</button>
<p id="responseToGet"></p>

<br><br>
<label for="username">Search dog by name:</label>
<input type="text" id="dogName" name="dogName">
<br><br>

<button onclick="doGetAndElaborateOutput()">Send GET and elaborate output</button>


<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
</body>
