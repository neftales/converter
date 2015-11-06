var React = require("react");
var FileForm = require("./components/file-form.jsx");
var Actions = require("./actions/actions.js");

function app() {
  React.render(<FileForm/>, document.getElementById('content'));
}

window.onload = app;
