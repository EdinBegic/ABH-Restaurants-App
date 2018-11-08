import { helper } from '@ember/component/helper';

export function neq([value1, value2]) {
  if(value1 != value2) {
    return true;
  }
  return false;
}

export default helper(neq);
