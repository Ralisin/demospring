<body>
<head>
    <script src="scriptExample.js"></script>
    <title>Example JSP and JS</title>

    <style>
        pre {
            margin: 0;
            background-color: #f9f9f9;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>

<h2>Login</h2>
<!-- Simple login form with POST method -->
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
</body>
<hr>
<!-- Button to trigger a GET request -->
<button onclick="doGet()">Send GET</button>
<p></p>
<!-- Button to trigger a GET request and process the output (e.g., display a table) -->
<button onclick="doGetAndElaborateOutput()">Send GET and elaborate output</button>
<p id="responseToGet"
   style="max-width: 100%; overflow-x: scroll; white-space: nowrap; scrollbar-width: none; -ms-overflow-style: none;"></p> <!-- Placeholder to display GET request response -->

<hr>
<br>

<label for="dogName">Search dog by name:</label>
<!-- Input field to search for a dog by name -->
<input type="text" id="dogName" name="dogName">

<br><br>

<hr>

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
