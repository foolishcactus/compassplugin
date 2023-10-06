import { WebPlugin } from '@capacitor/core';

import type { compassPlugin } from './definitions';

export class compassWeb extends WebPlugin implements compassPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
