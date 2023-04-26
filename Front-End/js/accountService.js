
// importing nav details from component/nav.js.
import { nav, navContent } from "../component/nav.js";

// import loadData,domain from common.js file..!
import { loadData,domain } from "./common.js";


window.addEventListener("load", () => {
  document.querySelector("nav").innerHTML = nav();
  navContent();

  let obj = JSON.parse(localStorage.getItem("data"));
  let bankAccountsArr = obj.bankAccounts;

  // console.log(bankAccountsArr[0]);
  appendAcc(bankAccountsArr);
});

let dropdown = document.querySelector(".dropdown");

dropdown.addEventListener("click", function () {
  this.classList.toggle("active");

  let dropdownContent = this.querySelector(".dropdown-content");

  if (dropdownContent.style.display === "block") {
    dropdownContent.style.display = "none";
  } else {
    dropdownContent.style.display = "block";
  }
});

// **********************************************************
// this will append bankAccounts to dropdown-content div..!
function appendAcc(data) {
  let cont = document.querySelector(".dropdown-content");
  cont.innerHTML = "";
  data.forEach((el, ind) => {
    let newDiv = document.createElement("div");
    // add class to new div
    newDiv.classList.add("bank-account");
    newDiv.innerHTML = `
      <p><b>${ind + 1}</b></p>
      <p>Account No: <b>${el.accountNo}</b></p>
      <p>Balance: <b>${el.balance}</b></p>
      <p>Bank Name: <b>${el.bankName}</b></p>
      <p>IFSC Code: <b>${el.ifscCode}</b></p>
      `;
    // creating button to append..!
    let btn = document.createElement("button");
    btn.innerHTML = "Delete";

    // Adding class to button..!
    btn.setAttribute("class", "delete");

    // Adding onclick to button..!
    btn.onclick = () => {
      deleteAcc(el.accountNo);
    };

    // appending button to all account div..!
    newDiv.append(btn);

    cont.append(newDiv);
  });
}

// **********************************************************

// *******************************Add Account Method*******************************

document.querySelector(".addAccount").addEventListener("click", () => {
  let accNumber = document.querySelector("#accountNo").value;
  let bankName = document.querySelector("#bankName").value;
  let ifsc = document.querySelector("#ifscCode").value;
  let balance = document.querySelector("#balance").value;

  addAccount(accNumber, bankName, ifsc, balance);
});

function addAccount(accNumber, bankName, ifscCode, balance) {
  if (
    accNumber >= 10000 &&
    accNumber <= 999999999 &&
    balance >= 0 &&
    bankName != "" &&
    ifscCode.toString().length > 5
  ) {
    let key = localStorage.getItem("uuid");

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      accountNo: accNumber,
      ifscCode: ifscCode,
      bankName: bankName,
      balance: balance,
    });

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    fetch(`${domain}/pws/accservice/accounts?key=${key}`, requestOptions).then(
      (response) => {
        if (response.status == 200) {
            loadData();
            
            alert("Account Added Successfully..");

            window.location.reload();
        }
      }
    );
    // .then((result) => console.log(result))
    // .catch((error) => console.log("error", error));
  } else {
    alert("Enter Valid Details In Form Fields..!");
    // console.log(accNumber, bankName, ifsc, balance);
  }
}

function clearAddAccountFields() {
  document.querySelector("#accountNo").value = "";
  document.querySelector("#bankName").value = "";
  document.querySelector("#ifscCode").value = "";
  document.querySelector("#balance").value = "";
}
// *******************************Add Account Method (END)*******************************

// *******************************Delete Account Method*******************************

// this will delete acc from wallet...!
function deleteAcc(accNumber) {
  let key = localStorage.getItem("uuid");

  var requestOptions = {
    method: "DELETE",
    redirect: "follow",
  };

  fetch(
    `${domain}/pws/accservice/accounts/${accNumber}?key=${key}`,
    requestOptions
  ).then((response) => {
    if (response.status == 202) {
        loadData();
        
        alert("Account removed from wallet successfully..");

        window.location.reload();
    } else {
      response.text().then((res) => {
        let obj = JSON.parse(res);

        alert(`${obj.message}`);
      });
    }
  });
  // .then((result) => console.log(result))
  // .catch((error) => console.log("error", error));
}

// *******************************Delete Account Method*******************************
