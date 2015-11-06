var Reflux = require("reflux");

var Actions = Reflux.createActions([
  "getNodes",
  "postConversion",
  // Session
  "setSession"
]);

module.exports = Actions;
