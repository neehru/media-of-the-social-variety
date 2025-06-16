"use strict";

let loadFile = function(event) {
    let output = document.getElementById('image-to-post');
    output.src = URL.createObjectURL(event.target.files[0]);
    output.onload = function() {
    URL.revokeObjectURL(output.src) // free memory
    }
};