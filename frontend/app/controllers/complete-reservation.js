import Controller from "@ember/controller";
import { inject as service } from "@ember/service";
import moment from 'moment';
export default Controller.extend({
    reservationDate: null,
    reservationTime: null,
    startDate: moment('2018-11-12 09:00:00').format('YYYY-MM-DD HH:mm:ss'),
});