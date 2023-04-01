// let domain = "https://paywal-app-production.up.railway.app"; // cloud
let domain = "http://localhost:8484"; // local

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
  loginCheck();
  console.log("acc");
}


// Adding OnClick to bill_service div..!
document.querySelector(".bill_service").addEventListener("click", () => {
  billService();
});

function billService() {
  loginCheck();
  console.log("acc");
}


// Adding OnClick to beneficiary_service div..!
document.querySelector(".beneficiary_service").addEventListener("click", () => {
  beneficiaryService();
});

function beneficiaryService() {
  loginCheck();
  console.log("acc");
}


// Adding OnClick to transaction_service div..!
document.querySelector(".transaction_service").addEventListener("click", () => {
  transactionService();
});
function transactionService() {
  loginCheck();
  console.log("acc");
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

// This Function is  used to load the User Data with the Key present in LS..
function loadData() {
  let key = localStorage.getItem("uuid");

  if (key != null && key != "null" && key != undefined) {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    fetch(`${domain}/pws/wallet/viewbal?key=${key}`, requestOptions)
      .then((response) => {
        if (response.status == 200) {
          response.text().then((res) => {
            localStorage.setItem("data", res);
          });
        }
      })
      // .then((result) => console.log(result))
      .catch((error) => console.log("error", error));
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
