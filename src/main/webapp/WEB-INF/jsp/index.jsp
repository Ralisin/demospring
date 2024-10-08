<body>
<head>
    <script src="scriptEsempio.js"></script>
    <title>Esempio JSP e JS</title>

</head>
<h2>Login</h2>
<form action="login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br><br>
    <input type="submit" value="Submit">
</form>

<button onclick="effettuaGet()">Invia GET</button>
<p id="rispostaAllaGet"></p>
<button onclick="effettuaGetEdElaboraOutput()">Invia GET ed elabora output</button>
<p id="rispostaAllaGetElaborata"></p>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
</body>
