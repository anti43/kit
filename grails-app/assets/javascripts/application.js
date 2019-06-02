// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-3.3.1.min
//= require bootstrap
//= require popper.min
//= require_self

tinymce.init({
    selector: 'textarea:not(.nomce)',
    height: 250,
    theme: 'modern',
    plugins: '',
    toolbar1: 'formatselect | bold italic | link | numlist | removeformat',
    image_advtab: true,
    //templates: "/template/list",
    content_css: [
        '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
        '//www.tinymce.com/css/codepen.min.css'
    ],
    setup: function(editor) {
        editor.getElement().removeAttribute('required');
    }
});