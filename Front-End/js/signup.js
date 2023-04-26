
// import domain from common.js file..!
import { domain } from "./common.js";

document.querySelector("form").addEventListener("submit", () => {
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

  fetch(`${domain}/pws/register`, requestOptions)
    .then((response) => {
      if (response.status == 200) {
        alert("SignUp Successful");

        window.location.href = "index.html";
      } else {
        response.text().then((res) => {
          let obj = JSON.parse(res);

          alert(`${obj.message}`);

          window.location.reload();
        });
      }
    })
    // .then((result) => console.log(result))
    .catch((error) => console.log("error", error));
}
