var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
})

myModal = new bootstrap.Modal(document.getElementById('exampleModal'));

function create() {
    myModal.show();
    document.getElementById('filmName').value = '';
    document.getElementById('filmGenre').value = '';
    document.getElementById('filmCreated').value = '';
    document.getElementById('filmRating').value = '';
}

function edit(id) {
    fetch('http://localhost:8080/film-table/' + id)
        .then(response => response.json())
        .then(response => {
            document.getElementById('id').value = response.id;
            document.getElementById('filmName').value = response.title;
            document.getElementById('filmGenre').value = response.genre;
            document.getElementById('filmCreated').value = response.created;
            document.getElementById('filmRating').value = response.rating;
        })
    myModal.show();
}