const navToggle = document.querySelector("#navToggle");
const nav = document.querySelector("nav");
const navIcon = document.querySelectorAll(".nav-toggle .fas");
const cartNumP = document.querySelector(".cart-num p");
const cartNumDiv = document.querySelector(".cart-num");

navToggle.addEventListener("click", () => {
  nav.classList.toggle("nav-open");
  navIcon.forEach((icon) => {
    icon.classList.toggle("nav-hidden");
  });
});

if (cartNumP.innerHTML == "0") {
  cartNumDiv.classList.add("nav-hidden");
} else if (cartNumP.innerHTML > 0) {
  cartNumDiv.classList.remove("nav-hidden");
}
