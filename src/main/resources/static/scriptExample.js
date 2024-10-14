function doGet() {
    // URL a cui fare la richiesta
    const url = 'cane/';

    // Execute GET request
    fetch(url)
        .then(response => {
            // Check if the response is ok (status code 200-299)
            if (!response.ok) {
                throw new Error('Error in the request: ' + response.status);
            }
            // Convert response in text
            return response.text();
        })
        .then(data => {
            // Show the result in the paragraph with id "responseToGet"
            document.getElementById('responseToGet').innerText = data;
        })
        .catch(error => {
            // Manage possible errors
            console.error(':', error);
            document.getElementById('responseToGet').innerText = 'Error: ' + error.message;
        });
}



// Funzione per effettuare la GET e mostrare i risultati in una tabella
function doGetAndElaborateOutput() {
    // URL a cui fare la richiesta
    const dogName = document.getElementById('dogName').value;

    var url = "cane/";
    // URL a cui fare la richiesta, aggiungendo il parametro nomeCane
    if(dogName)
        url = url + "search?nomeCane=" + dogName;

    // Eseguire la GET request
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error in the request: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            // Create table with data
            createTable(data);
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('responseToGet').innerText = 'Error: ' + error.message;
        });
}

// Function to create a table with received data
function createTable(data) {
    // Trova l'elemento dove inserire la tabella
    const container = document.getElementById('responseToGet');

    // Crea l'elemento table
    const table = document.createElement('table');
    table.border = "1";

    // Create the table header
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

    // Add one row for each element of the data array
    data.forEach(item => {
        const tr = document.createElement('tr');

        // Columns for each object property
        tr.innerHTML = `
            <td>${item.id}</td>
            <td>${item.nomeCane}</td>
            <td>${item.padrone.nome} ${item.padrone.cognome}</td>
            <td>${item.padrone.indirizzo.viaENumero}</td>
            <td>${item.padrone.indirizzo.cap}</td>
            <td>${formatQualifications(item.padrone.titoliDiStudio)}</td>
        `;
        tbody.appendChild(tr);
    });

    table.appendChild(tbody);

    // Empty the container and add the table
    container.innerHTML = '';
    container.appendChild(table);
}

// function to format the person's educational qualifications
function formatQualifications(qualifications) {
    return qualifications.map(qualification => `
        ${qualification.nomeTitolo} (${qualification.annoConseguimento})${qualification.classeDiploma ? ' - Subject: ' + qualification.classeDiploma : ''}
    `).join('<br>');
}