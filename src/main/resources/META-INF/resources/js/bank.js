function loesche(id) {
    fetch("/banken/" + id, {
        method: 'DELETE'
    })
        .then(response => response.json())
        .then(data => {
            const parse = JSON.parse(data);
            console.log(parse);
        });
    // location.reload();
}
