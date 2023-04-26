// import domain from common.js file..!
import { domain } from "./common.js";

document.querySelector("form").addEventListener("submit", () => {
  login(event);
});

let loadingScreen = document.querySelector(".loading-screen");

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

  fetch(`${domain}/pws/login`, requestOptions)
    .then((response) => {
      if (response.status == 200) {
        response.text().then((res) => {
          // Storing the Key to LS..
          localStorage.setItem("uuid", res);

          alert("Login Successful");

          // Directing to Index.html..
          window.location.href = "index.html";
        });
      } else {
        response.text().then((res) => {
          let obj = JSON.parse(res);

          if (
            window.confirm(
              `${obj.message}` + " OR Press OK to Register to PWS application"
            )
          ) {
            window.location.href = "signup.html";
          }
        });
      }
    })
    .catch((error) => {
      // if (window.confirm("Unnable to Login " +"/n"+"Press OK to Register to PWS application"+"/n"+`Error:${error}`)) {
      //   window.location.href = "signup.html";
      // }
      // console.log(error);
    });
}
