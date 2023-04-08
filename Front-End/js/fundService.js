import { nav, navContent } from "../component/nav.js";

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

// onClicking add button we are calling deposit service..!
document.querySelector(".add").addEventListener("click", () => {
  let amt = document.querySelector("#dipositAmount").value;
  let accno = document.querySelector("#accountnum").value;

  if (amt > 0 && accno > 9999) {
    console.log(amt, accno);
  } else {
    alert("Give Proper values");
    document.querySelector("#dipositAmount").value = "";
    document.querySelector("#accountnum").value = "";
  }
});

//onClicking send button we are calling Transfer fund service..!
document.querySelector(".send").addEventListener("click", () => {
  let amt = document.querySelector("#transferAmount").value;
  let mob = document.querySelector("#Receivermobile").value;

  if (amt > 0 && mob > 9999999999) {
  } else {
    alert("Give Proper values");
    document.querySelector("#transferAmount").value = "";
    document.querySelector("#Receivermobile").value = "";
  }
});
