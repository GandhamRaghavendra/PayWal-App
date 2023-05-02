let domain = "https://paywal-app-production.up.railway.app"; // cloud
// let domain = "http://localhost:8484"; // local

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

export { loadData, domain };
