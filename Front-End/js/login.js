document.querySelector("form").addEventListener("submit", () => {
  login(event);
});

function login(e) {
  e.preventDefault();

  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  let mobile = document.getElementById("mobile").value;
  let password = document.getElementById("password").value;

  let raw = JSON.stringify({
    mobile: mobile,
    password: password,
  });

  let requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch("http://localhost:8484/pws/login", requestOptions)
    .then((response) => response.text())
    .then((result) => console.log(result))
    .catch((error) => console.log("error", error));
}
