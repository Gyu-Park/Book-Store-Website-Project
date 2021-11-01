/*Review Modal Close*/
var reviewModal = document.getElementById('product-review-modal-form');

window.onclick = function(event) {
	if (event.target == reviewModal) {
			reviewModal.style.display = "none";
	}
}