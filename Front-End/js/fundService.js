
// importing nav details from component/nav.js.
import { nav, navContent } from "../component/nav.js";

// import loadData from main.js file..!
import { loadData,domain } from "./common.js";


window.addEventListener("load", () => {
  document.querySelector("nav").innerHTML = nav();
  navContent();
});

document.querySelector(".selfdeposit_div").addEventListener("click", () => {
  deposit();
});

document.querySelector(".transfer_div").addEventListener("click", () => {
  transfer();
});

document.querySelector(".deposit_cancel").addEventListener("click", () => {
  deposit();
});

document.querySelector(".transfer_cancel").addEventListener("click", () => {
  transfer();
});

function deposit() {
  let icon = document.querySelector(".deposit");
  let dropdown = document.querySelector(".dropdown_selfdeposit");
  if (icon.classList.contains("rotate")) {
    icon.classList.remove("rotate");
    dropdown.style.visibility = "hidden";
  } else {
    icon.classList.add("rotate");
    dropdown.style.visibility = "visible";
  }
}

function transfer() {
  let icon = document.querySelector(".transfer");
  let dropdown = document.querySelector(".dropdown_transfer");
  if (icon.classList.contains("rotate")) {
    icon.classList.remove("rotate");
    dropdown.style.visibility = "hidden";
  } else {
    icon.classList.add("rotate");
    dropdown.style.visibility = "visible";
  }
}

// ****************************************************************************
// onClicking add button we are calling deposit service..!
document.querySelector(".add").addEventListener("click", () => {
  let amt = document.querySelector("#dipositAmount").value;
  let accno = document.querySelector("#accountnum").value;
  let key = localStorage.getItem("uuid");

  if (amt > 0 && accno > 9999) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      accountNo: `${accno}`,
    });

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    fetch(
      `${domain}/pws/wallet/deposit?amount=${amt}&key=${key}`,
      requestOptions
    ).then((response) => {
      if (response.status == 200) {
        alert("Fund Added Successfully..");

        // This will update our LS data..!
        loadData();

        depositFieldClean();
      } else {
        response.text().then((res) => {
          let obj = JSON.parse(res);

          alert(`${obj.message}`);

          depositFieldClean();
        });
      }
    });
    // .then((result) => console.log(result))
    // .catch((error) => console.log("error", error));
  } else {
    alert("Give Proper values");
    depositFieldClean();
  }
});

// This method is called when we need to clean the deposit Field..!!
function depositFieldClean() {
  document.querySelector("#dipositAmount").value = "";
  document.querySelector("#accountnum").value = "";
}

// ****************************************************************************

// ****************************************************************************
//onClicking send button we are calling Transfer fund service..!
document.querySelector(".send").addEventListener("click", () => {
  let amt = document.querySelector("#transferAmount").value;
  let mob = document.querySelector("#Receivermobile").value;
  let key = localStorage.getItem("uuid");

  if (amt > 0 && mob > 999999999) {
    var requestOptions = {
      method: "POST",
      redirect: "follow",
    };

    fetch(
      `${domain}/pws/wallet/transfer?tmob=${mob}&amount=${amt}&key=${key}`,
      requestOptions
    ).then((response) => {
      if (response.status == 200) {
        alert("Fund Transfered successfully");

        // This will update our LS data..!
        loadData();

        transferFieldClean();
      } else {
        response.text().then((res) => {
          let obj = JSON.parse(res);

          alert(`${obj.message}`);

          transferFieldClean();
        });
      }
    });
    // .then((result) => console.log(result))
    // .catch((error) => console.log("error", error));
  } else {
    alert("Give Proper values");
    transferFieldClean();
  }
});

// This method is called when we need to clean the transfer Field..!!
function transferFieldClean() {
  document.querySelector("#transferAmount").value = "";
  document.querySelector("#Receivermobile").value = "";
}

// ****************************************************************************
