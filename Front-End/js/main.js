
// import loadData,domain from common.js file..!
import { loadData, domain } from "./common.js";

let key = localStorage.getItem("uuid") || null;

document.querySelector("body").onload = () => {
  loginSucc();
  loadData();
};

function loginCheck() {
  if (key == null || key == undefined || key == "null") return false;
  else return true;
}

// Adding OnClick to wallet_service div..!
document.querySelector(".wallet_service").addEventListener("click", () => {
  walletService();
});

function walletService() {
  if (loginCheck()) {
    window.location.href = "walletService.html";
  } else {
    if (window.confirm("Login first..")) window.location.href = "login.html";
  }
}

// Adding OnClick to fund_service div..!
document.querySelector(".fund_service").addEventListener("click", () => {
  fundService();
});

function fundService() {
  if (loginCheck()) {
    window.location.href = "fund_M_Service.html";
  } else {
    if (window.confirm("Login first..")) window.location.href = "login.html";
  }
}

// Adding OnClick to acc_service div..!
document.querySelector(".acc_service").addEventListener("click", () => {
  accountService();
});

function accountService() {
  if (loginCheck()) {
    window.location.href = "accService.html";
  } else {
    if (window.confirm("Login first..")) window.location.href = "login.html";
  }
}

// Adding OnClick to bill_service div..!
document.querySelector(".bill_service").addEventListener("click", () => {
  billService();
});

function billService() {
  if (loginCheck()) {
    window.location.href = "billService.html";
  } else {
    if (window.confirm("Login first..")) window.location.href = "login.html";
  }
}

// Adding OnClick to beneficiary_service div..!
document.querySelector(".beneficiary_service").addEventListener("click", () => {
  beneficiaryService();
});

function beneficiaryService() {
  if (loginCheck()) {
    window.location.href = "beneficiaryService.html";
  } else {
    if (window.confirm("Login first..")) window.location.href = "login.html";
  }
}

// Adding OnClick to transaction_service div..!
document.querySelector(".transaction_service").addEventListener("click", () => {
  transactionService();
});
function transactionService() {
  if (loginCheck()) {
    window.location.href = "transactionService.html";
  } else {
    if (window.confirm("Login first..")) window.location.href = "login.html";
  }
}

function loginSucc() {
  key = localStorage.getItem("uuid");

  if (key != null && key != "null" && key != undefined) {
    // Create a new Font Awesome icon element
    let icon = document.createElement("i");

    icon.classList.add("fas", "fa-sign-out-alt");

    // On click logout method will be called..
    icon.onclick = () => {
      logout();
    };

    // Append the anchor element to the div
    let div = document.querySelector(".navLinks");
    div.appendChild(icon);
  } else {
    // Key is null (It will remove the sign-out icon..)
    let icon = document.querySelector(".fa-sign-out-alt");
    if (icon) {
      icon.remove();
    }
  }
}

function logout() {
  let key = localStorage.getItem("uuid");

  var requestOptions = {
    method: "GET",
    redirect: "follow",
  };

  fetch(`${domain}/pws/logout?key=${key}`, requestOptions)
    .then((response) => response.text())
    .then((result) => console.log(result))
    .catch((error) => console.log("error", error));

  localStorage.setItem("uuid", null);
  localStorage.setItem("data", null);

  loginSucc();
  // console.log("logout");
}
