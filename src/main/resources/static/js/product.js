/*Review Modal Close*/
var reviewModal = document.getElementById('product-review-modal-form');

window.onclick = function(event) {
	if (event.target == reviewModal) {
			reviewModal.style.display = "none";
	}
}

/*Similar Item Carousel*/
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