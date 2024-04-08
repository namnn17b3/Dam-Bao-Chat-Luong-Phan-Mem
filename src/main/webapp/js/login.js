document.querySelector('input[type="submit"]').onclick = () => {    
    const urlencoded = new URLSearchParams();
    urlencoded.append("email", document.querySelector('input[type="email"]').value);
    urlencoded.append("password", document.querySelector('input[type="password"]').value);
    
    const requestOptions = {
        method: "POST",
        body: urlencoded,
    };
    
    fetch(`${window.location.protocol}//${window.location.host}/api/teacher/login`, requestOptions)
        .then((response) => response.json())
        .then((result) => {
            if (result.status != 200) {
                Swal.fire({
                    title: "Opp!!!Error",
                    text: result['message'],
                    icon: "error"
                });
            }
            else {
                console.log('ok');
                window.location.href = `${window.location.protocol}//${window.location.host}/teacher-dashboard`;
            }
        })
        .catch((error) => console.error(error));
}

