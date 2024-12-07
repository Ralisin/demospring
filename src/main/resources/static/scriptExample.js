function doGet() {
    // URL to make the request
    const url = 'cane/';

    // Execute GET request
    fetch(url)
        .then(response => {
            // Check if the response is ok (status code 200-299)
            if (!response.ok) {
                throw new Error('Error in the request: ' + response.status);
            }
            // Convert the response to text
            return response.text();
        })
        .then(data => {
            styleGet(data, 'responseToGet')
        })
        .catch(error => {
            // Handle possible errors
            console.error('Error:', error);
            document.getElementById('responseToGet').innerText = 'Error: ' + error.message;
        });
}

// Function to style a GET with JSON data (list or single item)
function styleGet(data, pId) {
    const jsonData = JSON.parse(data);

    const container = document.createElement('div');

    const rowTop = document.createElement('div');
    const rowMiddle = document.createElement('div');
    const rowBottom = document.createElement('div');

    rowMiddle.style.marginLeft = '25px';
    rowMiddle.style.display = 'flex';
    rowMiddle.style.alignItems = 'flex-start';

    container.appendChild(rowTop);
    container.appendChild(rowMiddle);
    container.appendChild(rowBottom);

    const preElementLeft = document.createElement('pre');
    preElementLeft.style.display = 'inline-block';
    preElementLeft.style.marginRight = '10px';
    preElementLeft.textContent = "[";

    rowTop.appendChild(preElementLeft);

    if (Array.isArray(jsonData)) {
        // Add single elements to the list
        jsonData.forEach((item, index) => {
            const formattedItem = JSON.stringify(item, null, 2);

            const preElement = document.createElement('pre');
            preElement.style.display = 'inline-block';
            preElement.style.marginRight = '10px';
            preElement.textContent = formattedItem + ((index < jsonData.length - 1) ? "," : "");

            rowMiddle.appendChild(preElement);
        });
    } else {
        const preElement = document.createElement('pre');
        preElement.style.display = 'inline-block';
        preElement.style.marginRight = '10px';
        preElement.textContent = JSON.stringify(jsonData, null, 2);

        rowMiddle.appendChild(preElement);
    }

    // Crea la riga inferiore con la parentesi quadra di chiusura
    const preElementRight = document.createElement('pre');
    preElementRight.style.display = 'inline-block';
    preElementRight.style.marginRight = '10px';
    preElementRight.textContent = "]";

    rowBottom.appendChild(preElementRight);

    document.getElementById(pId).innerHTML = '';
    document.getElementById(pId).appendChild(container);
}

// Function to clear output
function doClear() {
    document.getElementById('responseToGet').innerHTML = '';
}

// Function to perform the GET and display the results in a table
function doGetAndElaborateOutput() {
    // Get the dog name from the input field
    const dogName = document.getElementById('dogName').value;

    let url = "cane/";

    // If a dog name is provided, add it as a query parameter
    if (dogName) url = url + "search?nomeCane=" + dogName;

    // Execute the GET request
    fetch(url)
        .then(response => {
            // Check if the response is ok
            if (!response.ok) {
                throw new Error('Error in the request: ' + response.status);
            }
            return response.json(); // Convert response to JSON format
        })
        .then(data => {
            // Create a table with the data received
            createTable(data);
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('responseToGet').innerText = 'Error: ' + error.message;
        });
}

// Function to create a table with the received data
function createTable(data) {
    // Find the element where to insert the table
    const container = document.getElementById('responseToGet');

    // Create the table element
    const table = document.createElement('table');
    table.border = "1";  // Set border for table

    // Create the table header with relevant columns
    const thead = document.createElement('thead');
    thead.innerHTML = `
        <tr>
            <th>Dog ID</th>
            <th>Dog name</th>
            <th>Dog owner</th>
            <th>Address</th>
            <th>Zip</th>
            <th>Qualifications</th>
        </tr>
    `;
    table.appendChild(thead);

    // Create the table body
    const tbody = document.createElement('tbody');

    // Add one row for each element in the data array
    data.forEach(item => {
        const tr = document.createElement('tr');

        // Populate the table with data for each dog and its owner
        tr.innerHTML = `
            <td>${item.id}</td>  <!-- Dog's ID -->
            <td>${item.nomeCane}</td>  <!-- Dog's name -->
            <td>${item.padrone.nome} ${item.padrone.cognome}</td>  <!-- Owner's full name -->
            <td>${item.padrone.indirizzo.viaENumero}</td>  <!-- Owner's street address -->
            <td>${item.padrone.indirizzo.cap}</td>  <!-- Owner's postal code (ZIP) -->
            <td>${formatQualifications(item.padrone.titoliDiStudio)}</td>  <!-- Owner's qualifications -->
        `;

        tbody.appendChild(tr);
    });

    table.appendChild(tbody);

    // Clear the container and add the new table
    container.innerHTML = '';
    container.appendChild(table);
}

// Function to format the person's educational qualifications
function formatQualifications(qualifications) {
    // Format each qualification with its name and year of completion, along with the class of diploma if available
    return qualifications.map(qualification => `
        ${qualification.nomeTitolo} (${qualification.annoConseguimento})${qualification.classeDiploma ? ' - Subject: ' + qualification.classeDiploma : ''}
    `).join('<br>');  // Join each formatted qualification with a line break
}

// Fetch owners for the select dropdown
function fetchOwners() {
    const url = '/api/persona/';
    fetch(url)
        .then(response => {
            // Check if the response is ok
            if (!response.ok) {
                throw new Error('Error in retrieving owners: ' + response.status);
            }
            return response.json();  // Convert response to JSON
        })
        .then(data => {
            const ownerSelect = document.getElementById('dogOwner');
            // Populate the dropdown with owners retrieved from the API
            data.forEach(persona => {
                const option = document.createElement('option');
                option.value = persona.id;
                option.text = `${persona.nome} ${persona.cognome}`;
                ownerSelect.appendChild(option);
            });
        })
        .catch(error => {
            // Handle errors while fetching owners
            console.error('Error:', error);
            document.getElementById('createDogResponse').innerText = 'Error: ' + error.message;
        });
}

