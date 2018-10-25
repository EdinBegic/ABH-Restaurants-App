import { helper } from '@ember/component/helper';

export function mod([dividend, divisor]) {
  return dividend % divisor;
}

export default helper(mod);
