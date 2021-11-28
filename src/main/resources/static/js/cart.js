/*WishList Item Carousel*/
const prev = document.querySelector('.prev');
const next = document.querySelector('.next');
const track = document.querySelector('.carousel-track');
const carouselContainer = document.querySelector('.carousel-container');

if (next != null) {
	next.addEventListener('click', () => {
		var carouselContainerVar = carouselContainer.offsetWidth;
		track.style.transform = `translateX(-${carouselContainerVar}px)`;
	})
}

if (prev != null) {
	prev.addEventListener('click', () => {
		track.style.transform = `translateX(-${0}px)`;
	})
}