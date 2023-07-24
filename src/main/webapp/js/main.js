const form = document.getElementById('register-form');
const nameInput = document.getElementById('name');
const uNameInput = document.getElementById('u_name');
const emailInput = document.getElementById('email');
const passInput = document.getElementById('pass');
const rePassInput = document.getElementById('re_pass');
const contactInput = document.getElementById('contact');
const submitButton = document.getElementById('signup');

form.addEventListener('submit', function(event) {
	event.preventDefault();
	if (validateForm()) {
		form.submit();
	}
});

function validateForm() {
	clearErrors();

	const name = nameInput.value.trim();
	const uName = uNameInput.value.trim();
	const email = emailInput.value.trim();
	const pass = passInput.value;
	const rePass = rePassInput.value;
	const contact = contactInput.value.trim();

	let isValid = true;

	if (name === '' || uName === '' || email === '' || pass === '' || rePass === '' || contact === '') {
		showError('All fields are required');
		isValid = false;
	}

	if (!/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(pass)) {
		showError('Password must be at least 8 characters long and contain at least one uppercase or lowercase letter, one digit, and one special character (@, $, !, %, *, #, ?, &).');
		isValid = false;
	}
	if (pass !== rePass) {
		showError('Passwords do not match');
		isValid = false;
	}



	if (!/^\d{10}$/.test(contact)) {
		showError('Contact number must be a 10-digit numeric value');
		isValid = false;
	}

	return isValid;
}

function showError(message) {
	alert(message);

}

function clearErrors() {
	const errorDivs = form.querySelectorAll('.alert-danger');
	errorDivs.forEach((errorDiv) => {
		errorDiv.remove();
	});
}
