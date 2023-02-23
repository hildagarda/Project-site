var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
})

myModal = new bootstrap.Modal(document.getElementById('exampleModal'));

function create() {
    myModal.show();
    document.getElementById('serialName').value = '';
    document.getElementById('serialGenre').value = '';
    document.getElementById('serialCreated').value = '';
    document.getElementById('serialRating').value = '';
}

function edit(id) {
    fetch('http://localhost:8080/serial-table/' + id)
        .then(response => response.json())
        .then(response => {
            document.getElementById('id').value = response.id;
            document.getElementById('serialName').value = response.title;
            document.getElementById('serialGenre').value = response.genre;
            document.getElementById('serialCreated').value = response.created;
            document.getElementById('serialRating').value = response.rating;
        })
    myModal.show();
}