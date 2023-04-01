// Navbar code ..!

let obj = JSON.parse(localStorage.getItem("data"));

function nav() {
  return `<h1>PayWal</h1>
      <div class="navLinks">
        <h2 class="welcomeTag"></h2>
        <a>Profile <i class="fas fa-user-circle"></i></a>
        <a href="./index.html">Home <i class="fa-solid fa-house"></i></a>
      </div>`;
}

function navContent() {
  document.querySelector(
    ".welcomeTag"
  ).innerText = `Welcome ${obj.customer.name}..`;
}

export { nav, navContent };
