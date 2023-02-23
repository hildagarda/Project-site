myModal = new bootstrap.Modal(document.getElementById('exampleModal'));

function create() {
    myModal.show();
    document.getElementById('animeName').value = '';
    document.getElementById('animeGenre').value = '';
    document.getElementById('animeCreated').value = '';
    document.getElementById('animeRating').value = '';
}

function edit(id) {
    fetch('http://localhost:8080/anime-table/' + id)
        .then(response => response.json())
        .then(response => {
            document.getElementById('id').value = response.id;
            document.getElementById('animeName').value = response.title;
            document.getElementById('animeGenre').value = response.genre;
            document.getElementById('animeCreated').value = response.created;
            document.getElementById('animeRating').value = response.rating;
        })
    myModal.show();
}