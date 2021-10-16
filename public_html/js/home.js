/** When a user scrolls down and center search bar disappears,
 *  Show a search bar on the nav bar **/
const searchbarOnNav = document.querySelector("#search-barOnNav");
const sectionOne = document.querySelector(".search-bar");

const sectionOneOptions = {
    rootMargin: "-95px 0px 0px 0px"
};

const sectionOneObserver = new IntersectionObserver(function(
    entries, 
    sectionOneObserver
) {
    entries.forEach(entry => {
        if(!entry.isIntersecting) {
            searchbarOnNav.classList.add("search-barShow");
        } else {
            searchbarOnNav.classList.remove("search-barShow");
        }
    })
}, sectionOneOptions);

sectionOneObserver.observe(sectionOne);


/** Slide show **/
const slidesShowImages = document.querySelectorAll(".slideShow-img");
const nextImageDelay = 5000;
let currentImageCounter = 0;

slidesShowImages[currentImageCounter].style.opacity = 1;

setInterval(nextImage, nextImageDelay);

function nextImage() {
    slidesShowImages[currentImageCounter].style.opacity = 0;
    currentImageCounter = (currentImageCounter + 1) % slidesShowImages.length;
    slidesShowImages[currentImageCounter].style.opacity = 1;
}