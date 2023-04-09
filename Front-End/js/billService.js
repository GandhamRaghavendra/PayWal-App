import { nav, navContent } from "../component/nav.js";

// import loadData from main.js file..!
import { loadData } from "./common.js";

let domain = "http://localhost:8484"; // local

window.addEventListener("load", () => {
  document.querySelector("nav").innerHTML = nav();
  navContent();

  let obj = JSON.parse(localStorage.getItem("data"));
  let billsArr = obj.billPayments;

  appendBill(billsArr);
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

// this will append bankAccounts to dropdown-content div..!
function appendBill(data) {
  let cont = document.querySelector(".dropdown-content");
  cont.innerHTML = "";
  data.forEach((el, ind) => {
    let newDiv = document.createElement("div");
    // add class to new div
    newDiv.classList.add("bills");
    newDiv.innerHTML = `
          <p>BillId: <b>${el.billId}</b></p>
          <p>BillType: <b>${el.billType}</b></p>
          <p>Amount: <b>${el.amount}</b></p>
          <p>PaymentDate: <b>${el.paymentDate}</b></p>
        `;
    cont.append(newDiv);
  });
}

document.querySelector(".addBill").addEventListener("click", () => {
  let type = document.querySelector("#billType").value;
  let amount = document.querySelector("#amount").value;
  let date = document.querySelector("#paymentDate").value;

  // Creating Date Obj
  let dateObj = new Date(date);

  // Formating the Date form YYYY/MM/DD to DD/MM/YYYY;
  let formattedDate = `${dateObj.getDate().toString().padStart(2, "0")}/${(
    dateObj.getMonth() + 1
  )
    .toString()
    .padStart(2, "0")}/${dateObj.getFullYear()}`;

  addBill(type, formattedDate, amount);
});

function addBill(type, date, amount) {
  let key = localStorage.getItem("uuid");

  if (type != "" && date != "NaN/NaN/NaN" && amount > 0) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      billType: type,
      amount: amount,
      paymentDate: date,
    });

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    fetch(`${domain}/pws/bills/add?key=${key}`, requestOptions).then(
      (response) => {
        if (response.status == 200) {
          loadData();
          
          alert("Bill Added Successfully..");
          
          window.location.reload();
        } else {
          response.text().then((res) => {
            let obj = JSON.parse(res);

            alert(`${obj.message}`);
          });
        }
      }
    );
    // .then((result) => console.log(result))
    // .catch((error) => console.log("error", error));
  } else {
    alert("Provide Valid Details..!");
  }
}
