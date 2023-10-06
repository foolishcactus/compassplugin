import { registerPlugin } from '@capacitor/core';

import type { compassPlugin } from './definitions';

const compass = registerPlugin<compassPlugin>('compass', {
  web: () => import('./web').then(m => new m.compassWeb()),
});

export * from './definitions';
export { compass };
