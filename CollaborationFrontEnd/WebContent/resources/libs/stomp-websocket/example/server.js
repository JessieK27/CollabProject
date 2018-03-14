var connect = require('connect');

connect()
  .use(connect.logger('dev'))
  .use(connect.static(__dirname))
  .use(connect.static(__dirname + '/../lib/'))
  .listen(8089);
console.log('Server running at http://0.0.0.0:8089/');
