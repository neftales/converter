// Dependencies
var _ = require("lodash");
var React = require('react');
var Reflux = require("reflux");
var smoke = require('../libs/smoke.js');
var qwest = require('qwest');
var fileToBase64 = require('filetobase64');

var FileForm = React.createClass({
  // since we are starting off without any data, there is no initial value
  getInitialState: function() {
    return {
      data_uri: null,
    };
  },

  // prevent form from submitting; we are going to capture the file contents
  handleSubmit: function(e) {
    e.preventDefault();

    qwest.post("http://192.168.1.14:8080/converter", {
      start: "PNG",
      end: "GIF",
      content: this.state.data_uri
    }, {
      dataType: "json",
      responseType: "json"
    })
    .then(function(response) {
       generateFile(response.baseData);
    }).catch(function(message) {
      smoke.signal(
        "Erro ao obter formulário.<br/>Tente novamente mais tarde."+ message,
        function() {},
        {duration: 3000}
      );
    }).complete(function() {trigger();});

  },

  generateFile: function(conversion) {
    var output = document.getElementById('output');
    output.src = 'data:' + conversion.format + ';base64,' + conversion.content;
  },

  // when a file is passed to the input field, retrieve the contents as a
  // base64-encoded data URI and save it to the component's state
  handleFile: function(e) {
    var self = this;
    var reader = new FileReader();
    var file = e.target.files[0];
    var base64 = fileToBase64(file);

    console.log(base64);

    self.setState({
      data_uri: base64 
    });

    trigger();
  },

  // return the structure to display and bind the onChange, onSubmit handlers
  render: function() {
    // since JSX is case sensitive, be sure to use 'encType'
    return (
      <form onSubmit={this.handleSubmit} encType="multipart/form-data">
        <input type="file" onChange={this.handleFile} />
        <input type="submit" />
      </form>
    );
  },
});

module.exports = FileForm;
