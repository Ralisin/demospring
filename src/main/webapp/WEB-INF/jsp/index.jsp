<body>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="scriptExample.js"></script>
    <title>Example JSP and JS</title>

    <style>
        body {
            margin: 0;
            /*display: flex;*/
            height: 100vh;
            flex-direction: column;
        }

        pre {
            margin: 0;
            background-color: #f9f9f9;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .container {
            display: flex;
        }

        .divider {
            width: 2px;
            background-color: #000; /* Linea divisoria */
        }

        .subcol {
            padding-left: 10px;
            padding-right: 10px;
            flex-direction: column;
        }
    </style>
</head>

<h2>Login (return new ModelAndView)</h2> <!-- Simple login form with POST method -->
<form action="login" method="post">
    <label for="username">Username:</label>
    <!-- Input field for username -->
    <input type="text" id="username" name="username"><br><br>

    <label for="password">Password:</label>
    <!-- Input field for password -->
    <input type="password" id="password" name="password"><br><br>

    <!-- Submit button to trigger form submission -->
    <input type="submit" value="Submit">
</form>
<!-- Error message display if there are issues during login -->
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<hr>
<%-- Send GET and send GET and elaborate output --%>

<!-- Button to trigger a GET request -->
<button onclick="doGet()">Send GET</button>
<button onclick="doClear()">Erase output</button>
<p></p>
<!-- Button to trigger a GET request and process the output (e.g., display a table) -->
<button onclick="doGetAndElaborateOutput()">Send GET and elaborate output</button>
<label for="dogName">Search dog by name:</label>
<!-- Input field to search for a dog by name -->
<input type="text" id="dogName" name="dogName">
<p id="responseToGet"
   style="max-width: 100%; overflow-x: scroll; white-space: nowrap; scrollbar-width: none; -ms-overflow-style: none;"></p>
<!-- Placeholder to display GET request response -->

<hr>
<%-- Create dog section --%>

<div class="container">
    <script>
        window.onload = function() {
            sessionStorage.removeItem('authToken');
        };
    </script>

    <div class="subcol">
        <h2>Create a new dog</h2>
        <!-- Form to create a new dog -->
        <form id="createDogForm">
            <label for="newDogName">Dog Name:</label>
            <!-- Input field for the new dog's name -->
            <input type="text" id="newDogName" name="newDogName" required><br><br>

            <label for="dogOwner">Select Owner:</label>
            <!-- Dropdown menu to select the dog's owner; options will be loaded dynamically -->
            <select id="dogOwner" name="dogOwner" required>
                <!-- The owners will be loaded dynamically here -->
            </select><br><br>

            <!-- Button to trigger dog creation -->
            <button type="button" onclick="createDog()">Create Dog</button>
        </form>

        <p id="createDogResponse"></p> <!-- Placeholder to display the result of the dog creation request -->
    </div>
    <div class="divider"></div>
    <div class="subcol">
        <h2>Delete a dog</h2>

        <form id="deleteDogForm">
            <label for="deleteDogId">Dog Id:</label>
            <!-- Input field for the new dog's name -->
            <input type="text" id="deleteDogId" name="newDogName" required><br><br>

            <!-- Button to trigger dog creation -->
            <button type="button" onclick="deleteDog()">Delete Dog</button>
        </form>

        <p id="deleteWrongResponse" style="color:red"></p>
        <p id="deleteRightResponse" style="color:green"></p>
    </div>
    <div class="divider"></div>
    <div class="subcol">
        <h2>Login (stay on same page, return a token)</h2>
        <form id="loginWithTokenForm">
            <label for="usrToken">Username:</label>
            <!-- Input field for the username -->
            <input type="text" id="usrToken" name="usr" required><br><br>

            <label for="pwdToken">Password:</label>
            <!-- Input field for user password -->
            <input type="password" id="pwdToken" name="pwd" required><br><br>

            <!-- Button to trigger dog creation -->
            <button type="button" onclick="doLoginToken()">LoginWithToken</button>
        </form>

        <p id="loginWithTokenWrongResponse" style="color:red"></p>
        <p id="loginWithTokenRightResponse" style="color:green"></p>
    </div>
</div>

</body>
