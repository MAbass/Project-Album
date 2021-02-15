// Wait for the DOM to be ready
$(document).ready(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"

    $(".inscription").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            nom: "required",
            prenom: "required",
            login: "required",
            mail: {
                required: true,
                // Specify that email should be validated
                // by the built-in "email" rule
                email: true
            },
            password: {
                required: true,
                minlength: 1
            },
            passwordConf: {
                required: true,
                minlength: 1,
                equalTo : "[name='password']",

            }
        },
        // Specify validation error messages
        messages: {
            nom: "Donner le nom",
            prenom: "Donner le prenom",
            login: "Donner le prenom",
            password: {
                required: "Donner un mot de passe",
                minlength: "Ton mot de passe doit etre superieur a 5",
            },
            passwordConf: {
                required: "Donner un mot de passe",
                minlength: "Ton mot de passe doit etre superieur a 5",
                equalTo: "Les mots de passes ne correspondent pas",

            },
            mail: "Donner un email valid"
        },

        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function (form) {
            form.submit();
        }
    });
});