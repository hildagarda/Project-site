myModal = new bootstrap.Modal(document.getElementById('exampleModal'));

function create() {
    myModal.show();
    document.getElementById('personName').value = '';
    document.getElementById('personSurname').value = '';
    document.getElementById('personType').value = '';
}

function edit(id) {
    fetch('http://localhost:8080/admin/person/' + id)
        .then(response => response.json())
        .then(response => {
            document.getElementById('id').value = response.id;
            document.getElementById('filmName').value = response.name; // это что и почему только одно нэйм
            document.getElementById('filmSurname').value = response.surname;
            document.getElementById('filmTypeOfPerson').value = response.typeOfPerson;
        })
    myModal.show();
}
