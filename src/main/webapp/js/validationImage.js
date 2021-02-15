// Wait for the DOM to be ready
$(document).ready(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"

    $(".image").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            titre: "required",
            type: "required",
            desc: "required",
            motscles: "required",
            image: "required",

        },
        // Specify validation error messages
        messages: {
            titre: "Donner un titre",
            type: "Donner un type",
            desc: "Donner une description",
            motscles: "Donner un ou des mots cles",
            image: "Donner un image",

        },

        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function (form) {
            form.submit();
        }
    });
});