import { helper } from '@ember/component/helper';
import Ember from 'ember';
import CONSTANTS from '../constants';
export function priceRange([range]) {
  let signs = '';
  for(let i = 0; i < range; i++){
    signs += '<div class="active-sign">$</div>';
  }
  for(let i = 0; i < CONSTANTS.PRICE_RANGE_SIZE - range; i++) {
    signs += '<div class="not-active-sign">$</div>';
  }
  return Ember.String.htmlSafe(signs);
}

export default helper(priceRange);
