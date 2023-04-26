
// import loadData from main.js file..!
import { loadData,domain } from "./common.js";

// importing navbar form component file..!
import { nav, navContent } from "../component/nav.js";

window.addEventListener("load", () => {

  // Getting nav to append navbar form nav.js
  document.querySelector("nav").innerHTML = nav();

  // This method for loding content in navbar..
  navContent(); 

  // Onload Wallet Method will be called
  wallet();
});

// Wallet Method
function wallet() {
  
  // This is for edit button..!
  localStorage.setItem("buttonValue", 0);
  let key = localStorage.getItem("uuid");
  let obj = JSON.parse(localStorage.getItem("data"));

  document.querySelector(".welcomeTag").innerText = `Welcome ${obj.customer.name}`;
  
  document.querySelector(".name").innerText = `${obj.customer.name}`;
  document.querySelector(".pass").innerText = `*********`;
}

//OnClick on Edit Button EditMethod Called
document.querySelector(".edit").addEventListener("click", () => {
  editMethod();
});

// EditMethod
function editMethod() {
  let val = localStorage.getItem("buttonValue");

  if (val == 0) {
    localStorage.setItem("buttonValue", 1);

    // Here we are making the field editable..
    let name = document.querySelector(".name");
    name.setAttribute("contenteditable", "true");
    name.classList.add("red");

    // Here we are making the field editable..
    let pass = document.querySelector(".pass");
    pass.setAttribute("contenteditable", "true");
    pass.classList.add("red");

    // Create a new button element
    let saveBtn = document.createElement("button");

    // Set the button text
    saveBtn.innerHTML = "Save";

    //adding class
    saveBtn.classList.add("save");

    // OnClick SaveBtn
    saveBtn.onclick = () => {
      let name = document.querySelector(".name").innerText;
      let pass = document.querySelector(".pass").innerText;

      UpdateNameAndPass(name, pass);
    };

    // Create a new button element
    let cancelBtn = document.createElement("button");

    // Set the button text
    cancelBtn.innerHTML = "Cancel";

    // adding class
    cancelBtn.classList.add("cancel");

    // Onclick cancleBtn
    cancelBtn.onclick = () => {
      cancel();
      console.log("Cancle");
    };

    document.querySelector(".update-wallet-div").appendChild(saveBtn);
    document.querySelector(".update-wallet-div").appendChild(cancelBtn);
  }
}

// Cancel Method
function cancel() {
  localStorage.setItem("buttonValue", 0);

  // Here we are making the field editable..
  let name = document.querySelector(".name");
  name.setAttribute("contenteditable", "false");
  name.classList.remove("red");

  // Here we are making the field editable..
  let pass = document.querySelector(".pass");
  pass.setAttribute("contenteditable", "false");
  pass.classList.remove("red");

  // Removing save and cancel buttons..
  document
    .querySelector(".update-wallet-div")
    .removeChild(document.querySelector(".save"));
  document
    .querySelector(".update-wallet-div")
    .removeChild(document.querySelector(".cancel"));
}

// UpdateNameAndPass Method ..!
function UpdateNameAndPass(name, pass) {
  // Getting Key from LS..!
  let key = localStorage.getItem("uuid");

  if (pass == "*********" || pass.length < 6 || name.length < 6) {
    alert("Give Proper Name and Password To update");
    window.location.reload();
  } else {
    // ** Method Call for Updating Name And Pass **
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let raw = JSON.stringify({
      name: `${name}`,
      password: `${pass}`,
    });

    let requestOptions = {
      method: "PUT",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    fetch(`${domain}/pws/wallet/update?kye=${key}`, requestOptions)
      .then((response) => {
        if (response.status == 202) {
          loadData();

          setTimeout(() => {
            wallet();
          }, 2000);
        }
      })
      //   .then((result) => console.log(result))
      .catch((error) => console.log("error", error));
    // ** MethodCall for Updating Name And Pass ** (END)..
  }
}
