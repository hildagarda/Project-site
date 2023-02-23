myModal = new bootstrap.Modal(document.getElementById('exampleModal'));

function create() {
    myModal.show();
    document.getElementById('genreName').value = '';
}

function edit(id) {
    fetch('http://localhost:8080/admin/genre/' + id)
        .then(response => response.json())
        .then(response => {
            document.getElementById('id').value = response.id;
            document.getElementById('serialName').value = response.name;
        })
    myModal.show();
}
