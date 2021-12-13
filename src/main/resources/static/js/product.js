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

/*Rating System*/
document.addEventListener('DOMContentLoaded', function(){
    let stars = document.querySelectorAll('.star');
    stars.forEach(function(star){
        star.addEventListener('click', setRating); 
    });
    
    let rating = parseInt(document.querySelector('.star-rating-container').getAttribute('data-value'));
    let target = stars[rating - 1];
    target.dispatchEvent(new MouseEvent('click'));
});

function setRating(ev){
    let span = ev.currentTarget;
    let stars = document.querySelectorAll('.star');
    let match = false;
    let num = 0;
    stars.forEach(function(star, index){
        if(match){
            star.classList.remove('rated');
        }else{
            star.classList.add('rated');
        }
        //are we currently looking at the span that was clicked
        if(star === span){
            match = true;
            num = index + 1;
        }
    });
    document.querySelector('.star-rating-container').setAttribute('data-value', num);
	document.querySelector('#rating').value = num;
}

/*Edit Review*/
document.addEventListener('DOMContentLoaded', function(){
    let stars = document.querySelectorAll('.star-edit');
    stars.forEach(function(star){
        star.addEventListener('click', setRatingEdit);
    });
    
    let rating = parseInt(document.querySelector('.edit-star-rating-container').getAttribute('data-value'));
    let target = stars[rating - 1];
    target.dispatchEvent(new MouseEvent('click'));
});

function setRatingEdit(ev){
    let span = ev.currentTarget;
    let stars = document.querySelectorAll('.star-edit');
    let match = false;
    let num = 0;
    stars.forEach(function(star, index){
        if(match){
            star.classList.remove('rated');
        }else{
            star.classList.add('rated');
        }
        //are we currently looking at the span that was clicked
        if(star === span){
            match = true;
            num = index + 1;
        }
    });
	document.querySelector('.edit-star-rating-container').setAttribute('data-value', num);
	document.querySelector('#editRating').value = num;
}