// Function to create a new dog
function createDog() {
    // Get the new dog's name and selected owner ID
    const dogName = document.getElementById('newDogName').value;
    const ownerId = document.getElementById('dogOwner').value;

    const token = sessionStorage.getItem('authToken');

    // Prepare the data to send in the POST request
    const data = {
        nomeCane: dogName, padrone: {id: ownerId}  // Only the owner ID is required
    };

    // Make the POST request to create the dog
    fetch('/api/cane/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',  // Set the request content type to JSON
            'Authorization': `Bearer ${token}`  // Set authorization token in the request
        }, body: JSON.stringify(data)  // Convert data to JSON string
    })
        .then(response => {
            return response.text().then(data => {
                if (response.ok) {
                    document.getElementById('createDogResponse').style.color = "black";
                    styleGet(data, 'createDogResponse');
                } else {
                    console.error('Error:', data);

                    document.getElementById('createDogResponse').style.color = "red";
                    document.getElementById('createDogResponse').innerText = data;
                }
            })
        })
        .catch(error => {
            console.error('Error:', error);

            document.getElementById('createDogResponse').style.color = "red";
            document.getElementById('createDogResponse').innerText = 'Error: ' + error.message;
        })
        // .then(response => {
        //     // Check if the dog was created successfully
        //     if (response.status === 201) {
        //         return response.json();  // Return the newly created dog data
        //     } else {
        //         throw new Error('Error in creating dog: ' + response.status);
        //     }
        // })
        // .then(data => {
        //     // Display a success message with the created dog's details
        //     styleGet(JSON.stringify(data), 'createDogResponse')
        // })
        // .catch(error => {
        //     // Handle errors during dog creation
        //     console.error('Error:', error);
        //     document.getElementById('createDogResponse').innerText = 'Error: ' + error.message;
        // });

    // // Make the POST request to create the dog
    // fetch('/api/cane/', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json',  // Set the request content type to JSON
    //         'Authorization': `Bearer ${token}`  // Set authorization token in the request
    //     }, body: JSON.stringify(data)  // Convert data to JSON string
    // })
    //     .then(response => {
    //         // Check if the dog was created successfully
    //         if (response.status === 201) {
    //             return response.json();  // Return the newly created dog data
    //         } else {
    //             throw new Error('Error in creating dog: ' + response.status);
    //         }
    //     })
    //     .then(data => {
    //         // Display a success message with the created dog's details
    //         styleGet(JSON.stringify(data), 'createDogResponse')
    //     })
    //     .catch(error => {
    //         // Handle errors during dog creation
    //         console.error('Error:', error);
    //         document.getElementById('createDogResponse').innerText = 'Error: ' + error.message;
    //     });
}

// Function to delete an existing dog via id
function deleteDog() {
    const dogId = document.getElementById('deleteDogId').value;
    if (dogId === "") {
        document.getElementById('deleteWrongResponse').innerText = 'No dog id passed';
        return;
    }

    const url = `/api/cane/${dogId}`;

    const token = sessionStorage.getItem('authToken');

    fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` // Set authorization token in the request
        }})
        .then(response => {
            if (response.ok) {
                document.getElementById('deleteWrongResponse').innerText = "";
                response.json().then(data => {
                    document.getElementById('deleteRightResponse').innerText = "Num deleted dogs: " +  data
                }).catch(error => {
                    console.error("Errore durante la lettura della risposta JSON:", error.message);
                });
            } else if (response.status === 400) { // Code 400 == Bad Request
                // Gestione specifica del Bad Request
                return response.text().then(data => {
                    console.error("Bad Request:", data);
                    document.getElementById('deleteWrongResponse').innerText = `Bad Request: check passed data`;
                });
            } else {
                response.text().then(data => {
                    console.error("Errore restituito dal server:", data);
                    document.getElementById('deleteWrongResponse').innerText = data;
                }).catch(error => {
                    console.error("Errore durante la lettura della risposta di errore:", error.message);
                });
                document.getElementById('deleteRightResponse').innerText = "";
            }
        })
        .catch(error => {
            console.error("Errore nella chiamata fetch:", error.message);
        });
}

// Function to login and store returned token in current browser session
function doLoginToken() {
    const url = '/api/loginWithToken';

    // Ottieni i dati dal modulo
    const username = document.getElementById("usrToken").value;
    const password = document.getElementById("pwdToken").value;

    if (username === "" || password === "") {
        document.getElementById('loginWithTokenWrongResponse').innerText = 'Empty username or password';
        return;
    }

    const data = {
        username: username,
        password: password
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'  // Set the request content type to JSON
        },
        body: JSON.stringify(data)  // Convert data to JSON string
    })
    .then(response => {
        return response.json().then(data => {
            if (response.ok) {
                console.log(data.token);  // Log the token or use it

                sessionStorage.setItem('authToken', data.token);

                document.getElementById('loginWithTokenWrongResponse').innerText = "";
                document.getElementById('loginWithTokenRightResponse').innerText = data.token;

                // return data.token;  // Return the response data (token or whatever is returned)
            } else {
                document.getElementById('loginWithTokenWrongResponse').innerText = 'Error ' + response.status + ': ' + data.error;
                document.getElementById('loginWithTokenRightResponse').innerText = "";
            }
        });
    });
}

// Call this function to fetch owners when the page loads
fetchOwners();
