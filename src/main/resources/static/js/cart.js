/*WishList Item Carousel*/
const prev = document.querySelector('.prev');
const next = document.querySelector('.next');
const track = document.querySelector('.carousel-track');
const carouselContainer = document.querySelector('.carousel-container').offsetWidth;

next.addEventListener('click', () => {
	track.style.transform = `translateX(-${carouselContainer}px)`;
})

prev.addEventListener('click', () => {
	track.style.transform = `translateX(-${0}px)`;
})