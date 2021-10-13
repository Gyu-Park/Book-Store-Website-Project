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