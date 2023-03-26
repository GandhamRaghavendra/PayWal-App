
document.querySelector("form").addEventListener('submit', () => {
  submit(event);
});

function submit(event) {
  event.preventDefault();

  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  let mobile = document.getElementById("mobile").value;
  let name = document.getElementById("name").value;
  let password = document.getElementById("password").value;

  let raw = JSON.stringify({
    mobile: mobile,
    name: name,
    password: password,
  });

  let requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch("http://localhost:8484/pws/register", requestOptions)
    .then((response) => response.text())
    .then((result) => console.log(result))
    .catch((error) => console.log("error", error));
}