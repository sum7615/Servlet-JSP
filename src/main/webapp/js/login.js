const form = document.getElementById('login-form');

const uNameInput = document.getElementById('username');
const passInput = document.getElementById('password');

const submitButton = document.getElementById('signin');

form.addEventListener('submit', function (event) {
    event.preventDefault(); 
    if (validateForm()) {
        form.submit(); 
    }
});

function validateForm() {
    clearErrors();

    const uName = uNameInput.value.trim();
    const pass = passInput.value;

    let isValid = true; 

    if (uName === '' || pass === '' ) {
        showError('All fields are required');
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
