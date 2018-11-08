import Service from '@ember/service';
import sweetAlert from 'ember-sweetalert';

export default Service.extend({
  error(message) {
    sweetAlert({
      title: message,
      confirmButtonText: 'Try again',
      confirmButtonColor: '#DC5154',
      type: 'error'
    });
  },
  success(title, callback) {
    sweetAlert({
      title: title,
      confirmButtonText: 'OK',
      confirmButtonColor: '#DC5154',
      type: 'success'
    })
    .then(callback);
  },
  question(title, callback) {
    sweetAlert({
      title:title,
      confirmButtonText: 'Yes',
      showCancelButton: true,
      cancelButtonText: 'No',
      confirmButtonColor: '#DC5154',
      type: 'warning'
        })
        .then(callback);
  }
});
