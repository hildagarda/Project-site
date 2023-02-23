myModal = new bootstrap.Modal(document.getElementById('exampleModal'));

function create() {
    myModal.show();
    document.getElementById('bookName').value = '';
    document.getElementById('bookGenre').value = '';
    document.getElementById('bookCreated').value = '';
    document.getElementById('bookRating').value = '';
}

function edit(id) {
    fetch('http://localhost:8080/book-table/' + id)
        .then(response => response.json())
        .then(response => {
            document.getElementById('id').value = response.id;
            document.getElementById('bookName').value = response.title;
            document.getElementById('bookGenre').value = response.genre;
            document.getElementById('bookCreated').value = response.created;
            document.getElementById('bookRating').value = response.rating;
        })
    myModal.show();
}