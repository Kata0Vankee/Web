let elements = document.querySelector('li a');
$(elements).click(function(){
	let span = document.querySelector('ul li');
	span.addClass('menu-ope')
})

// Upload and show photo
$(document).ready(function(){
	$('#submit_photo').click(function(){
		$('#photo').click();
	})

	//Read URL to show
	function readURL(input) {
		if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#photo_show').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
	}
	$('#photo').change(function(){
		readURL(this);
	})
})