const navToggle = document.querySelector("#navToggle");
const nav = document.querySelector("nav");
const navIcon = document.querySelectorAll(".nav-toggle .fas");

navToggle.addEventListener("click", () => {
  nav.classList.toggle("nav-open");
  navIcon.forEach((icon) => {
    icon.classList.toggle("nav-hidden");
  });
});
