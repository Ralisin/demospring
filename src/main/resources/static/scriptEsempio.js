function effettuaGet() {
    // URL a cui fare la richiesta
    const url = 'cane/';

    // Eseguire la GET request
    fetch(url)
        .then(response => {
            // Controlla se la risposta è ok (status code 200-299)
            if (!response.ok) {
                throw new Error('Errore nella richiesta: ' + response.status);
            }
            // Convertire la risposta in testo
            return response.text();
        })
        .then(data => {
            // Mostrare il risultato nel paragrafo con id "rispostaAllaGet"
            document.getElementById('rispostaAllaGet').innerText = data;
        })
        .catch(error => {
            // Gestire eventuali errori
            console.error('Errore:', error);
            document.getElementById('rispostaAllaGet').innerText = 'Errore: ' + error.message;
        });
}



// Funzione per effettuare la GET e mostrare i risultati in una tabella
function effettuaGetEdElaboraOutput() {
    // URL a cui fare la richiesta
    const url = 'cane/';

    // Eseguire la GET request
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Errore nella richiesta: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            // Crea la tabella con i dati
            creaTabella(data);
        })
        .catch(error => {
            console.error('Errore:', error);
            document.getElementById('rispostaAllaGet').innerText = 'Errore: ' + error.message;
        });
}

// Funzione per creare una tabella con i dati ricevuti
function creaTabella(data) {
    // Trova l'elemento dove inserire la tabella
    const container = document.getElementById('rispostaAllaGet');

    // Crea l'elemento table
    const table = document.createElement('table');
    table.border = "1";

    // Crea l'header della tabella
    const thead = document.createElement('thead');
    thead.innerHTML = `
        <tr>
            <th>ID Cane</th>
            <th>Nome Cane</th>
            <th>Nome Padrone</th>
            <th>Cognome Padrone</th>
            <th>Via e Numero</th>
            <th>CAP</th>
            <th>Titoli di Studio</th>
        </tr>
    `;
    table.appendChild(thead);

    // Crea il corpo della tabella
    const tbody = document.createElement('tbody');

    // Aggiungi una riga per ogni elemento dell'array data
    data.forEach(item => {
        const tr = document.createElement('tr');

        // Colonne per ogni proprietà dell'oggetto
        tr.innerHTML = `
            <td>${item.id}</td>
            <td>${item.nomeCane}</td>
            <td>${item.padrone.nome}</td>
            <td>${item.padrone.cognome}</td>
            <td>${item.padrone.indirizzo.viaENumero}</td>
            <td>${item.padrone.indirizzo.cap}</td>
            <td>${formattaTitoliDiStudio(item.padrone.titoliDiStudio)}</td>
        `;
        tbody.appendChild(tr);
    });

    table.appendChild(tbody);

    // Svuota il contenitore e aggiungi la tabella
    container.innerHTML = '';
    container.appendChild(table);
}

// Funzione per formattare la lista di titoli di studio
function formattaTitoliDiStudio(titoli) {
    return titoli.map(titolo => `
        ${titolo.nomeTitolo} (${titolo.annoConseguimento})${titolo.cicloUnico !== undefined ? ' - Ciclo Unico: ' + titolo.cicloUnico : ''}${titolo.classeDiploma ? ' - Classe: ' + titolo.classeDiploma : ''}
    `).join('<br>');
}