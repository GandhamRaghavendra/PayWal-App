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
