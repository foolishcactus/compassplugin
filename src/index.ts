import { registerPlugin } from '@capacitor/core';

import type { compassPlugin } from './definitions';

const compass = registerPlugin<compassPlugin>('compass', {
});

export * from './definitions';
export { compass };
