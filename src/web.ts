import { WebPlugin } from '@capacitor/core';

import type { SumupPluginPlugin } from './definitions';

export class SumupPluginWeb extends WebPlugin implements SumupPluginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
